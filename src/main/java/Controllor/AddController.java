package Controllor;

import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.dictionaryapp.Dictionary;
import org.example.dictionaryapp.DictionaryManagement;

import java.io.IOException;

public class AddController {

    @FXML
    private TextField wordField;

    @FXML
    private TextField definitionField;

    @FXML
    private Button backButton; // Đặt id cho nút "Back" trong file FXML


    private DictionaryManagement dictionaryManagement;

    public void initialize() {
        // Tạo một đối tượng Dictionary trước
        Dictionary dictionary = new Dictionary();

        // Khởi tạo DictionaryManagement với đối tượng Dictionary vừa tạo
        dictionaryManagement = new DictionaryManagement(dictionary);
    }

    @FXML
    void addWord(ActionEvent event) {
        // Lấy từ và định nghĩa từ các trường nhập liệu
        String word = wordField.getText().trim();
        String definition = definitionField.getText().trim();

        // Thêm từ vào từ điển
        dictionaryManagement.addWordFromCommandline(word, definition);


        // Xuất dữ liệu từ điển vào tệp dictionaries.txt
        dictionaryManagement.dictionaryExportToFile("dictionary/data/dictionaries.txt");

        // Hiển thị cảnh báo khi từ đã được thêm thành công
        showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Thêm từ thành công", "Từ \"" + word + "\" đã được thêm vào từ điển.");

        // Sau khi thêm, có thể cập nhật giao diện hoặc thông báo cho người dùng
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    // Sau khi thêm từ thành công


    @FXML
    void back(ActionEvent event) {
        try {
            // Tạo một đối tượng Stage từ sự kiện ActionEvent
            Stage stage = (Stage) backButton.getScene().getWindow();

            // Tải file FXML của giao diện MainUI.fxml
            Parent mainUI = FXMLLoader.load(getClass().getResource("/org/example/dictionaryapp/MainUI.fxml"));

            // Tạo một Scene mới với giao diện MainUI
            Scene scene = new Scene(mainUI);

            // Đặt Scene cho Stage và hiển thị lại giao diện MainUI
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println("Error loading MainUI.fxml: " + e.getMessage());
        }
    }
}
