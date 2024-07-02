package OOPS.AnonymousClass;


/**
 * Example of Anonymous Class with Interface having Multiple Methods in Java:

 * Purpose:
 * Useful for creating quick implementations of interfaces without the need
 * for separate class definitions.

 * Explanation within main function:

 * - An instance of the Calculator interface is created using an anonymous class.
 * - Inside the anonymous class, implementations for add, subtract, and multiply methods are provided.
 * - Operations (add, subtract, multiply) are performed using methods implemented in the anonymous class (calculator object).
 * - Results of each operation (sum, difference, product) are displayed using System.out.println.
 */

// Interface defining multiple behaviors
interface Calculator {
    int add(int a, int b);
    int subtract(int a, int b);
    int multiply(int a, int b);
}

public class AnonymousClass {
    public static void main(String[] args) {
        // Anonymous Class with Interface Implementation
        Calculator calculator = new Calculator() {
            // Implementing add method of Calculator interface
            @Override
            public int add(int a, int b) {
                return a + b;
            }

            // Implementing subtract method of Calculator interface
            @Override
            public int subtract(int a, int b) {
                return a - b;
            }

            // Implementing multiply method of Calculator interface
            @Override
            public int multiply(int a, int b) {
                return a * b;
            }
        };

        // Performing operations using methods implemented in the anonymous class

        // Performing addition operation (10 + 5)
        int sum = calculator.add(10, 5);

        // Performing subtraction operation (10 - 5)
        int difference = calculator.subtract(10, 5);

        // Performing multiplication operation (10 * 5)
        int product = calculator.multiply(10, 5);

        // Outputting results

        // Outputting results of each operation performed using the 'calculator' object.
        System.out.println("Sum: " + sum); // Output: Sum: 15
        System.out.println("Difference: " + difference); // Output: Difference: 5
        System.out.println("Product: " + product); // Output: Product: 50
    }
}
