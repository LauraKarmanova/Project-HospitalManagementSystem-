package controller;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class DoctorDashboardFXMLController implements Initializable {
    public Button backBttn6;
    public Button ptntDtlsBtn;
    public Button appntmntsBtn;
    public ImageView logo;

    @Override
public void initialize(URL url, ResourceBundle resourceBundle){
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
