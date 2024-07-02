package OOPS.ObjectCloning;

/*
 * Shallow Clone vs Deep Clone in Java
 *
 * Cloning in Java is a mechanism to create a copy of an object. It can be
 * achieved by implementing the Cloneable interface and overriding the clone() method.
 * There are two types of cloning: shallow cloning and deep cloning.
 *
 * 1. Shallow Clone:
 *    - Shallow cloning creates a copy of the object, bPrimitive Fields: The values of primitive fields are copied exactly.
Reference Fields: The references of the reference fields (objects) are copied, not the objects themselves. Therefore, both the original and the cloned object will refer to the same nested objects.
 *
 * 2. Deep Clone:
 *    - Deep cloning creates a fully independent copy of the entire object graph,
 *      including all nested objects.
 *    - Changes to nested objects in the cloned object do not affect the original object.
 *    - Use Case: Necessary when the object contains mutable nested objects(Non Primitives)that should
 *      be independently modifiable.
 *
 * Example:
 * Below is an example demonstrating both shallow and deep cloning in Java using a
 * simple Person and Address class.
 */

class Address implements Cloneable {
    String city;

    public Address(String city) {
        this.city = city;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Person implements Cloneable {
    String name;
    Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Shallow clone: The default clone() implementation

    protected Person shallowClone() throws CloneNotSupportedException {
        return (Person) super.clone();
    }

    // Deep clone: Custom clone() implementation to clone nested objects
    public Person deepClone() throws CloneNotSupportedException {
        Person cloned = (Person) super.clone(); // Shallow Clone....
        cloned.address = (Address) address.clone(); // Perform deep clone of the address
        return cloned;
    }
}

public class Example {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address address = new Address("New York");
        Person person1 = new Person("John", address);

        // Perform shallow clone
        Person person2 = person1.shallowClone();
        

        // Print original and cloned object details
        System.out.println("Shallow Clone Example:");
        System.out.println("Original Person: " + person1.name + ", " + person1.address.city);
        System.out.println("Cloned Person: " + person2.name + ", " + person2.address.city);

        // Modify the address of the cloned object
        person2.address.city = "San Francisco";

        // Print again to show that the address change affects both objects
        System.out.println("After modification:");
        System.out.println("Original Person: " + person1.name + ", " + person1.address.city);
        System.out.println("Cloned Person: " + person2.name + ", " + person2.address.city);

        // Perform deep clone
        Person person3 = person1.deepClone();

        // Modify the address of the deeply cloned object
        person3.address.city = "Los Angeles";

        // Print original and deeply cloned object details to show they are independent
        System.out.println("Deep Clone Example:");
        System.out.println("Original Person: " + person1.name + ", " + person1.address.city);
        System.out.println("Deep Cloned Person: " + person3.name + ", " + person3.address.city);
    }
}
