package Controllor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

public class ResultController {

    @FXML
    public Label remark, marks, markstext, correcttext, wrongtext;

    @FXML
    public ProgressIndicator correct_progress, wrong_progress;

    @FXML
    public void initialize() {
        correcttext.setText("Correct Answers : " + String.valueOf(QuizController.correct));
        wrongtext.setText("Incorrect Answers : " + String.valueOf(QuizController.wrong));

        marks.setText(QuizController.correct + "/10");

        float correctf = (float) QuizController.correct/10;
        correct_progress.setProgress(correctf);

        float wrongf = (float) QuizController.wrong/10;
        wrong_progress.setProgress(wrongf);

        int correct = QuizController.correct;

        markstext.setText(correct + " Marks Scored");

        if (correct<1) {
            remark.setText("Oh no..! You have failed the quiz. It seems that you need to improve your general knowledge. Practice daily! Check your results here.");
        } else if (correct==1) {
            remark.setText("Congratulations! You have passed the quiz with full marks because of your hardwork and dedication towards studies. Keep it up! Check your results here.");
        }
    }
}
