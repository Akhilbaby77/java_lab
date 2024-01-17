package org.example;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private JFrame frame;
    private List<Product> productList;

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/lab-10";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Akhil123@";

    public Main() {
        productList = new ArrayList<>();
        initialize();
        createTable();
        loadProductsFromDatabase();
    }

    private void initialize() {
        frame = new JFrame("Manufacturing Information");
        frame.setBounds(100, 100, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setBounds(10, 20, 120, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(140, 20, 165, 25);
        panel.add(nameText);

        JLabel productCodeLabel = new JLabel("Product Code:");
        productCodeLabel.setBounds(10, 60, 120, 25);
        panel.add(productCodeLabel);

        JTextField productCodeText = new JTextField(20);
        productCodeText.setBounds(140, 60, 165, 25);
        panel.add(productCodeText);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(10, 100, 80, 25);
        panel.add(quantityLabel);

        JTextField quantityText = new JTextField(20);
        quantityText.setBounds(140, 100, 165, 25);
        panel.add(quantityText);

        JButton addButton = new JButton("Add Product");
        addButton.setBounds(10, 140, 150, 25);
        panel.add(addButton);

        JButton viewButton = new JButton("View Products");
        viewButton.setBounds(170, 140, 150, 25);
        panel.add(viewButton);

        JButton updateButton = new JButton("Update Quantity");
        updateButton.setBounds(10, 170, 150, 25);
        panel.add(updateButton);

        JButton deleteButton = new JButton("Delete Product");
        deleteButton.setBounds(170, 170, 150, 25);
        panel.add(deleteButton);

        JTextArea manufacturingTextArea = new JTextArea();
        manufacturingTextArea.setBounds(10, 200, 350, 120);
        panel.add(manufacturingTextArea);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                String productCode = productCodeText.getText();
                int quantity = Integer.parseInt(quantityText.getText());

                Product product = new Product(name, productCode, quantity);
                productList.add(product);

                insertIntoDatabase(product);

                manufacturingTextArea.setText("");
                for (Product prod : productList) {
                    manufacturingTextArea.append(prod.toString() + "\n");
                }

                nameText.setText("");
                productCodeText.setText("");
                quantityText.setText("");
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manufacturingTextArea.setText("");
                for (Product prod : productList) {
                    manufacturingTextArea.append(prod.toString() + "\n");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productCode = productCodeText.getText();
                int newQuantity = Integer.parseInt(quantityText.getText());

                updateQuantity(productCode, newQuantity);

                manufacturingTextArea.setText("");
                for (Product prod : productList) {
                    manufacturingTextArea.append(prod.toString() + "\n");
                }

                productCodeText.setText("");
                quantityText.setText("");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productCode = productCodeText.getText();

                deleteProduct(productCode);

                manufacturingTextArea.setText("");
                for (Product prod : productList) {
                    manufacturingTextArea.append(prod.toString() + "\n");
                }

                productCodeText.setText("");
                quantityText.setText("");
            }
        });
    }

    private void createTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Products (" +
                    "id SERIAL PRIMARY KEY," +
                    "name TEXT," +
                    "product_code TEXT," +
                    "quantity INT)";
            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertIntoDatabase(Product product) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Products (name, product_code, quantity) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getProductCode());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadProductsFromDatabase() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Products")) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String productCode = resultSet.getString("product_code");
                int quantity = resultSet.getInt("quantity");

                Product product = new Product(name, productCode, quantity);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateQuantity(String productCode, int newQuantity) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Products SET quantity = ? WHERE product_code = ?")) {
            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setString(2, productCode);
            preparedStatement.executeUpdate();

            for (Product product : productList) {
                if (product.getProductCode().equals(productCode)) {
                    product.setQuantity(newQuantity);
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct(String productCode) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM Products WHERE product_code = ?")) {
            preparedStatement.setString(1, productCode);
            preparedStatement.executeUpdate();

            productList.removeIf(product -> product.getProductCode().equals(productCode));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }
}

class Product {
    private String name;
    private String productCode;
    private int quantity;

    public Product(String name, String productCode, int quantity) {
        this.name = name;
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getProductCode() {
        return productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product Name: " + name +
                ", Product Code: " + productCode + ", Quantity: " + quantity;
    }
}
