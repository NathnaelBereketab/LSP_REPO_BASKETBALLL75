package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * enhanced password generation algorithm that generates passwords containing
 * uppercase letters (a-z), lowercase letters (a-z), and digits (0-9)
 * using java.security.securerandom for cryptographically secure randomness.
 * 
 * @author final exam - question 1
 */
public class EnhancedPasswordAlgorithm implements PasswordGenerationAlgorithm {
    
    private static final String ALLOWED_CHARACTERS = 
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
        "abcdefghijklmnopqrstuvwxyz" +
        "0123456789";
    
    private SecureRandom secureRandom;
    
    /**
     * constructs a new enhancedpasswordalgorithm instance.
     * initializes the securerandom number generator.
     */
    public EnhancedPasswordAlgorithm() {
        this.secureRandom = new SecureRandom();
    }
    
    /**
     * generates a password of the specified length containing uppercase letters,
     * lowercase letters, and digits.
     * 
     * @param length the desired length of the password
     * @return a password string containing a-z, a-z, and 0-9
     */
    @Override
    public String generate(int length) {
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(ALLOWED_CHARACTERS.length());
            char c = ALLOWED_CHARACTERS.charAt(index);
            password.append(c);
        }
        
        return password.toString();
    }
}

