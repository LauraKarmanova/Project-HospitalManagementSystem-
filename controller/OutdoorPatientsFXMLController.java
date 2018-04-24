package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OutdoorPatientsFXMLController implements Initializable {
    public TableView<UserDetails> tblOutdoor;
    public TableColumn<UserDetails, String> last_name;
    public TableColumn<UserDetails, String> id;
    public TableColumn<UserDetails, String> first_name;
    public TableColumn<UserDetails, String> gender;
    public TableColumn<UserDetails, String> email;
    public TableColumn<UserDetails, String> phone_number;
    public TableColumn<UserDetails, String> address;
    public TableColumn<UserDetails, String> birth_date;
    public Button loadBtn;
    public Button backbtn3;

    private ObservableList<UserDetails> data;
    private DbConnection dc = new DbConnection();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void loadBtnAction(ActionEvent event) {
        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            String outdoorQuery = "select id, first_name, last_name, gender, email, phone_number , address, birth_date, type from patients where type = ?;";
            Connection connection = dc.Connect();
            PreparedStatement preparedStatement = connection.prepareStatement(outdoorQuery);
            preparedStatement.setString(1, "Outdoor");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                data.add(new UserDetails(rs.getString("id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("gender"), rs.getString("email"),
                        rs.getString("phone_number"), rs.getString("address"), rs.getString("birth_date"), rs.getString("type")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PatientDetailsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Set cell value factory to tableview.
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        first_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        last_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone_number.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        birth_date.setCellValueFactory(new PropertyValueFactory<>("birth_date"));

        tblOutdoor.setItems(null);
        tblOutdoor.setItems(data);
    }

    public void toHome(ActionEvent event) throws Exception {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/AdminPortalFXML.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();

    }
}
