import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters and setters (not shown for simplicity)

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

public class ManufacturingApp extends JFrame {

    private List<Product> productList;
    private DefaultListModel<Product> listModel;
    private JList<Product> productJList;
    private JTextField nameField, priceField;

    public ManufacturingApp() {
        productList = new ArrayList<>();
        listModel = new DefaultListModel<>();
        productJList = new JList<>(listModel);

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Manufacturing App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components
        JPanel inputPanel = createInputPanel();
        JScrollPane listScrollPane = new JScrollPane(productJList);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(listScrollPane, BorderLayout.CENTER);

        // Set up initial product list (for demonstration purposes)
        productList.add(new Product("Laptop", 1200.00));
        productList.add(new Product("Smartphone", 800.00));
        productList.add(new Product("Tablet", 500.00));
        productList.add(new Product("Desktop", 1500.00));
        updateProductList();

        // Set up JFrame properties
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new FlowLayout());

        nameField = new JTextField(15);
        priceField = new JTextField(8);
        JButton addButton = new JButton("Add Product");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        inputPanel.add(new JLabel("Product Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(addButton);

        return inputPanel;
    }

    private void addProduct() {
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());

        Product newProduct = new Product(name, price);
        productList.add(newProduct);
        updateProductList();

        // Clear input fields after adding a product
        nameField.setText("");
        priceField.setText("");
    }

    private void updateProductList() {
        listModel.clear();
        for (Product product : productList) {
            listModel.addElement(product);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ManufacturingApp();
            }
        });
    }
}
