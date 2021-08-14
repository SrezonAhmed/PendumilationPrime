package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Register {
    @FXML
    private AnchorPane regPane;
    @FXML
    private JFXTextField userID;
    @FXML
    private JFXPasswordField passID;
    @FXML
    private JFXTextField emailID;
    @FXML
    private JFXButton registerButton;
    @FXML
    private Label welcome;
    @FXML
    private JFXButton closeRButton;

    //private Handler handler;
    private PreparedStatement pst;
    Connection connection;

    public void initialize(URL url, ResourceBundle rb) {
        //handler = new Handler()//;
    }

    //////////////////////REGISTERING//////////////////
    @FXML
    void registerUp(ActionEvent event) throws SQLException, ClassNotFoundException {
        //SAVING DATA//
        connection = dbConnection.connector();
        try {
            pst = connection.prepareStatement("INSERT INTO main.dbLog (id, username, password, email) values (?, ?, ?, ?)");
            pst.setString(1, "id");
            pst.setString(2, userID.getText());
            pst.setString(3, passID.getText());
            pst.setString(4, emailID.getText());
            //pst.execute();
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ////WELCOME MESSAGE//
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Welcome to Pendumilation :) \n Please login now to use application" );
        alert.showAndWait();
    }

    ///////// CLOSE BUTTON /////////
    @FXML
    void makeRClose(ActionEvent event) {
        Stage stage = (Stage) closeRButton.getScene().getWindow();
        stage.close();
    }
}
