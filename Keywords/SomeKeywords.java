package OOPS.Keywords;

/**
 * Example demonstrating various Java keywords:
 *
 * - `final`: Used to define constants, prevent method overriding, and inheritance.
 * - `finally`: Used to execute code after try-catch block, regardless of whether an exception occurred.
 * - `finalize`: A method that Java calls before an object is garbage collected.
 * - `static`: Used for class-level methods and variables.
 * - `super`: Refers to the superclass and is used to call superclass methods and constructors.
 * - `this`: Refers to the current object and is used to call instance variables, methods, and constructors.
 */

class Example {
    // final variable
    public static final int MAX_VALUE = 100;

    // static variable
    public static int count = 0;

    // instance variable
    private int id;

    // Constructor using `this`
    public Example(int id) {
        this.id = id;
    }

    // final method
    public final void displayId() {
        System.out.println("ID: " + this.id);
    }

    // static method
    public static void incrementCount() {
        count++;
    }

    // Method that demonstrates finally block
    public void divide(int a, int b) {
        try {
            int result = a / b;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero.");
        } finally {
            System.out.println("This block is always executed.");
        }
    }

    // finalize method
    @Override
    protected void finalize() throws Throwable {
        try {
            System.out.println("finalize method called.");
        } finally {
            super.finalize();
        }
    }
}

// Superclass
class Parent {
    public void show() {
        System.out.println("Parent class method.");
    }
}

// Subclass using `super`
class Child extends Parent {
    public void show() {
        super.show(); // calls Parent's show method
        System.out.println("Child class method.");
    }
}

public class SomeKeywords {
    public static void main(String[] args) {
        // Using `static` and `final`
        System.out.println("Max Value: " + Example.MAX_VALUE);

        // Creating an object of Example class
        Example obj1 = new Example(1);
        obj1.displayId();

        // Using `this`
        Example obj2 = new Example(2);
        obj2.displayId();

        // Using static method
        Example.incrementCount();
        System.out.println("Count after increment: " + Example.count);

        // Using finally block
        obj1.divide(10, 0);

        // Demonstrating `super`
        Child child = new Child();
        child.show();

        // Triggering garbage collection to call finalize
        obj1 = null;
        obj2 = null;
        System.gc();

        // Assert keyword usage (to enable, run with -ea option)
        int value = 10;
        assert value > 5 : "Value should be greater than 5";
        System.out.println("Assert passed.");
    }
}
