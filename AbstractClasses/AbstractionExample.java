package OOPS.AbstractClasses;

/**
 * Abstraction in Java:
 * Abstraction is the process of hiding the implementation details and showing only the functionality to the user.
 * It helps in reducing programming complexity by hiding unnecessary details.
 *
 * There are two ways to achieve abstraction in Java:
 * 1. Abstract Classes
 * 2. Interfaces
 */

// Abstract class
abstract class Vehicle {
    // Abstract method (does not have a body)
    public abstract void startEngine();

    // Regular method
    public void stopEngine() {
        System.out.println("Engine stopped");
    }
}

// Subclass (inherited from Vehicle)
class Car extends Vehicle {
    // Providing implementation for the abstract method
    @Override
    public void startEngine() {
        System.out.println("Car engine started");
    }
}

// Subclass (inherited from Vehicle)
class Motorcycle extends Vehicle {
    // Providing implementation for the abstract method
    @Override
    public void startEngine() {
        System.out.println("Motorcycle engine started");
    }
}

public class AbstractionExample {
    public static void main(String[] args) {
        // Abstraction Explanation:
        // Abstraction is the process of hiding implementation details and showing only the functionality to the user.
        // Abstract classes in Java cannot be instantiated and are used to declare common characteristics for subclasses.

        // Cannot instantiate an abstract class directly
        // Vehicle myVehicle = new Vehicle(); // This would cause a compilation error

        // Using abstract class reference to point to the object of the concrete subclass
        Vehicle myVehicle;

        // Pointing to a Car object
        // Dynamic Method Dispatch: Reference is of type Vehicle, object is of type Car
        myVehicle = new Car();
        myVehicle.startEngine();  // Output: Car engine started - Car's implementation of startEngine() is called
        myVehicle.stopEngine();   // Output: Engine stopped - Method from abstract class is called

        // Pointing to a Motorcycle object
        // Dynamic Method Dispatch: Reference is of type Vehicle, object is of type Motorcycle
        myVehicle = new Motorcycle();
        myVehicle.startEngine();  // Output: Motorcycle engine started - Motorcycle's implementation of startEngine() is called
        myVehicle.stopEngine();   // Output: Engine stopped - Method from abstract class is called
    }
}
