package controller;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ReceptionistDashboardFXMLController implements Initializable {
    public Button backBttn5;
    public Button ptntDtlsBtn2;
    public Button appntmntsBtn2;
    public ImageView logoImage;

    @FXML
    private AnchorPane panel;

    @Override
    public void initialize(URL url , ResourceBundle rb){
        RotateTransition rotateTransition=new RotateTransition(Duration.seconds(122), logoImage);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(10*720);
        rotateTransition.play();
    }
    @FXML
    private void logoutAction(ActionEvent event)throws Exception {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/LoginAs.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();
    }

    public void toLogin(ActionEvent event) throws Exception {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/AdminLodinPanelFXML.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();
    }

    public void ptntDtlsAction(ActionEvent event)throws Exception {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/PatientDetailsFXML.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();
    }

    public void appntmntsAction(ActionEvent event) throws Exception {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/AppointmentsFXML.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();
    }
}
