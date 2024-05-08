package Controllor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.dictionaryapp.Dictionary;
import org.example.dictionaryapp.DictionaryManagement;


public class EditController {

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab editWordTab;

    @FXML
    private TextField wordField;

    @FXML
    private TextField definitionField;

    @FXML
    private Button editButton;

    @FXML
    private Tab removeTab;

    @FXML
    private TextField englishWordToDeleteTextField;

    @FXML
    private TextField meaningToDeleteTextField;

    @FXML
    private Button deleteButton;

    private DictionaryManagement dictionaryManagement;
    @FXML
    private TextField deleteWordTextField;

    @FXML
    private void initialize() {
        // Phương thức này được gọi khi controller được khởi tạo
        // Bạn có thể thực hiện các công việc chuẩn bị ở đây
        Dictionary dictionary = new Dictionary();

        // Khởi tạo DictionaryManagement với đối tượng Dictionary vừa tạo
        dictionaryManagement = new DictionaryManagement(dictionary);
    }

    @FXML
    void editWord(ActionEvent event) {
        // Lấy từ và định nghĩa từ các trường nhập liệu
        String word = wordField.getText().trim();
        String definition = definitionField.getText().trim();


        // Sửa từ trong từ điển
        dictionaryManagement.editWordFromCommandline(word, definition);

        // Xuất dữ liệu từ điển vào tệp dictionaries.txt
        dictionaryManagement.dictionaryExportToFile("dictionary/data/dictionaries.txt");

        // Cập nhật giao diện hoặc thông báo cho người dùng sau khi chỉnh sửa
        // Ví dụ: có thể cập nhật lại danh sách từ trong giao diện nếu cần

        showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Sửa thành công", "Từ \"" + word + "\" vào từ điển.");
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }


    @FXML
    void deleteWord(ActionEvent event) {
        // Lấy từ cần xóa từ trường nhập liệu
        String wordToDelete = deleteWordTextField.getText().trim();

        // Xóa từ khỏi từ điển
        dictionaryManagement.deleteWordFromCommandline(wordToDelete);

        // Xuất dữ liệu từ điển vào tệp dictionaries.txt
        dictionaryManagement.dictionaryExportToFile("dictionary/data/dictionaries.txt");

        // Cập nhật giao diện hoặc thông báo cho người dùng sau khi xóa
        // Ví dụ: có thể cập nhật lại danh sách từ trong giao diện nếu cần
        showAlert(Alert.AlertType.INFORMATION, "Thông báo", "Xóa thành công", "Từ \"" + wordToDelete + "\" ra khỏi từ điển.");
    }
}
