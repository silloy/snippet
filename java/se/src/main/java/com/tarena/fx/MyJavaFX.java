package com.tarena.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author SuShaohua
 * @date 2016/8/23 17:00
 * @description
 */
public class MyJavaFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btOk = new Button("ok");
        Scene scene = new Scene(btOk, 200, 250);
        primaryStage.setTitle("OKK");
        primaryStage.setScene(scene);
        primaryStage.show();

//        Stage stage = new Stage();
//        stage.setTitle("hello");
//        stage.setScene(new Scene(new Button("hello"), 100, 200));
//        stage.show();

        StackPane pane = new StackPane();
        pane.getChildren().add(new Button("ok"));
        Scene s = new Scene(pane, 200, 50);
        primaryStage.setTitle("Button in a pane");
        primaryStage.setScene(s);
        primaryStage.show();

        Circle circle = new Circle();
        circle.setCenterX(100);
        circle.setCenterY(100);
        circle.setRadius(50);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHEAT);
        Font font = new Font("HELLO", 16);
        Font.font(13);
        Pane p = new Pane();
        circle.centerXProperty().bind(p.widthProperty().divide(2));
        circle.centerYProperty().bind(p.heightProperty().divide(2));
        p.getChildren().add(circle);
        Scene sc = new Scene(p, 300, 200);
        primaryStage.setTitle("Circle");
        primaryStage.setScene(sc);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
