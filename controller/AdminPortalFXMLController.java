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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminPortalFXMLController implements Initializable {
    @FXML
    private Button logoutButton;
    @FXML
    private Hyperlink receptionHyper;
    @FXML
    private Hyperlink appointmentHyper;
    @FXML
    private Hyperlink indoorHyper;
    @FXML
    private Hyperlink outdoorHyper;
    @FXML
    private Hyperlink patientHyper;
    @FXML
    private Hyperlink doctorHyper;
    @FXML
    private AnchorPane panel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void receptionAction(ActionEvent event) throws Exception {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/ReceptionistDetails.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();
    }

    @FXML
    private void logoutAction(ActionEvent event) throws Exception {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/LoginAs.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();

    }

    @FXML
    private void appointmentsAction(ActionEvent event) throws Exception {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/AppointmentsFXML.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();

    }

    @FXML
    private void indoorPatAction(ActionEvent event) throws Exception {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/IndoorPatientsFXML.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();
    }

    @FXML
    private void doctorDetailsAction(ActionEvent event) throws Exception {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/DoctorDetailsFXML.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();
    }

    @FXML
    private void outdoorPatAction(ActionEvent event) throws Exception{
        Parent pat = FXMLLoader.load(getClass().getResource("/view/OutdoorPatientsFXML.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();
    }

    @FXML
    private void patientDetailsAction(ActionEvent event) throws Exception {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/PatientDetailsFXML.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();
    }


}
