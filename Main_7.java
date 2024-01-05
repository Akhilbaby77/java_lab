import java.util.ArrayList;
import java.util.List;

// Define a Product class representing a manufacturing product
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

public class Main_7 {

    public static void main(String[] args) {
        // Create a list of products
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Laptop", 1200.00));
        productList.add(new Product("Smartphone", 800.00));
        productList.add(new Product("Tablet", 500.00));
        productList.add(new Product("Desktop", 1500.00));

        // Use lambda expression to filter products based on a condition (e.g., price > 1000.00)
        List<Product> expensiveProducts = filterProducts(productList, product -> product.getPrice() > 1000.00);

        // Display the filtered products
        System.out.println("Expensive Products:");
        expensiveProducts.forEach(System.out::println);
    }

    // Generic method to filter products based on a given condition using a lambda expression
    private static List<Product> filterProducts(List<Product> products, ProductFilterCondition condition) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (condition.test(product)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    // Functional interface representing a condition for filtering products
    @FunctionalInterface
    interface ProductFilterCondition {
        boolean test(Product product);
    }
}
