package org.psk;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddWordWindow extends Stage {
    public AddWordWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/add_remove_view.fxml"));
        try {
            Parent root = fxmlLoader.load();
            AddWordController controller = fxmlLoader.getController();
            fxmlLoader.setController(controller);

            Scene scene = new Scene(root);
            setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}