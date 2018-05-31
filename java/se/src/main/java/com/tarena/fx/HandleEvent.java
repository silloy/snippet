package com.tarena.fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @author SuShaohua
 * @date 2016/8/24 16:03
 * @description
 */
public class HandleEvent extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox pane = new HBox(10);
        pane.setAlignment(Pos.CENTER);
        Button btok =  new Button("OK");
        Button btcancel = new Button("Cancel");
        OKHandleClass handle1 = new OKHandleClass();
        btok.setOnAction(handle1);
        pane.getChildren().addAll(btok, btcancel);
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Pane");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

class OKHandleClass implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Press OK");
    }
}