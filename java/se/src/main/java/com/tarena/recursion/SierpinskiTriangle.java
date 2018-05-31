package com.tarena.recursion;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.converter.BigIntegerStringConverter;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author SuShaohua
 * @date 2016/8/25 14:22
 * @description
 */
public class SierpinskiTriangle extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        SierpinskiTrianglePane trianglePane = new SierpinskiTrianglePane();
        TextField tfOrder = new TextField();
        tfOrder.setOnAction(
                e -> trianglePane.setOrder(Integer.parseInt(tfOrder.getText())));
        tfOrder.setPrefColumnCount(4);
        tfOrder.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(new Label("Enter an order: "), tfOrder);
        hBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(trianglePane);
        borderPane.setBottom(hBox);

        Scene scene = new Scene(borderPane, 300, 320);
        primaryStage.setTitle("SierpinskiTriangle");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.widthProperty().addListener(o -> trianglePane.paint());
        scene.heightProperty().addListener(o -> trianglePane.paint());

        BigInteger.probablePrime(10, new Random());
    }

    static class SierpinskiTrianglePane extends Pane {
        private int order = 0;

        public void setOrder(int Order) {
            this.order = order;
            paint();
        }

        SierpinskiTrianglePane() {}

        protected void paint() {
            Point2D p1 = new Point2D(getWidth() / 2, 10);
            Point2D p2 = new Point2D(10, getHeight() - 10);
            Point2D p3 = new Point2D(getWidth() - 10, getHeight() - 10);
            this.getChildren().clear();
            displayTriangles(order, p1, p2, p3);
        }

        private void displayTriangles(int order, Point2D p1, Point2D p2, Point2D p3) {
            if (0 == order){
                Polygon triangle = new Polygon();
                triangle.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
                triangle.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
                triangle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
                this.getChildren().add(triangle);
            }else {
                Point2D p12 = p1.midpoint(p2);
                System.out.println(p12.getX() + ", " + p12.getY());
                Point2D p23 = p2.midpoint(p3);
                System.out.println(p23.getX() + ", " + p23.getY());
                Point2D p31 = p3.midpoint(p1);
                System.out.println(p31.getX() + ", " + p31.getY());

                displayTriangles(order - 1, p1, p12, p31);
                displayTriangles(order - 1, p12, p2, p23);
                displayTriangles(order - 1, p31, p23, p3);
            }
        }
    }
}
