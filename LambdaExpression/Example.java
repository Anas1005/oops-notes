package OOPS.LambdaExpression;

import java.util.function.*;

/**
 * Lambda expressions in Java represent concise syntax for defining anonymous functions,
 * particularly useful for implementing functional interfaces with a single abstract method (Functional Interface).
 *
 * Example of Functional Interface and Lambda Expressions in Java:
 *
 * Purpose:
 * Demonstrates the use of lambda expressions with functional interfaces to provide
 * concise implementations of abstract methods.
 *
 * Explanation within main function:
 *
 * - A functional interface `MathOperation` is defined with a single abstract method `operation`.
 * - Lambda expressions are used to provide implementations for the abstract method, including
 *   addition, subtraction, multiplication, and division operations.
 * - Each lambda expression demonstrates a different way to implement the `MathOperation` interface.
 * - The `executeOperation` method showcases how lambda expressions can be passed as parameters
 *   to perform arithmetic operations dynamically.
 */

public class Example {

    // Functional interface with a single abstract method
    @FunctionalInterface
    interface MathOperation {
        int operation(int a, int b);
    }

    public static void main(String[] args) {
        // Using Lambda Expressions with Functional Interface

        // Lambda expression for addition operation
        MathOperation addition = (a, b) -> a + b;

        // Lambda expression for subtraction operation
        MathOperation subtraction = (a, b) -> a - b;

        // Lambda expression for multiplication operation
        MathOperation multiplication = (a, b) -> a * b;

        // Lambda expression for division operation (handling division by zero)
        MathOperation division = (a, b) -> {
            if (b != 0) {
                return a / b;
            } else {
                throw new IllegalArgumentException("Division by zero");
            }
        };

        // Using lambda expressions to perform operations

        // Example 1: Addition
        int result1 = executeOperation(addition, 10, 5);
        System.out.println("Addition Result: " + result1); // Output: Addition Result: 15

        // Example 2: Subtraction
        int result2 = executeOperation(subtraction, 10, 5);
        System.out.println("Subtraction Result: " + result2); // Output: Subtraction Result: 5

        // Example 3: Multiplication
        int result3 = executeOperation(multiplication, 10, 5);
        System.out.println("Multiplication Result: " + result3); // Output: Multiplication Result: 50

        // Example 4: Division
        try {
            int result4 = executeOperation(division, 10, 0);
            System.out.println("Division Result: " + result4); // This line won't execute due to exception
        } catch (IllegalArgumentException e) {
            System.out.println("Exception: " + e.getMessage()); // Output: Exception: Division by zero
        }
    }

    // Method that accepts a MathOperation and performs the operation
    private static int executeOperation(MathOperation operation, int a, int b) {
        return operation.operation(a, b);
    }
}