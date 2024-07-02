package OOPS.Polymorphism;

/**
 * Polymorphism in Java:
 * Polymorphism is one of the core concepts of object-oriented programming (OOP) that allows methods to do different things
 * based on the object it is acting upon. Polymorphism is of two types:
 *
 * 1. Compile-time Polymorphism (Method Overloading): Method overloading allows a class to have more than one method
 *    with the same name, but different parameters.
 *
 * 2. Runtime Polymorphism (Method Overriding): Method overriding allows a subclass to provide a specific implementation
 *    of a method that is already defined in its superclass. This is achieved through inheritance.
 *
 * Dynamic Method Dispatch:
 * Dynamic method dispatch is the mechanism by which a call to an overridden method is resolved at runtime rather than
 * compile-time. It is achieved through method overriding and is used to support runtime polymorphism.
 */

// Compile-time Polymorphism (Method Overloading)
class Arithmetic {
    // Method to add two integers
    public int add(int a, int b) {
        return a + b;
    }

    // Method to add three integers
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // Method to add two double values
    public double add(double a, double b) {
        return a + b;
    }
}

// Runtime Polymorphism (Method Overriding)
class Animal {
    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow");
    }
}
public class PolymorphismExample {
    public static void main(String[] args) {
        // Compile-time Polymorphism (Method Overloading)
        Arithmetic arithmetic = new Arithmetic();
        System.out.println("Sum of 2 and 3: " + arithmetic.add(2, 3));  // Calls add(int a, int b)
        System.out.println("Sum of 2, 3, and 4: " + arithmetic.add(2, 3, 4));  // Calls add(int a, int b, int c)
        System.out.println("Sum of 2.5 and 3.5: " + arithmetic.add(2.5, 3.5));  // Calls add(double a, double b)

        // Runtime Polymorphism (Method Overriding)
        Animal myAnimal;

        // Dynamic Method Dispatch: Reference is of type Animal, object is of type Dog
        myAnimal = new Dog();
        myAnimal.makeSound();  // Output: Bark - Dog's makeSound() is called at runtime

        // Dynamic Method Dispatch: Reference is of type Animal, object is of type Cat
        myAnimal = new Cat();
        myAnimal.makeSound();  // Output: Meow - Cat's makeSound() is called at runtime

        // Demonstrating runtime polymorphism through an array of Animal references
        Animal[] animals = { new Dog(), new Cat() };
        for (Animal animal : animals) {
            animal.makeSound();  // Output: Bark, Meow - Each object's makeSound() is called at runtime
        }
    }


}