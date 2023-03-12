package org.psk;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a button
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(e -> System.out.println("Witaj okrutny świecie!"));

        // Add the button to a layout pane
        StackPane root = new StackPane();
        root.getChildren().add(btn);

        // Create a scene with the layout pane
        Scene scene = new Scene(root, 300, 250);

        // Set the scene on the primary stage and show it
        primaryStage.setTitle("My JavaFX App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}