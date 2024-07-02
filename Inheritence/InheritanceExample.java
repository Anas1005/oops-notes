package OOPS.Inheritence;

/**
 * Inheritance in Java:
 * Inheritance is a key feature of object-oriented programming that allows one class (subclass/child class)
 * to inherit fields and methods from another class (superclass/parent class). It promotes code reuse and
 * establishes a natural hierarchical relationship between classes.
 *
 * Advantages of Inheritance:
 * 1. Code Reuse: Allows the reuse of existing code, reducing redundancy.
 * 2. Method Overriding: Enables a subclass to provide a specific implementation of a method already defined in its superclass.
 * 3. Extensibility: Makes it easier to extend and maintain existing code.
 * 4. Polymorphism: Supports polymorphism, allowing a single interface to represent different underlying forms (data types).

 * Disadvantages of Inheritance:
 * 1. Increased Complexity: Can make the code more complex and harder to understand.
 * 2. Tight Coupling: Creates a tightly coupled system, making it difficult to change the superclass without affecting the subclasses.
 * 3. Inheritance Hierarchy Issues: Deep inheritance hierarchies can be difficult to manage and understand.

 * Types of Inheritance in Java:
 * 1. Single Inheritance: A class inherits from one superclass.
 * 2. Multilevel Inheritance: A class is derived from another derived class.
 * 3. Hierarchical Inheritance: Multiple classes inherit from one superclass.

 * Note: Java does not support multiple inheritance with classes (a class cannot inherit from more than one class)
 * but it does support multiple inheritance with interfaces.

 * Example: Demonstrating basic inheritance in Java.
 */

// Superclass (Parent Class)
class Animal {
    // Instance variables
    private String name;
    private int age;

    // Constructor
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Getter method for age
    public int getAge() {
        return age;
    }

    // Method to display animal's information
    public void displayInfo() {
        System.out.println("Animal Name: " + name + ", Age: " + age);
    }
}

// Subclass (Child Class) that inherits from Animal
class Dog extends Animal {
    // Additional instance variable specific to Dog
    private String breed;

    // Constructor
    public Dog(String name, int age, String breed) {
        // Call to superclass constructor
        super(name, age);
        this.breed = breed;
    }

    // Getter method for breed
    public String getBreed() {
        return breed;
    }

    // Method to display dog's information
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Breed: " + breed);
    }
}

// Main class to demonstrate inheritance
public class InheritanceExample {
    public static void main(String[] args) {
        // Creating an object of Dog class using Dog reference
        // Full access to Dog class members, including inherited members from Animal class
        Dog dog = new Dog("Buddy", 5, "Golden Retriever");
        System.out.println("Using Dog reference:");
        dog.displayInfo();
        System.out.println("Name: " + dog.getName());
        System.out.println("Age: " + dog.getAge());
        System.out.println("Breed: " + dog.getBreed());

        // Creating an object of Dog class using Animal reference
        // Only access to Animal class members, Dog-specific members are not accessible
        Animal animal = new Dog("Max", 3, "Labrador");
        System.out.println("\nUsing Animal reference:");
        animal.displayInfo();
        System.out.println("Name: " + animal.getName());
        System.out.println("Age: " + animal.getAge());
        // The following line would cause a compile-time error because 'breed' is not accessible through Animal reference
        // System.out.println("Breed: " + animal.getBreed());

        // Demonstrating that a Parent class object cannot be assigned to a Child class reference
        // This line would cause a compile-time error because Animal is not necessarily a Dog
        // Dog newDog = new Animal("Rocky", 2);
    }
}
