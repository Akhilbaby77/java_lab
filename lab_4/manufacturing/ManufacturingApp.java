// File: manufacturing/ManufacturingApp.java
package manufacturing;

public class ManufacturingApp {
    public static void main(String[] args) {
        ManufacturingFactory manufacturingFactory = new ManufacturingFactory();
        manufacturingFactory.produce();
        manufacturingFactory.assembleProduct();
    }
}