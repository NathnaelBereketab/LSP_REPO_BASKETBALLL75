package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the Extract, Transform, and Load operations for the ETL pipeline.
 * This class demonstrates object-oriented decomposition by separating
 * the ETL logic into distinct methods with clear responsibilities.
 *
 * @author Assignment 3 - Object-Oriented ETL Pipeline
 * @version 1.0
 */
public class ETLProcessor {
    
    /**
     * Extracts product data from the specified CSV file.
     * 
     * @param inputPath the path to the input CSV file
     * @return a list of extracted products
     * @throws IOException if there is an error reading the file
     */
    public List<Product> extract(Path inputPath) throws IOException {
        final List<Product> products = new ArrayList<>();
        
        try (BufferedReader reader = Files.newBufferedReader(inputPath, StandardCharsets.UTF_8)) {
            String header = reader.readLine();
            if (header == null) {
                return products; // emptu file
            }
            
            String line;
            while ((line = reader.readLine()) != null) {
                // skip empty lines to avoid processing blank rows
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                        // split CSV line into fields - expect exactly 4 columns
                        String[] fields = line.split(",", -1);
                        if (fields.length != 4) {
                            // skip malformed rows that don't have the expected column count
                            System.err.println("Warning: Skipping malformed row (expected 4 columns, got " + fields.length + "): " + line);
                            continue;
                        }
                
                Product product = parseProductLine(fields);
                if (product != null) {
                    products.add(product);
                }
            }
        }
        
        return products;
    }
    
    /**
     * Parses a single product line from CSV fields.
     * 
     * @param fields the CSV fields to parse
     * @return a Product object if parsing succeeds, null if malformed
     */
    private Product parseProductLine(String[] fields) {
        try {
            int productId = Integer.parseInt(fields[0].trim());
            String name = fields[1].trim();
            double price = Double.parseDouble(fields[2].trim());
            String category = fields[3].trim();
            
            return new Product(productId, name, price, category);
                } catch (NumberFormatException e) {
                    // skip malformed rows - return null to indicate parsing failure
                    System.err.println("Warning: Skipping row with invalid data format: " + String.join(",", fields));
                    return null;
                }
    }
    
    /**
     * Transforms the list of products according to the specified rules.
     * 
     * @param products the list of products to transform
     * @return a list of transformed products
     */
    public List<Product> transform(List<Product> products) {
        final List<Product> transformed = new ArrayList<>();
        
        for (Product product : products) {
            Product transformedProduct = transformProduct(product);
            transformed.add(transformedProduct);
        }
        
        return transformed;
    }
    
    /**
     * Transforms a single product according to the business rules.
     * Demonstrates polymorphism by using different transformation strategies.
     * 
     * @param product the product to transform
     * @return the transformed product
     */
    private Product transformProduct(Product product) {
        String originalCategory = product.getCategory() == null ? "" : product.getCategory();
        
        // convert name to uppercase
        String newName = product.getName() == null ? null : product.getName().toUpperCase();
        
        // apply 10% discount for Electronics
        double newPrice = product.getPrice();
        if ("Electronics".equalsIgnoreCase(originalCategory)) {
            newPrice = product.getPrice() * 0.9; // apply 10% off
        }
        newPrice = roundToTwoDecimals(newPrice);
        
        // recategorize if final price > 500 and original category was Electronics
        String newCategory = originalCategory;
        if ("Electronics".equalsIgnoreCase(originalCategory) && newPrice > 500.0) {
            newCategory = "Premium Electronics";
        }
        
        // compute PriceRange from final price using polymorphism
        Product transformedProduct = new Product(product.getProductId(), newName, newPrice, newCategory);
        transformedProduct.setPriceRange(transformedProduct.computePriceRange()); // Polymorphic call
        
        return transformedProduct;
    }
    
    /**
     * Rounds a price to two decimal places using half-up rounding.
     * 
     * @param value the value to round
     * @return the rounded value with two decimal places
     */
    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
    
    
    
    /**
     * Loads transformed products to the specified output file.
     * 
     * @param outputPath the path to the output CSV file
     * @param products the list of transformed products to write
     * @throws IOException if there is an error writing the file
     */
    public void load(Path outputPath, List<Product> products) throws IOException {
        // create parent directories if they don't exist
        if (outputPath.getParent() != null) {
            Files.createDirectories(outputPath.getParent());
        }
        
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8)) {
            // write header row
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();
            
            // write product rows
            for (Product product : products) {
                writer.write(product.toString());
                writer.newLine();
            }
        }
    }
}
