package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class factorsClass extends pendulum {
    @FXML
    private AnchorPane factorPane;
    @FXML
    private JFXButton closeFButton;
    @FXML
    private JFXButton graphButtonD;
    @FXML
    private JFXButton graphButtonV;
    @FXML
    private JFXButton graphButtonA;


    ///////////////ACCELERATION GRAPH SCREEN BUTTON///////////////////////
    @FXML
    void makeGraphAcc(ActionEvent event) throws Exception {
        Stage primaryStage = new Stage();
        graphAcceleration graph = new graphAcceleration();
        graph.start(primaryStage);
        primaryStage.setTitle("Pendumilation - Equations - Acceleration Graph");
        //primaryStage.initStyle(StageStyle.UNIFIED);
        //primaryStage.setScene(new Scene(p, 658, 447));
        primaryStage.show();
    }

    ///////////////DISPLACEMENT GRAPH SCREEN BUTTON///////////////////////
    @FXML
    void makeGraphDis(ActionEvent event) throws Exception {
        Stage primaryStage = new Stage();
        graphDisplacement graph = new graphDisplacement();
        graph.start(primaryStage);
        primaryStage.setTitle("Pendumilation - Equations - Displacement Graph");
        //primaryStage.initStyle(StageStyle.UNIFIED);
        //primaryStage.setScene(new Scene(p, 658, 447));
        primaryStage.show();
    }

    ///////////////VELOCITY GRAPH SCREEN BUTTON///////////////////////
    @FXML
    void makeGraphVel(ActionEvent event) throws Exception {
        Stage primaryStage = new Stage();
        graphVelocity graph = new graphVelocity();
        graph.start(primaryStage);
        primaryStage.setTitle("Pendumilation - Equations - Velocity Graph");
        //primaryStage.initStyle(StageStyle.UNIFIED);
        //primaryStage.setScene(new Scene(p, 658, 447));
        primaryStage.show();
    }

    @FXML
    void makeFClose(ActionEvent event) {
        Stage stage = (Stage) closeFButton.getScene().getWindow();
        stage.close();
        //Platform.exit();
        //System.exit(0);
    }
}
