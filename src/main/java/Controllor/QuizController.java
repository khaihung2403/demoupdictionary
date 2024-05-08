package Controllor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class QuizController {

    @FXML
    public Label question;

    private Label feedbackLabel;

    @FXML
    public Button opt1, opt2, opt3, opt4;


    static int counter = 0;
    static int correct = 0;
    static int wrong = 0;


    @FXML
    private void initialize() {
        loadQuestions();
    }

    private void loadQuestions() {
        if(counter == 0) {
            question.setText("1. Why should we study Mr.Tuyên");
            opt1.setText("Grumpy");
            opt2.setText("Good");
            opt3.setText("Not friendly");
            opt4.setText("Very Bad");
        } else if(counter == 1) {
            question.setText("2. Từ nào sau đây không phải là một danh từ?");
            opt1.setText("Book");
            opt2.setText("Run");
            opt3.setText("Chair");
            opt4.setText("Table");
        } else if(counter == 2) {
            question.setText("3. từ nào sau đây là từ viết sai chính tả?");
            opt1.setText("Necessary");
            opt2.setText("Acheive");
            opt3.setText("Environment");
            opt4.setText("Definitely");
        } else if(counter == 3) {
            question.setText("4. Cụm từ nào sau đây là một thành ngữ (idiom) có nghĩa là \"thất bại hoàn toàn\"");
            opt1.setText("Break the ice");
            opt2.setText("Kick the bucket");
            opt3.setText("Bite the bullet");
            opt4.setText("Miss the boat");
        } else if(counter == 4) {
            question.setText("5. \"She sings very _______.\" - Điền từ còn thiếu vào chỗ trống:");
            opt1.setText("Good");
            opt2.setText("Well");
            opt3.setText("Nice");
            opt4.setText("Beautiful");
        } else if(counter == 5) {
            question.setText("6. Chọn từ có nghĩa gần với từ \"efficient\":");
            opt1.setText("Effective");
            opt2.setText("Lazy");
            opt3.setText("Slow");
            opt4.setText("Careless");
        } else if(counter == 6) {
            question.setText("7. \"Could you please _______ the window? It's getting cold.\" - Điền từ còn thiếu vào chỗ trống:");
            opt1.setText("Close");
            opt2.setText("Opening");
            opt3.setText("Opens");
            opt4.setText("Open");
        } else if(counter == 7) {
            question.setText("8. Từ nào sau đây không phải là một phó từ?");
            opt1.setText("Quickly");
            opt2.setText("Softly");
            opt3.setText("Jump");
            opt4.setText("Happily");
        } else if(counter == 8) {
            question.setText("9. Điền từ thích hợp vào chỗ trống: \"I _____ to school every day.\"");
            opt1.setText("Go");
            opt2.setText("Going");
            opt3.setText("Goes");
            opt4.setText("Went");
        } else if(counter == 9) {
            question.setText("10. \"He speaks French _______.\" - Điền từ còn thiếu vào chỗ trống:");
            opt1.setText("Good");
            opt2.setText("Well");
            opt3.setText("Nice");
            opt4.setText("Better");
        }
    }

    @FXML
    public void opt1clicked(ActionEvent event) {
        checkAnswer(opt1.getText().toString());
        if(checkAnswer(opt1.getText().toString())) {
            correct = correct + 1;
            showFeedback("Correct");
            feedbackLabel.setTextFill(Color.GREEN);
        } else {
            wrong = wrong + 1;
            showFeedback("Incorrect");
            feedbackLabel.setTextFill(Color.RED);
        }
        if(counter == 9) {
            try {
                Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                thisstage.close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/dictionaryapp/result.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            counter++;
            loadQuestions();
        }
    }

    boolean checkAnswer(String answer) {

        if(counter==0) {
            if(answer.equals("Good")) {
                return true;
            } else {
                return false;
            }
        } else if(counter==1) {
            if(answer.equals("Book")) {
                return true;
            } else {
                return false;
            }
        } else if(counter==2) {
            if(answer.equals("Acheive")) {
                return true;
            } else {
                return false;
            }
        } else if(counter==3) {
            if(answer.equals("Miss the boat")) {
                return true;
            } else {
                return false;
            }
        } else if(counter==4) {
            if(answer.equals("Well")) {
                return true;
            } else {
                return false;
            }
        } else if(counter==5) {
            if(answer.equals("Effective")) {
                return true;
            } else {
                return false;
            }
        } else if(counter==6) {
            if(answer.equals("Open")) {
                return true;
            } else {
                return false;
            }
        } else if(counter==7) {
            if(answer.equals("Jump")) {
                return true;
            } else {
                return false;
            }
        } else if(counter==8) {
            if(answer.equals("Go")) {
                return true;
            } else {
                return false;
            }
        } else if(counter==9) {
            if(answer.equals("Well")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


    @FXML
    public void opt2clicked(ActionEvent event) {
        checkAnswer(opt2.getText().toString());
        if(checkAnswer(opt2.getText().toString())) {
            correct = correct + 1;
            showFeedback("Correct");
            feedbackLabel.setTextFill(Color.GREEN);
        } else {
            wrong = wrong + 1;
            showFeedback("Incorrect");
            feedbackLabel.setTextFill(Color.RED);
        }

        if(counter == 9) {
            try {
                Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                thisstage.close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/dictionaryapp/result.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            counter++;
            loadQuestions();
        }
    }

    @FXML
    public void opt3clicked(ActionEvent event) {
        checkAnswer(opt3.getText().toString());
        if(checkAnswer(opt3.getText().toString())) {
            correct = correct + 1;
            showFeedback("Correct");
            feedbackLabel.setTextFill(Color.GREEN);
        } else {
            wrong = wrong + 1;
            showFeedback("Incorrect");
            feedbackLabel.setTextFill(Color.RED);
        }

        if(counter == 9) {
            try {
                Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                thisstage.close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/dictionaryapp/result.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            counter++;
            loadQuestions();
        }
    }

    @FXML
    public void opt4clicked(ActionEvent event) {
        checkAnswer(opt4.getText().toString());
        if(checkAnswer(opt4.getText().toString())) {
            correct = correct + 1;
            showFeedback("Correct");
            feedbackLabel.setTextFill(Color.GREEN);
        } else {
            wrong = wrong + 1;
            showFeedback("Incorrect");
            feedbackLabel.setTextFill(Color.RED);
        }

        if(counter == 9) {
            try {
                Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                thisstage.close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/dictionaryapp/result.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            counter++;
            loadQuestions();
        }
    }
    private void showFeedback(String feedback) {
        if (feedbackLabel != null) {
            // Nếu có một nhãn phản hồi hiện tại, xóa nó khỏi AnchorPane trước khi hiển thị thông báo mới
            AnchorPane anchorPane = (AnchorPane) opt1.getParent();
            anchorPane.getChildren().remove(feedbackLabel);
        }

        // Tạo một nhãn phản hồi mới
        feedbackLabel = new Label(feedback);
        feedbackLabel.setLayoutX(415); // Điều chỉnh vị trí hiển thị
        feedbackLabel.setLayoutY(700); // Điều chỉnh vị trí hiển thị
        // Thiết lập màu và font cho nhãn phản hồi

        feedbackLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));

        // Thêm nhãn phản hồi vào AnchorPane
        AnchorPane anchorPane = (AnchorPane) opt1.getParent();
        anchorPane.getChildren().add(feedbackLabel);
    }


}
