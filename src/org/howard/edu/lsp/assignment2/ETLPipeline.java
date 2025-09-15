package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


// Simple CSV ETL: extract → transform → load for products.csv
public class ETLPipeline {

    // Entry point: wires extract → transform → load and prints summary
    public static void main(String[] args) {
        Path inputPath = Paths.get("data", "products.csv");
        Path outputPath = Paths.get("data", "transformed_products.csv");

        List<Product> extractedRows = new ArrayList<>();
        List<Product> transformedRows = new ArrayList<>();

        int rowsRead = 0;
        int rowsTransformed = 0;
        int rowsSkipped = 0;

        // Stop if input file is missing 
        if (!java.nio.file.Files.exists(inputPath)) {
            System.err.println("Error: Input file not found: " + inputPath.toString());
            System.err.println("Ensure you are running from the project root and that data/products.csv exists.");
            return;
        }

        try {
            ExtractStats stats = extract(inputPath);
            extractedRows = stats.products;
            rowsRead = stats.totalRows;
            rowsSkipped = stats.skippedRows;

            transformedRows = transform(extractedRows);
            rowsTransformed = transformedRows.size();

            load(outputPath, transformedRows);
        } catch (IOException ioEx) {
            System.err.println("I/O error during ETL run: " + ioEx.getMessage());
        }

        printSummary(rowsRead, rowsTransformed, rowsSkipped, outputPath);
    }

    
    // Minimal row model used internally during transform
    private static class Product {
        int productId;
        String name;
        double price;
        String category;
        String priceRange; // computed during transform

        Product(int productId, String name, double price, String category) {
            this.productId = productId;
            this.name = name;
            this.price = price;
            this.category = category;
        }
    }

    // Holds parsed products and counters for summary
    private static class ExtractStats {
        List<Product> products = new ArrayList<>();
        int totalRows = 0;      // number of data rows 
        int skippedRows = 0;    // malformed/parse-failed rows
    }

    /**
     * Extract rows from the input CSV at data/products.csv using a relative path.
     * - File existence is checked in main 
     * - If the file is empty: return zero totals and empty products.
     * - Assumes simple CSV with no quoted commas.
     */
    // Read CSV (header + rows), count total/skipped, and parse valid rows
    private static ExtractStats extract(Path inputPath) throws IOException {
        ExtractStats stats = new ExtractStats();

        try (BufferedReader reader = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8)) {
            String header = reader.readLine();
            if (header == null) {
                return stats;
            }

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                stats.totalRows++;
                String[] fields = line.split(",", -1);
                if (fields.length != 4) {
                    stats.skippedRows++;
                    continue;
                }
                try {
                    int productId = Integer.parseInt(fields[0].trim());
                    String name = fields[1].trim();
                    double price = Double.parseDouble(fields[2].trim());
                    String category = fields[3].trim();
                    stats.products.add(new Product(productId, name, price, category));
                } catch (NumberFormatException nfe) {
                    stats.skippedRows++;
                }
            }
        }

        return stats;
    }

    /**
     * Transform rows following required order and rules.
     */
    // Apply required rules in order: uppercase → discount → round → recategorize → price range
    private static List<Product> transform(List<Product> products) {
        List<Product> transformed = new ArrayList<>();

        for (Product p : products) {
            String originalCategory = p.category == null ? "" : p.category;

            // Uppercase name
            String newName = p.name == null ? null : p.name.toUpperCase();

            // 10% discount for Electronics
            double newPrice = p.price;
            if ("Electronics".equalsIgnoreCase(originalCategory)) {
                newPrice = p.price * 0.9; // apply 10% off
            }
            newPrice = roundToTwoDecimals(newPrice);

            // Recategorize if final price > 500 and original category was Electronics
            String newCategory = originalCategory;
            if ("Electronics".equalsIgnoreCase(originalCategory) && newPrice > 500.0) {
                newCategory = "Premium Electronics";
            }

            // Compute PriceRange from final price
            String priceRange = determinePriceRange(newPrice);

            Product np = new Product(p.productId, newName, newPrice, newCategory);
            np.priceRange = priceRange;
            transformed.add(np);
        }

        return transformed;
    }

    // Two-decimal HALF_UP rounding as allowed by the spec
    private static double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    // Map final price to a PriceRange bucket
    private static String determinePriceRange(double price) {
        if (price <= 10.00) {
            return "Low";
        } else if (price <= 100.00) {
            return "Medium";
        } else if (price <= 500.00) {
            return "High";
        } else {
            return "Premium";
        }
    }

    /**
     * Load transformed rows to data/transformed_products.csv with header.
     */
    // Write header and transformed rows to data/transformed_products.csv
    private static void load(Path outputPath, List<Product> transformedProducts) throws IOException {
        if (outputPath.getParent() != null) {
            java.nio.file.Files.createDirectories(outputPath.getParent());
        }

        try (java.io.BufferedWriter writer = java.nio.file.Files.newBufferedWriter(outputPath, java.nio.charset.StandardCharsets.UTF_8)) {
            // Header
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            // Rows
            for (Product p : transformedProducts) {
                String priceStr = String.format(java.util.Locale.US, "%.2f", p.price);
                String name = p.name == null ? "" : p.name;
                String category = p.category == null ? "" : p.category;
                String priceRange = p.priceRange == null ? "" : p.priceRange;
                writer.write(p.productId + "," + name + "," + priceStr + "," + category + "," + priceRange);
                writer.newLine();
            }
        }
    }

    /**
     * Print run summary: rows read, transformed, skipped, and output path.
     */
    private static void printSummary(int rowsRead, int rowsTransformed, int rowsSkipped, Path outputPath) {
        System.out.println("Run Summary:");
        System.out.println("  Rows read: " + rowsRead);
        System.out.println("  Rows transformed: " + rowsTransformed);
        System.out.println("  Rows skipped: " + rowsSkipped);
        System.out.println("  Output path: " + outputPath.toString());
    }
}
