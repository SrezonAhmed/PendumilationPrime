package sample;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private JFXTextField userID;
    @FXML
    private JFXPasswordField passID;
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton registerButton;
    @FXML
    private JFXButton startButton;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXButton closeButton;
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private JFXButton arButton;

    //BORDERLESS WINDOWS VARIABLES//
    private double xOffset = 0;
    private double yOffset = 0;

    //LOGIN SCREEN VARIABLES//
    Connection connection = null;

    //MAIN SCREEN VARIABLES - PENDULUM//
    public Pane paneOne;
    public Circle bigCircle;
    public Circle smallCircle;
    public Line line;

    //MAIN SCREEN VARIABLES - BUTTONS//
    @FXML
    private JFXButton graphsButton;
    @FXML
    private JFXButton equationsButton;
    @FXML
    private JFXButton historyButton;
    @FXML
    private Label arValLabel;

    //OTHER SCREEN PANES//
    @FXML
    private AnchorPane factorPane;
    @FXML
    public AnchorPane equationsPane;
    @FXML
    public AnchorPane historyPane;

    /////HISTORY CLASS VARIABLES//////
    public Optional<String> fileName;
    public String displacementText;
    public String velocityText;
    public String accelerationText;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////WELCOME SCREEN///////////////////////////
    @FXML
    private AnchorPane rootPane;
    //START BUTTON//
    @FXML
    private void makeStart(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("login.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void startOne(Stage primaryStage) throws Exception {
        Parent rootPane = FXMLLoader.load(getClass().getResource("login.fxml"));
        //primaryStage.setTitle("Pendumilation - Login");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(rootPane, 632, 600));
        primaryStage.initStyle(StageStyle.UNDECORATED);
    }

    //////////////////LOGIN SCREEN - LOGIN BUTTON///////////////////////////
    public void makeLogin(ActionEvent event) throws Exception {

        /*progressBar.setDisable(false);
        Timer timer = new Timer();
        timer.wait((long) 0.5);*/

        //GET DATA FROM DATABASE//
        connection = dbConnection.connector();
        try {
            String query = "select * from main.dbLog where username=? and password=?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, userID.getText());
            pst.setString(2, passID.getText());
            ResultSet resultSet = pst.executeQuery();

            int count = 0;
            while (resultSet.next()) {
                count++;
                if ((count == 1) || (userID.getText().equals("user") && passID.getText().equals("pass"))) {
                    Stage primaryStage = new Stage();
                    paneOne = FXMLLoader.load(getClass().getResource("sample.fxml"));

                    //SIMULATION//
                    pendulum simulation = new pendulum();
                    simulation.SimulationOne(paneOne);

                    ///////////
                    //primaryStage.setTitle("Pendumilation :)");
                    //sample.fxml screen size
                    Scene scene = new Scene(paneOne, 658, 447);
                    primaryStage.initStyle(StageStyle.UNDECORATED);

                    /// BORDERLESS WINDOWS DRAGGABLE ///
                    paneOne.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            xOffset = event.getSceneX();
                            yOffset = event.getSceneY();
                        }
                    });
                    paneOne.setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            primaryStage.setX(event.getScreenX() - xOffset);
                            primaryStage.setY(event.getScreenY() - yOffset);
                        }
                    });
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("ERROR Logging In :( ");
                    alert.setContentText("Wrong username or password! Please try again.");
                }
            }
            resultSet.close();
            pst.close();
            makeClose(event);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //////////////////LOGIN SCREEN - REGISTER BUTTON///////////////////////////
    public  void makeRegister(ActionEvent event) throws Exception {
        Stage primaryStage = new Stage();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene scene = new Scene(pane,632, 432);
        //primaryStage.setTitle("Pendumilation - Registration");
        primaryStage.initStyle(StageStyle.UNDECORATED);

        // BORDERLESS WINDOWS DRAGGABLE ///
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //////////////////MAIN SCREEN - SIDE MENU///////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////BUTTON SCREENS///////////////////////////
    ///MAIN SCREEN - FACTOR BUTTON THAT LEAD TO FACTORS SCREEN////
    public void makeGraphs (ActionEvent event) throws Exception {

        Stage primaryStage = new Stage();
        factorPane = FXMLLoader.load(getClass().getResource("factors.fxml"));
        primaryStage.setTitle("Pendumilation - Factors");

        /// BORDERLESS WINDOWS DRAGGABLE ///
        factorPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        factorPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        Scene sceneF = new Scene(factorPane, 703, 391);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(sceneF);
        primaryStage.show();

    }

    ///MAIN SCREEN - EQUATION BUTTON THAT LEAD TO EQUATIONS SCREEN////
    public void makeEquations (ActionEvent event) throws Exception {
        Stage primaryStage = new Stage();
        equationsPane = FXMLLoader.load(getClass().getResource("equations.fxml"));
        primaryStage.setTitle("Pendumilation - Equations");
        primaryStage.initStyle(StageStyle.UNDECORATED);

        /// BORDERLESS WINDOWS DRAGGABLE ///
        equationsPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        equationsPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        primaryStage.setScene(new Scene(equationsPane, 860, 443));
        primaryStage.show();
    }

    ///MAIN SCREEN - EARLIER HISTORY BUTTON THAT LEAD TO EARLIER HISTORY SCREEN////
    public void makeHistory (ActionEvent event) throws Exception {
        Stage primaryStage = new Stage();
        historyPane = FXMLLoader.load(getClass().getResource("history.fxml"));
        primaryStage.setTitle("Pendumilation - Earlier History");
        primaryStage.initStyle(StageStyle.UNDECORATED);

        /// BORDERLESS WINDOWS DRAGGABLE ///
        historyPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        historyPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        primaryStage.setScene(new Scene(historyPane, 658, 447));
        primaryStage.show();
    }

    ///MAIN SCREEN - AIR RESISTANCE VALUE////
    @FXML
    public void newArVal(ActionEvent event) {
        int r = 5 +  (int)(Math.random()*(21));
        arValLabel.setText("" + r);
    }

    ///// CONNECTION TO FACTORS IN PENDULUM CLASS ///////////
    @FXML
    void factorsClicked(MouseEvent event) {
    }

    ///////// CLOSE BUTTON /////////
    @FXML
    public void makeClose(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}