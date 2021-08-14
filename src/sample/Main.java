package sample;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.UP;

public class Main extends Application {
    private double xOffset = 0;
    private double yOffset = 0;
    @Override
    public void start(Stage primaryStage) throws Exception {
        //CONNECT TO WELCOME SCREEN//
        Parent root = FXMLLoader.load(getClass().getResource("welcomeSplash.fxml"));
        primaryStage.setTitle("Pendumilation");
        //welcomeSplash.fxml screen size
        Scene scene = new Scene(root, 610, 428);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        /// BORDERLESS WINDOWS DRAGGABLE ///
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.getIcons().add(new Image("sample/iconSim.png"));
        //Platform.setImplicitExit(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
