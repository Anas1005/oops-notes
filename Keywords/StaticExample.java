package OOPS.Keywords;

/**
 * Purpose: Demonstrates the use of static keyword in various contexts.
 */
public class StaticExample {

    /**
     * Purpose: Static variable is shared among all instances of the class.
     */
    static int staticVariable;

    /**
     * Purpose: Instance variable is unique to each instance of the class.
     */
    int instanceVariable;

    /**
     * Purpose: Static block is used to initialize static variables when the class is first loaded.
     */
    static {
        staticVariable = 10;
        System.out.println("Static block executed. staticVariable = " + staticVariable);
    }

    /**
     * Purpose: Static method belongs to the class and can be called without an instance.
     */
    static void staticMethod() {
        System.out.println("Static method called. staticVariable = " + staticVariable);
        // System.out.println("Instance variable = " + instanceVariable); // This would cause an error
    }

    /**
     * Purpose: Instance method can access both static and instance variables.
     */
    void instanceMethod() {
        System.out.println("Instance method called. staticVariable = " + staticVariable);
        System.out.println("Instance variable = " + instanceVariable);
    }

    /**
     * Purpose: Static nested class can be instantiated without an instance of the outer class.
     */
    static class StaticNestedClass {
        void display() {
            System.out.println("Static nested class method called. staticVariable = " + staticVariable);
        }
    }

    public static void main(String[] args) {
        // Accessing static variable
        StaticExample.staticVariable = 20;
        System.out.println("Main method. staticVariable = " + StaticExample.staticVariable);

        // Calling static method
        StaticExample.staticMethod();

        // Creating an instance of StaticExample
        StaticExample example = new StaticExample();
        example.instanceVariable = 30;
        example.instanceMethod();

        // Creating an instance of static nested class
        StaticNestedClass nestedClass = new StaticNestedClass();
        nestedClass.display();
    }
}
