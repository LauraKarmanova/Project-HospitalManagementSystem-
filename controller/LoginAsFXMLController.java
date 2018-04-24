package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoginAsFXMLController implements Initializable{
    @FXML
    private Label hospitalLabel;
    @FXML
    private Label loginAsLabel;
    @FXML
    private Button adminButton;
    @FXML
    private Button doctorButton;
    @FXML
    private Button receptionistButton;
    @FXML
    private Label contactusLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Label faqLabel;

    @Override
    public void initialize(URL url , ResourceBundle rb){

    }
    @FXML
    private void adminAction(ActionEvent event)throws IOException {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/AdminLodinPanelFXML.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();
    }
    @FXML
    private void doctorAction(ActionEvent event)throws IOException {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/AdminLodinPanelFXML.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();
    }
    @FXML
    private void receptionistAction(ActionEvent event)throws IOException {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/AdminLodinPanelFXML.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();
    }
}
