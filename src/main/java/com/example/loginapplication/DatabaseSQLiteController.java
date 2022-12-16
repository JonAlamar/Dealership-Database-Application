package com.example.loginapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class DatabaseSQLiteController implements Initializable {

    @FXML
    private Button button;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label wrongLogin;

    @FXML
    private Label thankyouLabel;

    @FXML
    private Button registerButton;


    @FXML
    private void checkLogin(ActionEvent event) throws SQLException, IOException {
        ResultSet rs = loadSet();

        while(rs.next()){
            if(rs.getString("user_name").equals(username.getText()) && rs.getString("user_password").equals(password.getText())) {
                App n = new App();
                n.changeScene("afterLogin.fxml");
            }
        }
        wrongLogin.setText("Wrong username or password!");
    }

    public void registerUser(ActionEvent event) throws SQLException {
        wrongLogin.setText("");
        Connection connection = App.loadJDBC();
        String sql = "INSERT INTO users (user_name, user_password) VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, username.getText());
        pstmt.setString(2, password.getText());
        pstmt.executeUpdate();
        thankyouLabel.setText("Thank you for registering " + username.getText() + "!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            App.loadJDBC();
            thankyouLabel.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet loadSet() throws SQLException{
        Connection connection = App.loadJDBC();
        String sql = "SELECT * FROM USERS";
        Statement statement = connection.createStatement();
        statement.executeQuery(sql);
        ResultSet rs = statement.executeQuery(sql);
        /*rs.beforeFirst();*/
        /*while(rs.next()){
            System.out.println("UserName:"+rs.getString("user_name")+"\nPassword:"+rs.getString("user_password"));
        }*/
        return rs;
    }
}
