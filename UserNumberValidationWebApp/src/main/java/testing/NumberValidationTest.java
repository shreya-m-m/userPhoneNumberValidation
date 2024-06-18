package testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NumberValidationTest {

    // Mobile number validation function
    public boolean isValidMobileNumber(String number) {
        if (number == null || number.length() != 12) {
            return false;
        }
        // Regular expression to match a valid mobile number
        String regex = "^(?!\\d*(\\d)\\1{10})\\d{12}$"; // Matches exactly 12 digits and checks if all digits are not the same
        return number.matches(regex);
    }

    // Positive test cases
    @Test
    public void testValidMobileNumber() {
        assertTrue(isValidMobileNumber("123456789012"));
        System.out.println("\nPositive Test Cases:");
        System.out.println("Valid Mobile Number:");
        System.out.println("Input: 123456789012");
        System.out.println("Expected Output: Valid");
        System.out.println("Actual Output: Valid");
    }

    // Negative test cases
    @Test
    public void testInvalidMobileNumber() {
        assertFalse(isValidMobileNumber("1234567890AB")); 
        assertFalse(isValidMobileNumber("1234567890123")); 
        assertFalse(isValidMobileNumber("111111111111")); 
        assertFalse(isValidMobileNumber("")); 
        assertFalse(isValidMobileNumber(null)); 
        System.out.println("\nNegative Test Cases:");
        System.out.println("Invalid Mobile Numbers:");
        System.out.println("Input: 1234567890AB");
        System.out.println("Expected Output: Invalid");
        System.out.println("Actual Output: Invalid");
        System.out.println("\nInput: 1234567890123");
        System.out.println("Expected Output: Invalid");
        System.out.println("Actual Output: Invalid");
        System.out.println("\nInput: 111111111111");
        System.out.println("Expected Output: Invalid");
        System.out.println("Actual Output: Invalid");
        System.out.println("\nInput: (empty string)");
        System.out.println("Expected Output: Invalid");
        System.out.println("Actual Output: Invalid");
        System.out.println("\nInput: null");
        System.out.println("Expected Output: Invalid");
        System.out.println("Actual Output: Invalid");
    }
}
