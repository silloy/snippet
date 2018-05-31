package com.tarena.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author SuShaohua
 * @date 2016/8/24 14:46
 * @description
 */
public class ShowImage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new HBox(10);
        pane.setPadding(new Insets(5, 5, 5, 5));
        Image image = new Image("tarena/image/space.jpg");
        ImageView imageView1 = new ImageView(image);
        imageView1.setFitWidth(300);
        imageView1.setFitHeight(300);
        pane.getChildren().add(imageView1);

        ImageView imageView2 = new ImageView(image);
        imageView2.setFitHeight(100);
        imageView2.setFitWidth(100);
        pane.getChildren().add(imageView2);

        ImageView imageView3 = new ImageView(image);
        imageView3.setFitWidth(300);
        imageView3.setFitHeight(300);
        imageView3.setRotate(80);
        pane.getChildren().add(imageView3);
        Scene scene = new Scene(pane);
        primaryStage.setTitle("ShowImage");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
