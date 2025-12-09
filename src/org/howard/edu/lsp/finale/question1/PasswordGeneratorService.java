package org.howard.edu.lsp.finale.question1;

/**
 * service class for generating passwords using various algorithms.
 * implements the singleton pattern to ensure only one instance exists,
 * and the strategy pattern to allow swappable password generation algorithms.
 * 
 * @author final exam - question 1
 */
public class PasswordGeneratorService {
    
    /*
     * ====================================================================
     * design pattern documentation
     * ====================================================================
     * 
     * patterns used:
     * 1. singleton pattern
     * 2. strategy pattern
     * 
     * why these patterns were appropriate:
     * 
     * singleton pattern:
     * - the requirement states "only one instance of the service may exist"
     *   and "provide a single shared access point." the singleton pattern
     *   ensures that only one instance of passwordgeneratorservice can be
     *   created and provides a global access point through getinstance().
     *   this prevents multiple instances from being created and ensures
     *   consistent behavior across the application.
     * 
     * strategy pattern:
     * - the requirements call for "support multiple approaches to password
     *   generation," "allow the caller to select the approach at run time,"
     *   "support future expansion of password-generation approaches," and
     *   "make password-generation behavior swappable." the strategy pattern
     *   encapsulates each password generation algorithm (basic, enhanced,
     *   letters) as separate classes implementing a common interface
     *   (passwordgenerationalgorithm). this allows:
     *   - runtime algorithm selection via setalgorithm()
     *   - easy addition of new algorithms without modifying existing code
     *   - swappable behavior through the algorithm field
     *   - separation of concerns - each algorithm is independent
     * 
     * together, these patterns provide a flexible, extensible, and
     * maintainable solution that meets all the specified requirements.
     * ====================================================================
     */
    
    private static PasswordGeneratorService instance;
    private PasswordGenerationAlgorithm algorithm;
    
    /**
     * private constructor to enforce singleton pattern.
     */
    private PasswordGeneratorService() {
        // private constructor prevents instantiation
    }
    
    /**
     * returns the singleton instance of passwordgeneratorservice.
     * creates the instance if it doesn't exist (lazy initialization).
     * 
     * @return the single instance of passwordgeneratorservice
     */
    public static PasswordGeneratorService getInstance() {
        if (instance == null) {
            instance = new PasswordGeneratorService();
        }
        return instance;
    }
    
    /**
     * sets the password generation algorithm to use.
     * 
     * @param name the name of the algorithm ("basic", "enhanced", or "letters")
     */
    public void setAlgorithm(String name) {
        switch (name.toLowerCase()) {
            case "basic":
                this.algorithm = new BasicPasswordAlgorithm();
                break;
            case "enhanced":
                this.algorithm = new EnhancedPasswordAlgorithm();
                break;
            case "letters":
                this.algorithm = new LettersPasswordAlgorithm();
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + name);
        }
    }
    
    /**
     * generates a password of the specified length using the currently
     * selected algorithm.
     * 
     * @param length the desired length of the password
     * @return a password string of the specified length
     * @throws illegalstateexception if no algorithm has been set before calling this method
     */
    public String generatePassword(int length) {
        if (algorithm == null) {
            throw new IllegalStateException("No algorithm has been set. Call setAlgorithm() first.");
        }
        return algorithm.generate(length);
    }
}

