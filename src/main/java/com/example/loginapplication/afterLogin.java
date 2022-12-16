package com.example.loginapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.StringJoiner;

public class afterLogin/* implements Initializable*/ {

    public ObservableList<Sales> salesData;
    public ObservableList<Customers> customersData;
    public ObservableList<Cars> carsData;
    public ObservableList<CustomerSales> customerSalesData;

    public afterLogin() throws SQLException {
        this.salesData = FXCollections.observableArrayList();
        this.customersData = FXCollections.observableArrayList();
        this.carsData = FXCollections.observableArrayList();
        this.customerSalesData = FXCollections.observableArrayList();
    }

    @FXML
    private TableView tableView;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField maxIntField;

    @FXML
    private Label insertSuccessLabel;

    @FXML
    private TextField cusCustomerIdField;

    @FXML
    private Label warningLabel;

    @FXML
    private TextField yearField;

    @FXML
    private TextField vinField;

    @FXML
    private TextField salesCustomerField;

    @FXML
    private TextField salesIdField;

    @FXML
    private TextField salesVinField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField milesField;

    @FXML
    private TextField makeField;

    @FXML
    private TextField intrateField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField minIntField;

    @FXML
    private Button buttonInnerJoin1;

    @FXML
    private Button carsButton;

    @FXML
    private Button customerInsertButton;

    @FXML
    private Button carInsertButton;

    @FXML
    private Button saleInsertButton;

    @FXML
    private Button customersButton;

    @FXML
    private Button salesButton;

    @FXML
    private Button customerSearchButton;

    @FXML
    private Button carSearchButton;

    @FXML
    private Button saleSearchButton;

    @FXML
    private Button goBackButton;

    /*@FXML
    private Button intSearchButton;*/

    @FXML
    void checkCustomerFields(ActionEvent event) throws SQLException {
        warningLabel.setText("");
        if((firstNameField.getText().isBlank() && lastNameField.getText().isBlank()) && (cusCustomerIdField.getText().isBlank() && addressField.getText().isBlank()))  {
            warningLabel.setText("Please enter data before searching!");
        } else {
            tableView.getItems().clear();
            tableView.getColumns().clear();
            searchName();
        }
    }

    @FXML
    void checkCarFields(ActionEvent event) throws SQLException {
        warningLabel.setText("");
        if(vinField.getText().isBlank() && makeField.getText().isBlank() && (modelField.getText().isBlank() && yearField.getText().isBlank()) && (milesField.getText().isBlank())) {
            warningLabel.setText("Please enter data before searching!");
        } else {
            tableView.getItems().clear();
            tableView.getColumns().clear();
            searchCars();
        }
    }

    @FXML
    void checkSaleFields(ActionEvent event) throws SQLException {
        warningLabel.setText("");
        if ((salesIdField.getText().isBlank() && priceField.getText().isBlank()) && (minIntField.getText().isBlank() && salesVinField.getText().isBlank()) && (salesCustomerField.getText().isBlank()) && (maxIntField.getText().isBlank() && intrateField.getText().isBlank())) {
            warningLabel.setText("Please enter data before searching!");
        } else if (!intrateField.getText().isBlank() && (!minIntField.getText().isBlank() || !maxIntField.getText().isBlank())) {
            warningLabel.setText("Please enter one interest rate or a range!");
        } else {
            tableView.getItems().clear();
            tableView.getColumns().clear();
            searchSales();
        }
    }

    /*@FXML
    void checkInterestFields(ActionEvent event) throws SQLException {
        warningLabel.setText("");
        if((minIntField.getText().isBlank() && maxIntField.getText().isBlank())) {
            warningLabel.setText("Please enter data before searching!");
        } else {
            tableView.getItems().clear();
            tableView.getColumns().clear();
            searchIntRate();
        }
    }*/

    @FXML
    void goBack(ActionEvent event) throws IOException {
        App n = new App();
        n.changeScene("DatabaseSQLite.fxml");
    }

    @FXML
    void searchName() throws SQLException {
        Connection connection = App.loadJDBC();
        String sql = "SELECT * FROM Customers WHERE ";

        StringJoiner sj = new StringJoiner(" AND ");
        if (!cusCustomerIdField.getText().isBlank()) {
            sj.add("cus_id = ?");
        }
        if (!firstNameField.getText().isBlank()) {
            sj.add("cus_fname LIKE '%'||?||'%'");
        }
        if (!lastNameField.getText().isBlank()) {
            sj.add("cus_lname LIKE '%'||?||'%'");
        }
        if (!addressField.getText().isBlank()) {
            sj.add("cus_address LIKE '%'||?||'%'");
        }
        sql += sj.toString();

        PreparedStatement pstmt = connection.prepareStatement(sql);
        int index = 1;
        if (!cusCustomerIdField.getText().isBlank()) {
            pstmt.setInt(index, Integer.parseInt(cusCustomerIdField.getText()));
            index++;
        }
        if (!firstNameField.getText().isBlank()) {
            pstmt.setString(index, firstNameField.getText());
            index++;
        }
        if (!lastNameField.getText().isBlank()) {
            pstmt.setString(index, lastNameField.getText());
            index++;
        }
        if (!addressField.getText().isBlank()) {
            pstmt.setString(index, addressField.getText());
            index++;
        }
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Customers customers;
            customers = new Customers(rs.getInt("cus_id"), rs.getString("cus_fname"), rs.getString("cus_lname"), rs.getString("cus_address"));
            System.out.println(customers.getCusID() + " - " + customers.getFirstName() + " - " + customers.getLastName() + " - " + customers.getAddress());
            customersData.add(customers);
        }
        initializeCustomerColumns();
        rs.close();
    }

    @FXML
    void searchCars() throws SQLException {
        Connection connection = App.loadJDBC();
        String sql = "SELECT * FROM Cars WHERE ";

        StringJoiner sj = new StringJoiner(" AND ");
        if (!vinField.getText().isBlank()) {
            sj.add("cars_vin LIKE '%'||?||'%'");
        }
        if (!makeField.getText().isBlank()) {
            sj.add("cars_make LIKE '%'||?||'%'");
        }
        if (!modelField.getText().isBlank()) {
            sj.add("cars_model LIKE '%'||?||'%'");
        }
        if (!yearField.getText().isBlank()) {
            sj.add("cars_year LIKE '%'||?||'%'");
        }
        if (!milesField.getText().isBlank()) {
            sj.add("cus_address LIKE '%'||?||'%'");
        }
        sql += sj.toString();

        PreparedStatement pstmt = connection.prepareStatement(sql);
        int index = 1;
        if (!vinField.getText().isBlank()) {
            pstmt.setString(index, vinField.getText());
            System.out.println(vinField.getText());
            index++;
        }
        if (!makeField.getText().isBlank()) {
            pstmt.setString(index, makeField.getText());
            System.out.println(makeField.getText());
            index++;
        }
        if (!modelField.getText().isBlank()) {
            pstmt.setString(index, modelField.getText());
            System.out.println(modelField.getText());
            index++;
        }
        if (!yearField.getText().isBlank()) {
            pstmt.setInt(index, Integer.parseInt(yearField.getText()));
            System.out.println(yearField.getText());
            index++;
        }
        if (!milesField.getText().isBlank()) {
            pstmt.setInt(index, Integer.parseInt(milesField.getText()));
            System.out.println(milesField.getText());
            index++;
        }
        System.out.println(pstmt);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Cars cars = new Cars(rs.getString("cars_vin"), rs.getString("cars_make"), rs.getString("cars_model"), rs.getInt("cars_year"), rs.getInt("cars_miles"));
            System.out.println(cars.getCarVin() + " - " + cars.getMake() + " - " + cars.getModel() + " - " + cars.getYear() + " - " + cars.getMiles());
            carsData.add(cars);
        }
        initializeCarsColumns();
        rs.close();
    }

    @FXML
    void searchSales() throws SQLException {
        Connection connection = App.loadJDBC();
        String sql = "SELECT * FROM Sales WHERE ";

        StringJoiner sj = new StringJoiner(" AND ");
        if (!salesIdField.getText().isBlank()) {
            sj.add("sales_id = ?");
        }
        if (!priceField.getText().isBlank()) {
            sj.add("sales_price LIKE '%'||?||'%'");
        }
        if ((!minIntField.getText().isBlank()) && (!maxIntField.getText().isBlank())) {
            sj.add("sales_intrate BETWEEN ? AND ?");
        }
        if (!intrateField.getText().isBlank()) {
            sj.add("sales_intrate = ?");
        }
        if (!salesVinField.getText().isBlank()) {
            sj.add("cars_vin LIKE '%'||?||'%'");
        }
        if (!salesCustomerField.getText().isBlank()) {
            sj.add("cus_id LIKE '%'||?||'%'");
        }
        sql += sj.toString();

        PreparedStatement pstmt = connection.prepareStatement(sql);
        int index = 1;
        if (!salesIdField.getText().isBlank()) {
            pstmt.setInt(index, Integer.parseInt(salesIdField.getText()));
            index++;
        }
        if (!priceField.getText().isBlank()) {
            pstmt.setString(index, priceField.getText());
            index++;
        }
        if (!minIntField.getText().isBlank()) {
            pstmt.setString(index, minIntField.getText());
            index++;
        }
        if (!maxIntField.getText().isBlank()) {
            pstmt.setString(index, maxIntField.getText());
            index++;
        }
        if (!intrateField.getText().isBlank()) {
            pstmt.setString(index, intrateField.getText());
            index++;
        }
        if (!salesVinField.getText().isBlank()) {
            pstmt.setString(index, salesVinField.getText());
            index++;
        }
        if (!salesCustomerField.getText().isBlank()) {
            pstmt.setString(index, salesCustomerField.getText());
            index++;
        }
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Sales sales = new Sales(rs.getInt("sales_id"), rs.getInt("sales_price"), rs.getInt("sales_intrate"), rs.getString("cars_vin"), rs.getInt("cus_id"));
            System.out.println(sales.getSalesID() + " - " + sales.getSalesPrice() + " - " + sales.getSalesInterest() + " - " + sales.getCarVin() + " - " + sales.getCusID());
            salesData.add(sales);
        }
        initializeSalesColumns();
        rs.close();
    }

    /*@FXML
    void searchIntRate() throws SQLException {
        Connection connection = App.loadJDBC();
        String sql = "SELECT * FROM Sales WHERE sales_intrate BETWEEN ? AND ?;";

        StringJoiner sj = new StringJoiner(" AND ");
        if (!minIntField.getText().equals("")) {
            sj.add("sales_id = ?");
        }
        if (!maxIntField.getText().equals("")) {
            sj.add("sales_id = ?");
        }
        sql += sj.toString();

        PreparedStatement pstmt = connection.prepareStatement(sql);
        int index = 1;
        if (!minIntField.getText().equals("")) {
            pstmt.setInt(index, Integer.parseInt(minIntField.getText()));
            index++;
        }
        if (!maxIntField.getText().equals("")) {
            pstmt.setInt(index, Integer.parseInt(maxIntField.getText()));
            index++;
        }
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Sales sales;
            sales = new Sales(rs.getInt("sales_id"), rs.getInt("sales_price"), rs.getInt("sales_intrate"), rs.getString("cars_vin"), rs.getInt("cus_id"));
            System.out.println(sales.getSalesID() + " - " + sales.getSalesPrice() + " - " + sales.getSalesInterest() + " - " + sales.getCarVin() + " - " + sales.getCusID());
            salesData.add(sales);
        }
        initializeSalesColumns();
        rs.close();
    }*/


    public void loadCars() throws SQLException {
        tableView.getItems().clear();
        tableView.getColumns().clear();
        Connection connection = App.loadJDBC();
        String sql = "SELECT * FROM Cars;";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Cars cars;
            cars = new Cars(rs.getString("cars_vin"), rs.getString("cars_make"), rs.getString("cars_model"), rs.getInt("cars_year"), rs.getInt("cars_miles"));
            System.out.println(cars.getCarVin() + " - " + cars.getMake() + " - " + cars.getModel() + " - " + cars.getYear() + " - " + cars.getMiles());
            carsData.add(cars);
        }
        initializeCarsColumns();
        rs.close();
    }

    public void loadCustomers() throws SQLException {
        tableView.getItems().clear();
        tableView.getColumns().clear();
        Connection connection = App.loadJDBC();
        String sql = "SELECT * FROM Customers;";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Customers customers;
            customers = new Customers(rs.getInt("cus_id"), rs.getString("cus_fname"), rs.getString("cus_lname"), rs.getString("cus_address"));
            System.out.println(customers.getCusID() + " - " + customers.getFirstName() + " - " + customers.getLastName() + " - " + customers.getAddress());
            customersData.add(customers);
        }
        initializeCustomerColumns();
        rs.close();
    }

    public void loadSales() throws SQLException {
        tableView.getItems().clear();
        tableView.getColumns().clear();
        Connection connection = App.loadJDBC();
        String sql = "SELECT * FROM Sales;";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Sales sales;
            sales = new Sales(rs.getInt("sales_id"), rs.getInt("sales_price"), rs.getInt("sales_intrate"), rs.getString("cars_vin"), rs.getInt("cus_id"));
            System.out.println(sales.getSalesID() + " - " + sales.getSalesPrice() + " - " + sales.getSalesInterest() + " - " + sales.getCarVin() + " - " + sales.getCusID());
            salesData.add(sales);
        }
        initializeSalesColumns();
        rs.close();
    }

    @FXML
    private void myInnerJoin1() throws SQLException {
        tableView.getItems().clear();
        tableView.getColumns().clear();
        Connection connection = App.loadJDBC();
        String sql = "SELECT c.cus_fname, c.cus_lname, s.sales_price, s.sales_intrate\n" +
                "FROM customers AS c\n" +
                "INNER JOIN sales AS s\n" +
                "ON c.cus_id = s.cus_id";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            CustomerSales customersales;
            customersales = new CustomerSales(rs.getString("cus_fname"), rs.getString("cus_lname"), rs.getInt("sales_price"), rs.getInt("sales_intrate"));
            System.out.println(customersales.getFirstName() + " - " + customersales.getLastName() + " - " + customersales.getSalesPrice() + " - " + customersales.getSalesInterest());
            customerSalesData.add(customersales);
        }
        initializeMyInnerJoin1();
        rs.close();
    }

    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("in the second initializable");
        try {
            loadSales();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initializeSalesColumns();
    }*/

    private void initializeSalesColumns() {
        TableColumn id = new TableColumn("Sales ID");
        id.setMinWidth(75);
        id.setCellValueFactory(new PropertyValueFactory<Sales, Integer>("salesID"));

        TableColumn salesPrice = new TableColumn("Price");
        salesPrice.setMinWidth(150);
        salesPrice.setCellValueFactory(new PropertyValueFactory<Sales, Integer>("salesPrice"));

        TableColumn salesInterest = new TableColumn("Interest");
        salesInterest.setMinWidth(75);
        salesInterest.setCellValueFactory(new PropertyValueFactory<Sales, Integer>("salesInterest"));

        TableColumn carVin = new TableColumn("VIN");
        carVin.setMinWidth(75);
        carVin.setCellValueFactory(new PropertyValueFactory<Sales, String>("carVin"));

        TableColumn cusID = new TableColumn("Cus ID");
        cusID.setMinWidth(75);
        cusID.setCellValueFactory(new PropertyValueFactory<Sales, Integer>("cusID"));
        tableView.setItems(salesData);
        tableView.getColumns().addAll(id, salesPrice, salesInterest, carVin, cusID);
    }

    private void initializeCustomerColumns() {
        TableColumn id = new TableColumn("Cus ID");
        id.setMinWidth(75);
        id.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("cusID"));

        TableColumn firstName = new TableColumn("First Name");
        firstName.setMinWidth(150);
        firstName.setCellValueFactory(new PropertyValueFactory<Customers, String>("firstName"));

        TableColumn lastName = new TableColumn("Last Name");
        lastName.setMinWidth(150);
        lastName.setCellValueFactory(new PropertyValueFactory<Customers, String>("lastName"));

        TableColumn address = new TableColumn("Address");
        address.setMinWidth(300);
        address.setCellValueFactory(new PropertyValueFactory<Customers, String>("address"));

        tableView.setItems(customersData);
        tableView.getColumns().addAll(id, firstName, lastName, address);
    }

    private void initializeCarsColumns() {
        TableColumn id = new TableColumn("VIN");
        id.setMinWidth(175);
        id.setCellValueFactory(new PropertyValueFactory<Customers, String>("carVin"));

        TableColumn make = new TableColumn("Make");
        make.setMinWidth(125);
        make.setCellValueFactory(new PropertyValueFactory<Customers, String>("make"));

        TableColumn model = new TableColumn("Model");
        model.setMinWidth(125);
        model.setCellValueFactory(new PropertyValueFactory<Customers, String>("model"));

        TableColumn year = new TableColumn("Year");
        year.setMinWidth(75);
        year.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("year"));

        TableColumn miles = new TableColumn("Miles");
        miles.setMinWidth(75);
        miles.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("miles"));

        tableView.setItems(carsData);
        tableView.getColumns().addAll(id, make, model, year, miles);
    }

    private void initializeMyInnerJoin1() {
        TableColumn firstName = new TableColumn("First Name");
        firstName.setMinWidth(175);
        firstName.setCellValueFactory(new PropertyValueFactory<Customers, String>("firstName"));

        TableColumn lastName = new TableColumn("Last Name");
        lastName.setMinWidth(125);
        lastName.setCellValueFactory(new PropertyValueFactory<Customers, String>("lastName"));

        TableColumn salesPrice = new TableColumn("Sales Price");
        salesPrice.setMinWidth(125);
        salesPrice.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("salesPrice"));

        TableColumn saleInterest = new TableColumn("Sale Interest");
        saleInterest.setMinWidth(75);
        saleInterest.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("salesInterest"));

        tableView.setItems(customerSalesData);
        tableView.getColumns().addAll(firstName, lastName, salesPrice, saleInterest);
    }

    public void insertCustomersData(ActionEvent actionEvent) throws SQLException {
        insertSuccessLabel.setText("");
        warningLabel.setText("");
        if((firstNameField.getText().isBlank() || lastNameField.getText().isBlank()) || (addressField.getText().isBlank()))  {
            warningLabel.setText("No null values allowed!");
        } else {
            Connection connection = App.loadJDBC();
            String sql = "INSERT INTO customers (cus_fname, cus_lname, cus_address) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, firstNameField.getText());
            pstmt.setString(2, lastNameField.getText());
            pstmt.setString(3, addressField.getText());
            pstmt.executeUpdate();
            insertSuccessLabel.setText(firstNameField.getText() + " " + lastNameField.getText() + " has been inserted into the customers table!");
        }
    }

    public void insertCarsData(ActionEvent actionEvent) throws SQLException {
        insertSuccessLabel.setText("");
        warningLabel.setText("");
        if (vinField.getText().isBlank() || makeField.getText().isBlank() || (modelField.getText().isBlank() || yearField.getText().isBlank()) || (milesField.getText().isBlank())) {
            warningLabel.setText("No null values allowed!");
        } else {
            Connection connection = App.loadJDBC();
            String sql = "INSERT INTO cars (cars_vin, cars_make, cars_model, cars_year, cars_miles) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, vinField.getText());
            pstmt.setString(2, makeField.getText());
            pstmt.setString(3, modelField.getText());
            pstmt.setString(4, yearField.getText());
            pstmt.setString(5, milesField.getText());
            pstmt.executeUpdate();
            insertSuccessLabel.setText(makeField.getText() + " " + modelField.getText() + " has been inserted into the cars table!");
        }
    }

    public void insertSalesData(ActionEvent actionEvent) throws SQLException {
        insertSuccessLabel.setText("");
        warningLabel.setText("");
        if (((priceField.getText().isBlank() || intrateField.getText().isBlank()) || (salesVinField.getText().isBlank() || salesCustomerField.getText().isBlank()))) {
            warningLabel.setText("No null values allowed!");
        } else {
            Connection connection = App.loadJDBC();
            String sql = "INSERT INTO sales (sales_price, sales_intrate, cars_vin, cus_id) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, priceField.getText());
            pstmt.setString(2, intrateField.getText());
            pstmt.setString(3, salesVinField.getText());
            pstmt.setString(4, salesCustomerField.getText());
            pstmt.executeUpdate();
            insertSuccessLabel.setText("Customer " + salesCustomerField.getText() + " has been inserted into the sales table!");
        }
    }
}