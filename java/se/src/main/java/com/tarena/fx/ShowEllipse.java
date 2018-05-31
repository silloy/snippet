package com.tarena.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

/**
 * @author SuShaohua
 * @date 2016/8/24 15:35
 * @description
 */
public class ShowEllipse extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new StackPane();
        for (int i = 0; i < 24; i++){
            Ellipse e = new Ellipse(250, 300, 200, 100);
            e.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
            e.setFill(Color.color(Math.random(), Math.random(), Math.random()));
            e.setRotate(i * 180 / 24);
            pane.getChildren().add(e);
        }

        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setTitle("Ellipse");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
