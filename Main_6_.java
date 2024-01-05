// Generic interface representing a product
interface Product<T> {
    void displayDetails();
}

// Generic class representing a manufacturing process
class ManufacturingProcess<T extends Product<?>> {
    private T product;

    public ManufacturingProcess(T product) {
        this.product = product;
    }

    public void start() {
        System.out.println("Starting manufacturing process...");
        product.displayDetails();
        System.out.println("Manufacturing process completed.");
    }
}

// Concrete implementation of a product
class Car implements Product<String> {
    private String modelName;

    public Car(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public void displayDetails() {
        System.out.println("Car Model: " + modelName);
    }
}

// Concrete implementation of another product
class Smartphone implements Product<String> {
    private String brand;

    public Smartphone(String brand) {
        this.brand = brand;
    }

    @Override
    public void displayDetails() {
        System.out.println("Smartphone Brand: " + brand);
    }
}

public class Main_6_ {
    public static void main(String[] args) {
        // Creating a manufacturing process for a car
        ManufacturingProcess<Car> carManufacturingProcess = new ManufacturingProcess<>(new Car("Sedan"));
        carManufacturingProcess.start();

        // Creating a manufacturing process for a smartphone
        ManufacturingProcess<Smartphone> smartphoneManufacturingProcess = new ManufacturingProcess<>(new Smartphone("Android"));
        smartphoneManufacturingProcess.start();
    }
}
