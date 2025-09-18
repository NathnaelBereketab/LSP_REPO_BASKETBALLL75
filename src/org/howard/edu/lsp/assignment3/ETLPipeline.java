package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * This main ETL Pipeline class coordinates the Extract, Transform, and Load operations.
 * This class uses an ETLProcessor to handle the core operations
 * while managing the overall pipeline flow and error handling.
 *
 * @author Assignment 3 - Object-Oriented ETL Pipeline
 * @version 1.0
 */
public class ETLPipeline {
    
    private final ETLProcessor processor;
    
    /**
     * Constructs a new ETLPipeline with a default ETLProcessor.
     */
    public ETLPipeline() {
        this.processor = new ETLProcessor();
    }
    
    /**
     * Main entry point for the ETL pipeline application.
     * 
     * @param args command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        ETLPipeline pipeline = new ETLPipeline();
        
        Path inputPath = Paths.get("data", "products.csv");
        Path outputPath = Paths.get("data", "transformed_products.csv");
        
        // Check if input file exists
        if (!Files.exists(inputPath)) {
            System.err.println("Error: Input file not found: " + inputPath.toString());
            System.err.println("Ensure you are running from the project root and that data/products.csv exists.");
            return;
        }
        
        try {
            // extract data
            List<Product> extractedProducts = pipeline.processor.extract(inputPath);
            int rowsRead = extractedProducts.size();
            
            // transform data
            List<Product> transformedProducts = pipeline.processor.transform(extractedProducts);
            int rowsTransformed = transformedProducts.size();
            
            // load data
            pipeline.processor.load(outputPath, transformedProducts);
            
            // print summary
            pipeline.printSummary(rowsRead, rowsTransformed, 0, outputPath);
            
        } catch (IOException e) {
            System.err.println("I/O error during ETL run: " + e.getMessage());
        }
    }
    
    /**
     * Prints a summary of the ETL processing results.
     * 
     * @param rowsRead the number of rows read from input
     * @param rowsTransformed the number of rows successfully transformed
     * @param rowsSkipped the number of rows skipped due to errors
     * @param outputPath the path to the output file
     */
    private void printSummary(int rowsRead, int rowsTransformed, int rowsSkipped, Path outputPath) {
        System.out.println("Run Summary:");
        System.out.println("  Rows read: " + rowsRead);
        System.out.println("  Rows transformed: " + rowsTransformed);
        System.out.println("  Rows skipped: " + rowsSkipped);
        System.out.println("  Output path: " + outputPath.toString());
    }
    
    /**
     * Gets the ETL processor used by this pipeline.
     * 
     * @return the ETL processor
     */
    public ETLProcessor getProcessor() {
        return processor;
    }
}
