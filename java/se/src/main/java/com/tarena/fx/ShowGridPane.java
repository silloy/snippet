package com.tarena.fx;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author SuShaohua
 * @date 2016/8/24 15:18
 * @description
 */
public class ShowGridPane extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(12, 12, 12, 12));
        pane.setHgap(5.5);
        pane.setVgap(11);

        pane.add(new Label("First Name: "), 0, 0);
        pane.add(new TextField(), 1, 0);
        pane.add(new Label("MI: "), 0, 1);
        pane.add(new TextField(), 1, 1);
        pane.add(new Label("Last Name: "), 0, 2);
        pane.add(new TextField(), 1, 2);
        pane.add(new Button("Add Name"), 1, 3);
        GridPane.setHalignment(new Button("Add Name"), HPos.CENTER);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Grid");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
