package com.tarena.fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * @author SuShaohua
 * @date 2016/8/24 16:20
 * @description
 */
public class ControlCircle extends Application {
    private CirclePane circlePane = new CirclePane();
    @Override
    public void start(Stage primaryStage) throws Exception {

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        Button btEnlarge = new Button("Enlarge");
        Button btshrink = new Button("Shrink");
        hBox.getChildren().addAll(btEnlarge, btshrink);

        btEnlarge.setOnAction(new EventHandler<ActionEvent> (){
            public void handle(ActionEvent e){
                circlePane.enlarge();
            }
        });
        btshrink.setOnAction(e -> circlePane.shrink());
        //btshrink.setOnAction(new ShinkHandle());

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(circlePane);
        borderPane.setBottom(hBox);
        BorderPane.setAlignment(hBox, Pos.CENTER);

        Scene scene = new Scene(borderPane, 400, 300);
        primaryStage.setTitle("Circle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class ShinkHandle implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            circlePane.shrink();
        }
    }
}


class CirclePane extends StackPane{
    private Circle circle = new Circle(100);
    public CirclePane(){
        getChildren().add(circle);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
    }

    public void enlarge() {
        circle.setRadius(circle.getRadius() + 20);
    }

    public void shrink(){
        circle.setRadius(circle.getRadius() > 20 ? circle.getRadius() - 2 : circle.getRadius());
    }
}