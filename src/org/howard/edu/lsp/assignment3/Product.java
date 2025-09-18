package org.howard.edu.lsp.assignment3;

/**
 * Base class for all product types.
 * Demonstrates inheritance by providing common product functionality.
 *
 * @author Assignment 3 - Object-Oriented ETL Pipeline
 * @version 1.0
 */
abstract class BaseProduct {
    private int productId;
    private String name;
    private double price;
    private String category;
    
    /**
     * Gets the product ID.
     * 
     * @return the product ID
     */
    public int getProductId() {
        return productId;
    }
    
    /**
     * Gets the product name.
     * 
     * @return the product name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the product price.
     * 
     * @return the product price
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Gets the product category.
     * 
     * @return the product category
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * sets the product ID.
     * 
     * @param productId the new product ID
     */
    protected void setProductId(int productId) {
        this.productId = productId;
    }
    
    /**
     * sets the product name.
     * 
     * @param name the new product name
     */
    protected void setName(String name) {
        this.name = name;
    }
    
    /**
     * sets the product price.
     * 
     * @param price the new product price
     */
    protected void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * sets the product category.
     * 
     * @param category the new product category
     */
    protected void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * abstract method for computing price range - demonstrates polymorphism.
     * 
     * @return the price range string
     */
    public abstract String computePriceRange();
}

/**
 * represents a product with its attributes and computed price range.
 * This class demonstrates inheritance by extending BaseProduct and
 * encapsulation by using private fields with public getter and setter methods.
 * 
 */
public class Product extends BaseProduct {
    
    private String priceRange;
    
    /**
     * constructs a new Product with the specified attributes.
     * 
     * @param productId the unique identifier for the product
     * @param name the name of the product
     * @param price the price of the product
     * @param category the category of the product
     */
    public Product(int productId, String name, double price, String category) {
        setProductId(productId);
        setName(name);
        setPrice(price);
        setCategory(category);
        this.priceRange = ""; // Will be computed during transformation
    }
    
    
    /**
     * Implements the abstract method from BaseProduct.
     * Demonstrates polymorphism by providing specific implementation.
     * 
     * @return the computed price range
     */
    @Override
    public String computePriceRange() {
        if (getPrice() <= 10.00) {
            return "Low";
        } else if (getPrice() <= 100.00) {
            return "Medium";
        } else if (getPrice() <= 500.00) {
            return "High";
        } else {
            return "Premium";
        }
    }
    
    /**
     * Gets the computed price range.
     * 
     * @return the price range (Low, Medium, High, Premium)
     */
    public String getPriceRange() {
        return priceRange;
    }
    
    /**
     * Sets the computed price range.
     * 
     * @param priceRange the price range (Low, Medium, High, Premium)
     */
    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }
    
    /**
     * Returns a string representation of the product in CSV format.
     * 
     * @return CSV-formatted string representation
     */
    @Override
    public String toString() {
        String priceStr = String.format(java.util.Locale.US, "%.2f", getPrice());
        String nameStr = getName() == null ? "" : getName();
        String categoryStr = getCategory() == null ? "" : getCategory();
        String priceRangeStr = priceRange == null ? "" : priceRange;
        
        return getProductId() + "," + nameStr + "," + priceStr + "," + categoryStr + "," + priceRangeStr;
    }
}
