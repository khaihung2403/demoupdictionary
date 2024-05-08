package Controllor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import org.example.dictionaryapp.GoogleAPI;
import org.example.dictionaryapp.TextSpeech;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class APIController {

    @FXML
    private ListView<String> listView;

    @FXML
    private TextArea textArea;

    private TextSpeech textSpeech;

    public void initialize() {
        // Initialize TextSpeech
        textSpeech = new TextSpeech();
    }

    public void translate(ActionEvent event) {
        String text = textArea.getText();
        try {
            // Thực hiện dịch văn bản
            String translatedText = GoogleAPI.translate(text, "en", "vi");
            // Hiển thị kết quả dịch trong ô "Dịch Nghĩa"
            listView.getItems().clear(); // Xóa các mục cũ
            List<String> lines = splitString(translatedText, 97); // Số ký tự trên mỗi dòng

            // Thêm các dòng đã chia vào ListView
            listView.getItems().addAll(lines);

            //listView.getItems().add(translatedText);

            // Đọc văn bản đã dịch
            /*if (textSpeech != null) {
                textSpeech.speak(translatedText);
            } else {
                System.out.println("TextSpeech object is not initialized.");
            }*/
        } catch (IOException e) {
            e.printStackTrace();
            // Xử lý lỗi nếu có
            listView.getItems().clear(); // Xóa các mục cũ

            listView.getItems().add("Error occurred while translating.");
        }
    }

    @FXML
    void read(ActionEvent event) {
        // Phương thức này sẽ được gọi khi nhấn nút "Đọc"
        String textToRead = textArea.getText().trim(); // Lấy văn bản từ textArea
        if (textToRead.isEmpty()) {
            System.out.println("No text to read.");
            return;
        }

        if (textSpeech != null) {
            textSpeech.speak(textToRead); // Gọi phương thức speak để đọc văn bản
        } else {
            System.out.println("TextSpeech object is not initialized."); // Xử lý trường hợp textSpeech chưa được khởi tạo
        }
    }
    // Hàm chia nhỏ chuỗi thành các phần theo số ký tự mong muốn trên mỗi dòng
    private List<String> splitString(String text, int maxLength) {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < text.length(); i += maxLength) {
            lines.add(text.substring(i, Math.min(i + maxLength, text.length())));
        }
        return lines;
    }
}

