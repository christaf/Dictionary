import org.junit.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.psk.DictionaryController;
import org.psk.DictionaryView;

import static org.junit.Assert.assertEquals;

public class DictionaryViewTest {

    @Test
    public void testCreateSearchUI() {

//        DictionaryController controller = DictionaryController.getInstance(new Stage());
//        DictionaryView view = new DictionaryView();

//        Scene searchScene = view.createSearchUI(controller);

        // Verify the search UI is created correctly
//        VBox searchPane = (VBox) searchScene.getRoot();
//        assertEquals(10, searchPane.getSpacing());
//        assertEquals(10, searchPane.getPadding().getTop());
//        assertEquals(10, searchPane.getPadding().getBottom());
//        assertEquals(10, searchPane.getPadding().getLeft());
//        assertEquals(10, searchPane.getPadding().getRight());

//        HBox searchBox = (HBox) searchPane.getChildren().get(0);
//        assertEquals(10, searchBox.getSpacing());

//        TextField searchField = (TextField) searchBox.getChildren().get(0);
//        assertEquals("Enter a word to search", searchField.getPromptText());

//        ListView<String> wordList = (ListView<String>) searchPane.getChildren().get(1);
        //assertEquals(200, wordList.getPrefWidth());

        // Verify search results are displayed correctly
//        ObservableList<String> results = FXCollections.observableArrayList("apple", "banana", "cherry");
//        view.displaySearchResults(results);
//        assertEquals(3, wordList.getItems().size());
//        assertEquals("apple", wordList.getItems().get(0));
//        assertEquals("banana", wordList.getItems().get(1));
//        assertEquals("cherry", wordList.getItems().get(2));
    }

    @Test
    public void testCreateAddWordUI() {
//        DictionaryController controller = DictionaryController.getInstance(new Stage());
//        DictionaryView view = new DictionaryView();

//        Scene addScene = view.createAddWordUI(controller);

        // Verify the add word UI is created correctly
//        VBox addPane = (VBox) addScene.getRoot();
//        assertEquals(10, addPane.getSpacing());
//        assertEquals(10, addPane.getPadding().getTop());
//        assertEquals(10, addPane.getPadding().getBottom());
//        assertEquals(10, addPane.getPadding().getLeft());
//        assertEquals(10, addPane.getPadding().getRight());

//        TextField addField = (TextField) addPane.getChildren().get(0);
//        assertEquals("Enter a word to add", addField.getPromptText());

//        HBox buttonBox = (HBox) addPane.getChildren().get(1);
//        assertEquals(10, buttonBox.getSpacing());

//        assertEquals("Save Word", ((javafx.scene.control.Button) buttonBox.getChildren().get(0)).getText());
//        assertEquals("Cancel", ((javafx.scene.control.Button) buttonBox.getChildren().get(1)).getText());
    }

}
