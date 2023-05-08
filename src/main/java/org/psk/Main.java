package org.psk;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlLocation = getClass().getResource("view.fxml");
        System.out.println(fxmlLocation);
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        DictionaryController controller = new DictionaryController();
        loader.setController(controller);
        //Parent root = loader.load();
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
    }

    @Override
    public void stop() {

    }

}
