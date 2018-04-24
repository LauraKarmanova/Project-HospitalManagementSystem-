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

public class IndoorPatientsFXMLController implements Initializable {

    public TableView<UserDetails> tblIndoor;
    public TableColumn<UserDetails, String> last_nameIn;
    public TableColumn <UserDetails, String> idIn;
    public TableColumn <UserDetails, String> first_nameIn;
    public TableColumn <UserDetails, String> genderIn;
    public TableColumn <UserDetails, String> emailIn;
    public TableColumn <UserDetails, String> phone_numberIn;
    public TableColumn <UserDetails, String> addressIn;
    public TableColumn <UserDetails, String> birth_dateIn;
    public Button loadBtnIn;
    public Button backBtn2;

    private ObservableList<UserDetails> data;
    private DbConnection dc = new DbConnection();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    public void loadBtnActionIn(ActionEvent event) {
        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            String outdoorQuery="select id, first_name, last_name, gender, email, phone_number , address, birth_date, type from patients where type = ?;";
            Connection connection = dc.Connect();
            PreparedStatement preparedStatement = connection.prepareStatement(outdoorQuery);
            preparedStatement.setString(1, "Indoor");
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
        idIn.setCellValueFactory(new PropertyValueFactory<>("id"));
        first_nameIn.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        last_nameIn.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        genderIn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        emailIn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone_numberIn.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        addressIn.setCellValueFactory(new PropertyValueFactory<>("address"));
        birth_dateIn.setCellValueFactory(new PropertyValueFactory<>("birth_date"));

        tblIndoor.setItems(null);
        tblIndoor.setItems(data);
    }

    public void toHome(ActionEvent event) throws Exception {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/AdminPortalFXML.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();

    }
}
