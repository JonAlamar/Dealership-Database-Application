package com.example.loginapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;

import java.io.IOException;
import static javafx.application.Application.launch;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage stg;
    public static String jdbcURL = "jdbc:sqlite:src/main/resources/com/example/loginapplication/Dealership.db";


    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("DatabaseSQLite.fxml"));
        primaryStage.setTitle("Raceway Automotive");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        if (fxml.equalsIgnoreCase("afterLogin.fxml")) {
            stg.setScene(new Scene(pane, 1200, 850));
        } else {
            stg.setScene(new Scene(pane, 600, 600));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Connection loadJDBC() throws SQLException {
        System.out.println("Establishing JDBC connection");
        try {
            Connection connection = DriverManager.getConnection(jdbcURL);
            System.out.println("Connection success!");
            return connection;
        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        return null;
    }

}