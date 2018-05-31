package com.tarena.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * @author SuShaohua
 * @date 2016/8/24 15:08
 * @description
 */
public class ShowFlowPane extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(11, 12, 13, 14));
        pane.setVgap(5);
        pane.setHgap(5);
        pane.getChildren().addAll(new Label("First Name: "), new TextField(), new Label("MI: "));
        TextField tfmi = new TextField();
        tfmi.setPrefColumnCount(1);
        pane.getChildren().addAll(tfmi, new Label("Last Name: "), new TextField());

        Scene scene = new Scene(pane, 350, 250);
        primaryStage.setTitle("ShowFlowImage");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
