package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * basic password generation algorithm that generates passwords containing
 * digits only (0-9) using java.util.random.
 * 
 * @author final exam - question 1
 */
public class BasicPasswordAlgorithm implements PasswordGenerationAlgorithm {
    
    private static final String DIGITS = "0123456789";
    private Random random;
    
    /**
     * constructs a new basicpasswordalgorithm instance.
     * initializes the random number generator.
     */
    public BasicPasswordAlgorithm() {
        this.random = new Random();
    }
    
    /**
     * generates a password of the specified length containing only digits (0-9).
     * 
     * @param length the desired length of the password
     * @return a password string containing only digits
     */
    @Override
    public String generate(int length) {
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(DIGITS.length());
            char c = DIGITS.charAt(index);
            password.append(c);
        }
        
        return password.toString();
    }
}

