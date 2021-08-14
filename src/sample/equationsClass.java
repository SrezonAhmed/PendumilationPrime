package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class equationsClass extends Controller {
    @FXML
    private AnchorPane equationsPane;
    @FXML
    private JFXTextField angle;
    @FXML
    private JFXTextField time;
    @FXML
    private JFXButton displacementButton;
    @FXML
    private Label displacementAnswer;
    @FXML
    private JFXTextField amplitude;
    @FXML
    private JFXButton velocityButton;
    @FXML
    private JFXButton accelerationButton;
    @FXML
    private Label velocityAnswer;
    @FXML
    private Label accelerationAnswer;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton graphButtonD;
    @FXML
    private JFXButton graphButtonV;
    @FXML
    private JFXButton graphButtonA;
    @FXML
    private JFXButton eCloseButton;

    public Pane p = new Pane();

    //TEXT FIELD STRING VALUES TO INTEGERS - ANGULAR FREQ., TIME, AMPLITUDE////////////
    //private double angleInt = Integer.parseInt(angle.getText());
    //private double timeInt = Integer.parseInt(time.getText());
    //private double amplitudeInt = Integer.parseInt(amplitude.getText());

    ///////////////CALCULATING ACCELERATION////////////////////
    @FXML
    void calculateAcceleration(ActionEvent event) {
        double accelerationValue = Math.round(-Integer.parseInt(amplitude.getText()) * (Integer.parseInt(angle.getText()) * Integer.parseInt(angle.getText())) * Math.sin(Integer.parseInt(angle.getText()) * Integer.parseInt(time.getText())) * 100.00) / 100.00;
        accelerationAnswer.setText("" + accelerationValue);
        accelerationText = "" + accelerationValue;
    }

    ///////////////CALCULATING DISPLACEMENT////////////////////
    @FXML
    void calculateDisplacement(ActionEvent event) {
        double displacementValue = Math.round(Math.sin(Integer.parseInt(angle.getText()) * Integer.parseInt(time.getText())) * 100.00) / 100.00;
        displacementAnswer.setText("" + displacementValue);
        displacementText = "" + displacementValue;
    }

    ///////////////CALCULATING VELOCITY///////////////////////
    @FXML
    void calculateVelocity(ActionEvent event) {
        double velocityValue = Math.round(Integer.parseInt(amplitude.getText()) * Integer.parseInt(angle.getText()) * Math.cos(Integer.parseInt(angle.getText()) * Integer.parseInt(time.getText())) * 100.00) / 100.00;
        velocityAnswer.setText("" + velocityValue);
        velocityText = "" + velocityValue;
    }

    //////////////////////////////////////////////////////////////////////////////////////

    ///////////////SAVING EQUATIONS///////////////////////
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    //get current date time with Date()
    Date date = new Date();
    String dateT = (dateFormat.format(date));
    @FXML
    public void saveEquations(ActionEvent event) {
        ////////READING EQUATIONS INTO A FILE/////////
        FileChooser fileChooser = new FileChooser();
        //fileChooser.setInitialDirectory(new File(System.getProperty("C:\\Users\\mania\\IdeaProjects\\CSIA")));

        TextInputDialog dialogue = new TextInputDialog();
        dialogue.setTitle("Pendumilation - Save Equations");
        dialogue.setContentText("Please enter file name: ");
        fileName = dialogue.showAndWait();

        if (fileName.isPresent()) {
            try{
                // Create file
                FileWriter fileStream = new FileWriter(fileName.get() + ".txt");
                BufferedWriter out = new BufferedWriter(fileStream);
                out.write("Date (YYYY/MM/DD) and Time (HH/MM/SS): " + dateT + ", \n");
                out.write("\nDisplacement: " + displacementText + ", \n" + "m");
                out.write("\nVelocity: " + velocityText + ", \n" + "ms");
                out.write("\nAcceleration: " + accelerationText + "ms^2");
                //Close the output stream
                out.close();
            }catch (Exception e){//Catch exception if any
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    ///////// CLOSE BUTTON /////////
    @FXML
    void makeEClose(ActionEvent event) {
        Stage stage = (Stage) eCloseButton.getScene().getWindow();
        stage.close();
        //Platform.exit();
        //System.exit(0);
    }

}