package OOPS.ExceptionHandling;

/**
 * Example of Different Types of Exceptions in Java:
 *
 * Purpose:
 * Demonstrates various types of exceptions including checked, unchecked, and custom exceptions.
 *
 * Explanation within main function:
 *
 * - `divide`: Throws `ArithmeticException` (unchecked) for division by zero.
 * - `readFile`: Throws `FileNotFoundException` (checked) when trying to read a non-existent file.
 * - `parseNumber`: Demonstrates `NumberFormatException` (unchecked) for invalid parsing of a string.
 * - Custom exception `InvalidAgeException` is used to handle cases where age is less than 18.
 */

import java.io.*;

public class Example {

    // Method that throws an unchecked exception (ArithmeticException)
    public static double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return (double) a / b;
    }

    // Method that throws a checked exception (FileNotFoundException)
    public static void readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file); // May throw FileNotFoundException
        BufferedReader br = new BufferedReader(fr);
        // Reading lines from file (not implemented for brevity)
    }

    // Method that demonstrates NumberFormatException (unchecked)
    public static int parseNumber(String str) {
        return Integer.parseInt(str); // May throw NumberFormatException
    }

    // Custom checked exception class
    static class InvalidAgeException extends Exception {
        public InvalidAgeException(String message) {
            super(message);
        }
    }

    // Method that validates age and throws custom checked exception
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or above");
        }
    }

    public static void main(String[] args) {
        // Example of handling unchecked exception (ArithmeticException)
        try {
            double result = divide(10, 0); // Attempting division by zero
            System.out.println("Result: " + result); // This line won't execute due to exception
        } catch (ArithmeticException e) {
            System.out.println("Unchecked Exception caught: " + e.getMessage()); // Output: Unchecked Exception caught: Division by zero
        }

        // Example of handling checked exception (FileNotFoundException)
        try {
            readFile("nonexistentfile.txt"); // Attempting to read a non-existent file
        } catch (FileNotFoundException e) {
            System.out.println("Checked Exception caught: " + e.getMessage()); // Output: Checked Exception caught: nonexistentfile.txt (or similar)
        }

        // Example of handling unchecked exception (NumberFormatException)
        try {
            int number = parseNumber("abc"); // Attempting to parse a non-numeric string
            System.out.println("Parsed Number: " + number); // This line won't execute due to exception
        } catch (NumberFormatException e) {
            System.out.println("Unchecked Exception caught: " + e.getMessage()); // Output: Unchecked Exception caught: For input string: "abc"
        }

        // Example of custom exception handling with user-defined exceptions
        try {
            validateAge(15); // Method that checks age and throws custom exception
        } catch (InvalidAgeException e) {
            System.out.println("Custom Exception caught: " + e.getMessage()); // Output: Custom Exception caught: Age must be 18 or above
        }
    }
}
