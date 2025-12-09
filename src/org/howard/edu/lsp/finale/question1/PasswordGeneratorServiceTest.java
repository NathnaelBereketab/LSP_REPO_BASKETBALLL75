package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PasswordGeneratorServiceTest {

    private PasswordGeneratorService service;

    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
    }

    @Test
    public void checkInstanceNotNull() {
        assertNotNull(service, "Service instance should not be null");
    }

    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        // verify that both 'service' (created in @beforeeach) 
        // and 'second' refer to the exact same object in memory. this 
        // test confirms true singleton behavior — not just that the 
        // two objects are equal, but they are the *same 
        // instance* returned by getinstance().
        assertSame(service, second, "Both instances should refer to the exact same object (singleton behavior)");
    }

    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();
        // verify that illegalstateexception is thrown when generatepassword is called
        // before an algorithm has been set
        assertThrows(IllegalStateException.class, () -> {
            s.generatePassword(10);
        }, "Should throw IllegalStateException when generatePassword is called before setAlgorithm");
    }

    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);
        assertNotNull(p, "Password should not be null");
        assertEquals(10, p.length(), "Password should have the correct length");
        assertTrue(p.matches("^[0-9]+$"), "Password should contain only digits (0-9)");
    }

    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);
        assertNotNull(p, "Password should not be null");
        assertEquals(12, p.length(), "Password should have the correct length");
        assertTrue(p.matches("^[A-Za-z0-9]+$"), "Password should contain only A-Z, a-z, and 0-9");
    }

    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);
        assertNotNull(p, "Password should not be null");
        assertEquals(8, p.length(), "Password should have the correct length");
        assertTrue(p.matches("^[A-Za-z]+$"), "Password should contain only letters (A-Z, a-z)");
    }

    @Test
    public void switchingAlgorithmsChangesBehavior() {
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);

        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);

        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);

        // verify correct behavior characteristics of each algorithm
        assertNotNull(p1, "Basic password should not be null");
        assertNotNull(p2, "Letters password should not be null");
        assertNotNull(p3, "Enhanced password should not be null");
        
        assertEquals(10, p1.length(), "Basic password should have correct length");
        assertEquals(10, p2.length(), "Letters password should have correct length");
        assertEquals(10, p3.length(), "Enhanced password should have correct length");
        
        assertTrue(p1.matches("^[0-9]+$"), "Basic password should contain only digits");
        assertTrue(p2.matches("^[A-Za-z]+$"), "Letters password should contain only letters");
        assertTrue(p3.matches("^[A-Za-z0-9]+$"), "Enhanced password should contain A-Z, a-z, and 0-9");
        
        // verify that algorithms produce different behavior
        // (note: there's a small chance they could be the same by random chance, but very unlikely)
        // we can at least verify they follow their respective character set rules
    }
}

