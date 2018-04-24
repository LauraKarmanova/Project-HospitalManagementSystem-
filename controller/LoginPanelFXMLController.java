package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static controller.DbConnection.Connect;
import static controller.MessageBox.display;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginPanelFXMLController implements Initializable {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label loginHereLabel;
    @FXML
    private AnchorPane panel;
    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    @FXML
    private void backAction(ActionEvent event) throws Exception {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/LoginAs.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();
    }

    private static Connection conn = null;
    private static PreparedStatement stat = null;
    private static String url = "jdbc:mysql://localhost:3306";
    private static String Password = "";
    private static String userame = "root";
    private static String sqlInsert;
    ResultSet result;

    @FXML
    private void logIn(ActionEvent event) throws SQLException, IOException {


        String sqlSelect = "select * from test.register where username='" + username.getText().toString() +
                "' and password='" + password.getText().toString() + "' ";

        try {
            Thread.sleep(1000);
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, userame, Password);
            stat = conn.prepareStatement(sqlSelect);
            result = stat.executeQuery();
            boolean adm = username.getText().toString().equals("admin");
            boolean rece = username.getText().toString().equals("receptionist");
            boolean doc = username.getText().toString().equals("dentist");
            if (result.next()) {
                if ((result.getString("username")).equals(username.getText().toString())
                        && (result.getString("password")).equals(password.getText().toString())&& rece) {

                        Parent pat = FXMLLoader.load(getClass().getResource("/view/ReceptionistDashboardFXML.fxml"));
                        Scene patientScene = new Scene(pat);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(patientScene);
                        window.show();
                }
                    if ((result.getString("username")).equals(username.getText().toString())
                            && (result.getString("password")).equals(password.getText().toString())&& adm) {
                        Parent pat = FXMLLoader.load(getClass().getResource("/view/AdminPortalFXML.fxml"));
                        Scene patientScene = new Scene(pat);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(patientScene);
                        window.show();
                    }
                    if ((result.getString("username")).equals(username.getText().toString())
                            && (result.getString("password")).equals(password.getText().toString())&& doc) {
                        Parent pat = FXMLLoader.load(getClass().getResource("/view/DoctorDashboardFXML.fxml"));
                        Scene patientScene = new Scene(pat);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(patientScene);
                        window.show();
                    }

            } else if (!result.next()) {
                display("Error", "username or password is invalid ");
            }


        } catch (Exception r) {
            display("Error", r.getMessage());
        }

    }
}