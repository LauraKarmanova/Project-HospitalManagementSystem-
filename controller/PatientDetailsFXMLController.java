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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static controller.MessageBox.display;

public class PatientDetailsFXMLController implements Initializable {
    public TableColumn genderSearch;
    public TableColumn emailSearch;
    public TableColumn phone_numberSearch;
    public TableColumn addressSearch;
    public TableColumn birth_dateSearch;
    public TableColumn typeSearch;
    public TableColumn last_nameSearch;
    public TableColumn first_nameSearch;
    public TableColumn idSearch;
    public TableView tblSearch;
    public TextField addressSrch;
    public TextField phone_numberSrch;
    public TextField birth_dateSrch;
    public TextField first_nameSrch;
    public TextField last_nameSrch;
    public TextField emailSrch;
    public Button searchBtn;
    public Button homeButton3;
    public TextField first_nameUpdt;
    public TextField last_nameUpdt;
    public Button updateBtn;
    public TextField emailUpdt;
    public TextField addressUpdt;
    public TextField phone_numberUpdt;
    public TextField birth_dateUpdt;
    public Button logoutButton3;
    public Button homeButton2;
    public Button deleteBtn;
    public TextField first_nameAdd;
    public TextField last_nameAdd;
    public Button addBtn;
    public TextField emailAdd;
    public ToolBar genderAdd;
    public CheckBox maleAdd;
    public CheckBox femaleAdd;
    public TextField phone_numberAdd;
    public TextField addressAdd;
    public TextField birth_dateAdd;
    public ToolBar typeAdd;
    public CheckBox indoorAdd;
    public CheckBox outdoorAdd;
    public TextField idDlt;
    public TextField username;
    public PasswordField password;
    public Label loginHereLabel;
    public AnchorPane panel;
    public TextField idSrch;
    public Button logoutButton;
    public Button homeButton;
    public TableView<UserDetails> tblViewer;
    public TableColumn<UserDetails, String> columnId;
    public TableColumn<UserDetails, String> columnFName;
    public TableColumn<UserDetails, String> columnLName;
    public TableColumn<UserDetails, String> columnGender;
    public TableColumn<UserDetails, String> columnEMail;
    public TableColumn<UserDetails, String> columnPhNumber;
    public TableColumn<UserDetails, String> columnAddress;
    public TableColumn<UserDetails, String> columnType;
    public TableColumn<UserDetails, String> columnBDate;

    private ObservableList<UserDetails> data;
    private DbConnection dc = new DbConnection();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("select id, first_name,last_name,gender,email,phone_number,address,birth_date,type from patients");
            while (rs.next()) {
                data.add(new UserDetails(rs.getString("id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("gender"), rs.getString("email"),
                        rs.getString("phone_number"), rs.getString("address"), rs.getString("birth_date"), rs.getString("type")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PatientDetailsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Set cell value factory to tableview.
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnFName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        columnLName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        columnEMail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnPhNumber.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        columnBDate.setCellValueFactory(new PropertyValueFactory<>("birth_date"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));

        tblViewer.setItems(null);
        tblViewer.setItems(data);

    }

    @FXML
    private void toHome(ActionEvent event) throws Exception {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/AdminPortalFXML.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();
    }

    @FXML
    private void toLoginAs(ActionEvent event) throws Exception {
        Parent pat = FXMLLoader.load(getClass().getResource("/view/LoginAs.fxml"));
        Scene patientScene = new Scene(pat);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(patientScene);
        window.show();
    }

    private String gender() {
        if (femaleAdd.isSelected()) {
            return "Female";
        } else {
            return "Male";
        }

    }

    private String typeIn() {
        if (indoorAdd.isSelected()) {
            return "Indoor";
        } else {
            return "Outdoor";
        }

    }

    public void searchBtnAction(ActionEvent event) {
        String queryString = "select first_name, last_name ,email,phone_number,address,birth_date from patients where id = ?;";
        try {
            Connection connection = dc.Connect();
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, idSrch.getText().toString());
            ResultSet rset = preparedStatement.executeQuery();
            if (rset.next()) {
                first_nameSrch.setText(rset.getString(1));
                last_nameSrch.setText(rset.getString(2));
                emailSrch.setText(rset.getString(3));
                phone_numberSrch.setText(rset.getString(4));
                addressSrch.setText(rset.getString(5));
                birth_dateSrch.setText(rset.getString(6));
            } else {
                first_nameSrch.setText("");
                last_nameSrch.setText("");
                emailSrch.setText("");
                phone_numberSrch.setText("");
                addressSrch.setText("");
                birth_dateSrch.setText("");
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }

    public void updateBtnAction(ActionEvent event) {
        String queryString = "update patients set last_name = ?, email = ?, phone_number = ?, address = ?,  birth_date= ? where first_name = ? ";
        try {
            Connection conn = dc.Connect();
            PreparedStatement preparedStatement = conn.prepareStatement(queryString);
            preparedStatement.setString(1, last_nameUpdt.getText().toString());
            preparedStatement.setString(2, emailUpdt.getText().toString());
            preparedStatement.setString(3, phone_numberUpdt.getText().toString());
            preparedStatement.setString(4, addressUpdt.getText().toString());
            preparedStatement.setString(5, birth_dateUpdt.getText().toString());
            preparedStatement.setString(6, first_nameUpdt.getText().toString());

            preparedStatement.executeUpdate();
            display("Success", "Successfully updated");
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }

    public void deleteBtnAction(ActionEvent event) {
        try {
            Connection con = dc.Connect();
            Statement statement = con.createStatement();
            statement.executeUpdate("delete from patients where id= " + idDlt.getText().toString() + " ;");


        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }

    public void skipBtnAction(ActionEvent event) {
        first_nameAdd.setText("");
        last_nameAdd.setText("");
        emailAdd.setText("");
        phone_numberAdd.setText("");
        addressAdd.setText("");
        birth_dateAdd.setText("");
        indoorAdd.setSelected(false);
        outdoorAdd.setSelected(false);
        femaleAdd.setSelected(false);
        maleAdd.setSelected(false);
    }

    public void addBtnAction(ActionEvent event) {
        String queryString = "insert into patients (first_name, last_name,gender, email, phone_number, address, birth_date, type, appointment) values (?,?, ?, ?, ?, ?, ?,?, ?);";

        try {
            Connection conn = dc.Connect();
            PreparedStatement preparedStatement = conn.prepareStatement(queryString);
            preparedStatement.setString(1, first_nameAdd.getText().toString());
            preparedStatement.setString(2, last_nameAdd.getText().toString());
            preparedStatement.setString(3, gender());
            preparedStatement.setString(4, emailAdd.getText().toString());
            preparedStatement.setString(5, phone_numberAdd.getText().toString());
            preparedStatement.setString(6, addressAdd.getText().toString());
            preparedStatement.setString(7, birth_dateAdd.getText().toString());
            preparedStatement.setString(8, typeIn());
            preparedStatement.setString(9, "");



            preparedStatement.executeUpdate();
            display("Success", "Successfully added");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
