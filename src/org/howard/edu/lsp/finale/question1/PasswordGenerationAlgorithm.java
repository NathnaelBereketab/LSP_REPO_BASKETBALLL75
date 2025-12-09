package org.howard.edu.lsp.finale.question1;

/**
 * strategy interface for password generation algorithms.
 * this interface defines the contract that all password generation
 * algorithms must implement, allowing for swappable behavior at runtime.
 * 
 * @author final exam - question 1
 */
public interface PasswordGenerationAlgorithm {
    
    /**
     * generates a password of the specified length using the algorithm's
     * specific character set and random number generation approach.
     * 
     * @param length the desired length of the password
     * @return a password string of the specified length
     */
    String generate(int length);
}

