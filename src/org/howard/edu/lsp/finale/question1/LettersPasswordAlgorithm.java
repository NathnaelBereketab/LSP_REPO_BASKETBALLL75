package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * letters-only password generation algorithm that generates passwords containing
 * only letters (a-z, a-z) using java.util.random.
 * 
 * @author final exam - question 1
 */
public class LettersPasswordAlgorithm implements PasswordGenerationAlgorithm {
    
    private static final String LETTERS = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
        "abcdefghijklmnopqrstuvwxyz";
    
    private Random random;
    
    /**
     * constructs a new letterspasswordalgorithm instance.
     * initializes the random number generator.
     */
    public LettersPasswordAlgorithm() {
        this.random = new Random();
    }
    
    /**
     * generates a password of the specified length containing only letters (a-z, a-z).
     * 
     * @param length the desired length of the password
     * @return a password string containing only letters
     */
    @Override
    public String generate(int length) {
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(LETTERS.length());
            char c = LETTERS.charAt(index);
            password.append(c);
        }
        
        return password.toString();
    }
}

