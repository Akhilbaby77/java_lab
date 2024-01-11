import java.util.*;

class Product {
    private int id;
    private String name;
    private int quantity;

    public Product(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', quantity=" + quantity + '}';
    }
}

public class Main_lab8 {
    public static void main(String[] args) {
        // Using ArrayList to store a list of products
        List<Product> productList = new ArrayList<>();

        // Using HashMap to store products with their IDs as keys
        Map<Integer, Product> productMap = new HashMap<>();

        // Using HashSet to store unique product names
        Set<String> uniqueProductNames = new HashSet<>();

        // Adding products to the ArrayList, HashMap, and HashSet
        productList.add(new Product(1, "Chair", 100));
        productList.add(new Product(2, "Table ", 150));
        productList.add(new Product(3, "bed", 75));

        for (Product product : productList) {
            productMap.put(product.getId(), product);
            uniqueProductNames.add(product.getName());
        }

        // Displaying the list of products using ArrayList
        System.out.println("List of Products:");
        for (Product product : productList) {
            System.out.println(product);
        }

        // Displaying product details using HashMap with product IDs
        System.out.println("\nProduct Details using HashMap (ID-based lookup):");
        for (int productId : productMap.keySet()) {
            Product product = productMap.get(productId);
            System.out.println(product);
        }

        // Displaying unique product names using HashSet
        System.out.println("\nUnique Product Names:");
        for (String productName : uniqueProductNames) {
            System.out.println(productName);
        }
    }
}
