package Controllor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.example.dictionaryapp.DictionaryCommandline;
import org.example.dictionaryapp.DictionaryManagement;
import org.example.dictionaryapp.Trie;
import org.example.dictionaryapp.TextSpeech;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> listView;

    @FXML
    private TextArea definitionTextArea;

    @FXML
    private TextField englishWordField;

    @FXML
    private TextField vietnameseDefinitionField;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button editButton;

    @FXML
    private Button speakButton; // Đã đổi tên thành "Phát âm"

    @FXML
    private ListView<String> suggest;

    private DictionaryManagement dictionaryManagement;
    private DictionaryCommandline dictionaryCommandline;
    private org.example.dictionaryapp.Dictionary dictionary;
    private Trie trie;

    private TextSpeech textSpeech;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dictionary = new org.example.dictionaryapp.Dictionary();
        trie = new Trie(); // Initialize Trie object
        dictionaryManagement = new DictionaryManagement(dictionary, trie); // Pass both Dictionary and Trie objects
        dictionaryManagement.insertFromFile("data/dictionaries.txt");
        dictionaryCommandline = new DictionaryCommandline();
        textSpeech = new TextSpeech();
        suggest = listView; // Assign suggest to listView

        // Add event to suggest when typing a word
        searchField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                handleLookup();
            }
        });
    }

    @FXML
    private void handleLookup() {
        String keyword = searchField.getText().trim().toLowerCase();
        if (!keyword.isEmpty()) {
            List<String> suggestions = dictionaryManagement.suggestKeywords(keyword);
            listView.setItems(FXCollections.observableArrayList(suggestions));
        } else {
            listView.getItems().clear();
        }
    }

    @FXML
    private void handleSuggestionClicked() {
        String selectedWord = listView.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            searchField.setText(selectedWord);
            handleLookup(); // Gọi lại để cập nhật kết quả tìm kiếm
        }
    }

    @FXML
    void handleSearchFieldAction(ActionEvent event) {
        String wordToSearch = searchField.getText().trim();
        String definition = dictionaryManagement.dictionarySearcher(wordToSearch);
        definitionTextArea.setText(definition);

        // Kiểm tra xem từ đã tồn tại trong từ điển hay không
        if (!definition.equals("Không tìm thấy từ trong từ điển.")) {
            // Nếu từ tồn tại, thêm nó vào ListView để hiển thị kết quả
            listView.getItems().clear(); // Xóa tất cả các mục cũ
            //listView.getItems().add(definition);
            // wordToSearch +
        } else {
            // Nếu từ không tồn tại, thông báo cho người dùng
            listView.getItems().clear(); // Xóa tất cả các mục cũ
            listView.getItems().add("Không tìm thấy từ trong từ điển.");
        }

    }


    @FXML
    private void handleCloseMain(ActionEvent event) {
        // Lấy Stage hiện tại của Scene và đóng nó
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleAdd(ActionEvent event) {
        try {
            // Tạo một FXMLLoader mới để tải UI thêm từ
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/dictionaryapp/AddUI.fxml"));
            Parent root = loader.load();

            // Tạo một Scene mới với UI thêm từ
            Scene scene = new Scene(root);

            // Lấy Stage từ nút hiện tại và đóng nó
            Stage mainStage = (Stage) addButton.getScene().getWindow();
            mainStage.close();

            // Tạo một Stage mới và hiển thị Scene thêm từ
            Stage addStage = new Stage();
            addStage.setScene(scene);
            addStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEditButtonAction(ActionEvent event) {
        try {
            // Tải giao diện chỉnh sửa từ edit.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/dictionaryapp/Edit.fxml"));
            Parent root = loader.load();

            // Tạo một Scene mới với giao diện chỉnh sửa
            Scene scene = new Scene(root);

            // Tạo một Stage mới và hiển thị Scene giao diện chỉnh sửa
            Stage editStage = new Stage();
            editStage.setScene(scene);
            editStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void openAPI() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/dictionaryapp/API.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSpeakButtonAction(ActionEvent event) {
        String wordToSpeak = searchField.getText().trim();
        textSpeech.speak(wordToSpeak);
    }

    @FXML
    private void openGameInterface() {
        try {
            // Load the Game.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/dictionaryapp/Game.fxml"));
            Parent root = loader.load();

            // Create a new stage and display the Game interface
            Stage gameStage = new Stage();
            gameStage.setScene(new Scene(root));
            gameStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

