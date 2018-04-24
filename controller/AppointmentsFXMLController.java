package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static controller.MessageBox.display;

public class AppointmentsFXMLController implements Initializable {


    public Button addBtnAp;
    public TextField dotw;
    public TableView tblAppointment;
    public TextField idApoi;
    public TableColumn<UserDetails, String> idAp;
    public TableColumn<UserDetails, String> first_nameAp;
    public TableColumn<UserDetails, String> last_nameAp;
    public TableColumn<UserDetails, String> genderAp;
    public TableColumn<UserDetails, String> emailAp;
    public TableColumn<UserDetails, String> phone_numberAp;
    public TableColumn<UserDetails, String> addressAp;
    public TableColumn<UserDetails, String> birth_dateAp;
    public TableColumn<UserDetails, String> timeAp;

    private static Connection conn = null;
    private static PreparedStatement stat = null;
    private static PreparedStatement stat1 = null;

    private static String url = "jdbc:mysql://localhost:3306/data_hosp";
    private static String Password = "";
    private static String userame = "root";
    public Button homebtn1;

    private ObservableList<UserDetails> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    public void addBtnAp(ActionEvent event) {
        String queryString = "update patients set appointment = ? where id = ? ";
        String table = "select id, first_name, last_name, gender, email, phone_number , address, birth_date, appointment from patients ";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, userame, Password);
            data = FXCollections.observableArrayList();
            stat = conn.prepareStatement(queryString);
            stat.setString(1, dotw.getText().toString());
            stat.setString(2, idApoi.getText().toString());
            stat.executeUpdate();
            display("Success", "Successfully added");
            stat1 = conn.prepareStatement(table);
            ResultSet rs = stat1.executeQuery();
            while (rs.next()) {
                data.add(new UserDetails(rs.getString("id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("gender"), rs.getString("email"),
                        rs.getString("phone_number"), rs.getString("address"), rs.getString("birth_date"),
                        rs.getString("appointment")));
            }

        } catch (Exception ex) {
            Logger.getLogger(PatientDetailsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Set cell value factory to tableview.
        idAp.setCellValueFactory(new PropertyValueFactory<>("id"));
        first_nameAp.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        last_nameAp.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        genderAp.setCellValueFactory(new PropertyValueFactory<>("gender"));
        emailAp.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone_numberAp.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        addressAp.setCellValueFactory(new PropertyValueFactory<>("address"));
        birth_dateAp.setCellValueFactory(new PropertyValueFactory<>("birth_date"));
        timeAp.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblAppointment.setItems(null);
        tblAppointment.setItems(data);
    }

    public void toHome(ActionEvent event) throws Exception {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/AdminLodinPanelFXML.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();
    }
}
