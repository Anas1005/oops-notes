package OOPS.Enacapsulation;

/**
 * Purpose: Demonstrates the concept of encapsulation in Java.
 * Encapsulation is the technique of making the fields in a class private
 * and providing access to the fields via public methods.
 */
public class Person {

    // Private variables - these can only be accessed within this class
    private String name;
    private int age;

    /**
     * Purpose: Public getter method for name
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Purpose: Public setter method for name
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Purpose: Public getter method for age
     * @return the age of the person
     */
    public int getAge() {
        return age;
    }

    /**
     * Purpose: Public setter method for age
     * @param age the age to set, must be positive
     */
    public void setAge(int age) {
        // You can add validation logic here, e.g., age should be positive
        if(age > 0) {
            this.age = age;
        }
    }

    public static void main(String[] args) {
        // Creating an object of the Person class
        Person person = new Person();

        // Setting values using setter methods
        person.setName("John");
        person.setAge(30);

        // Getting values using getter methods
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
    }
}
