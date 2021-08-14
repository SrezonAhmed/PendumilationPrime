package sample;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class pendulum extends Controller {
    //////////////////SIMULATION OF PENDULUM///////////////////////////
    PathTransition bPath;
    public void SimulationOne(Pane paneOne) throws Exception {
        double w = 400;
        double h = 400;
        Arc simulationArc;
        simulationArc = new Arc(w / 2, h * 0.8, w * 0.15, w * 10, 180, 180);
        simulationArc.setFill(Color.TRANSPARENT);
        simulationArc.setStroke(Color.BLACK);
        smallCircle = new Circle(simulationArc.getCenterX() - simulationArc.getRadiusX(), simulationArc.getCenterY(), 20);
        bigCircle = new Circle(simulationArc.getCenterX(), simulationArc.getCenterY() - h / 2, smallCircle.getRadius() / 2);
        simulationArc = new Arc(bigCircle.getCenterX(), bigCircle.getCenterY(), w / 2, h / 2, 240, 60);
        line = new Line(bigCircle.getCenterX(), bigCircle.getCenterY(), smallCircle.getCenterX(), smallCircle.getCenterY());
        line.endXProperty().bind(smallCircle.translateXProperty().add(smallCircle.getCenterX()));
        line.endYProperty().bind(smallCircle.translateYProperty().add(smallCircle.getCenterY()));
        bPath = new PathTransition();
        bPath.setDuration(Duration.millis(4000));
        bPath.setPath(simulationArc);
        bPath.setNode(smallCircle);
        bPath.setOrientation(PathTransition.OrientationType.NONE);
        bPath.setCycleCount(PathTransition.INDEFINITE);
        bPath.setAutoReverse(true);
        bPath.play();
        System.out.println(bPath.getCurrentRate());
        ////// SPEED OF PENDULUM FROM MOUSE ACTION ///
        paneOne.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                decreaseSpeed();
            } else if (e.getButton() == MouseButton.SECONDARY) {
                increaseSpeed();
            }
        });

        paneOne.getChildren().addAll(smallCircle, bigCircle, line);
    }

    //// INCREASE PENDULUM SPEED ///
    public void increaseSpeed() {
        bPath.setRate(bPath.getCurrentRate() + 1);
    }

    //// DECREASE PENDULUM SPEED ///
    public void decreaseSpeed() {
        bPath.setRate(bPath.getCurrentRate() - 1);
    }


}