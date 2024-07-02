package OOPS.Constructors;

/**
 * Purpose: Demonstrates different types of constructors in Java.
 * Constructors are special methods used for initializing objects. They have the same name as the class
 * and do not have a return type.
 */
public class Car {

    private String brand;
    private String model;
    private int year;

    /**
     * Purpose: Default constructor (no parameters).
     * Initializes the object with default values.
     */
    public Car() {
        this.brand = "Unknown";
        this.model = "Unknown";
        this.year = 0;
    }

    /**
     * Purpose: Parameterized constructor with three parameters.
     * Initializes the object with the provided values.
     * @param brand the brand of the car
     * @param model the model of the car
     * @param year the year of manufacture
     */
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    /**
     * Purpose: Copy constructor.
     * Creates a new Car object with the same state as the provided Car object.
     * @param anotherCar the Car object to copy
     */
    public Car(Car anotherCar) {
        this.brand = anotherCar.brand;
        this.model = anotherCar.model;
        this.year = anotherCar.year;
    }

    /**
     * Purpose: Getter method for the brand of the car.
     * @return the brand of the car
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Purpose: Getter method for the model of the car.
     * @return the model of the car
     */
    public String getModel() {
        return model;
    }

    /**
     * Purpose: Getter method for the year of manufacture of the car.
     * @return the year of manufacture of the car
     */
    public int getYear() {
        return year;
    }

    public static void main(String[] args) {
        // Creating objects using different constructors
        Car car1 = new Car(); // Default constructor
        Car car2 = new Car("Toyota", "Camry", 2022); // Parameterized constructor
        Car car3 = new Car(car2); // Copy constructor

        // Displaying information about each car
        System.out.println("Car 1 - Brand: " + car1.getBrand() + ", Model: " + car1.getModel() + ", Year: " + car1.getYear());
        System.out.println("Car 2 - Brand: " + car2.getBrand() + ", Model: " + car2.getModel() + ", Year: " + car2.getYear());
        System.out.println("Car 3 - Brand: " + car3.getBrand() + ", Model: " + car3.getModel() + ", Year: " + car3.getYear());
    }
}

