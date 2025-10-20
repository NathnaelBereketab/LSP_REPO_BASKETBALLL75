package org.howard.edu.lsp.midterm.question2;

/**
 * Main class to demonstrate the AreaCalculator overloaded methods.
 * Shows method overloading vs. using different method names.
 */
public class Main {
    public static void main(String[] args) {
        // Demonstrate all overloaded area methods
        System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 → area = " + AreaCalculator.area(4));
        
        // Demonstrate exception handling
        try {
            AreaCalculator.area(-1.0); // This should throw an exception
            System.out.println("ERROR: Exception was not thrown for invalid radius!");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }
    
    /*
     * Method overloading is better than using different method names (like rectangleArea, circleArea) 
     * because it provides a cleaner, more intuitive API. Users can call area() with different parameters 
     * and the compiler automatically selects the correct method based on the parameter types and count. 
     * This follows the principle of polymorphism and makes the code more maintainable and easier to use.
     */
}
