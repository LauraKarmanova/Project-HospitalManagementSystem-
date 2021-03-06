package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import static controller.MessageBox.display;

public class ReceptionistDetailsController implements Initializable {
    public TextField nameFldRec;
    public TextField usernameFldRec;
    public TextField passwordFldRec;
    public Button addBtn;

    private static Connection conn = null;
    private static PreparedStatement stat = null;
    private static String url = "jdbc:mysql://localhost:3306";
    private static String Password = "";
    private static String userame = "root";
    public Button backBtn4;

    @Override
    public void initialize(URL url , ResourceBundle rb){

    }

    public void addbtnAction(ActionEvent event) {
        String queryString = "insert into  test.register (username, password,name) values (?,?, ?);";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, userame, Password);
            stat = conn.prepareStatement(queryString);
            stat.setString(1,usernameFldRec.getText().toString());
            stat.setString(2,passwordFldRec.getText().toString());
            stat.setString(3,nameFldRec.getText().toString());
            stat.executeUpdate();
            display("Success", "Successfully added");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toHome(ActionEvent event) throws Exception{
        Parent pat = FXMLLoader.load(getClass().getResource("/view/AdminPortalFXML.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();
    }
}
