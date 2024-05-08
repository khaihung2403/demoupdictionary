package Controllor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class GameController {

    @FXML
    private Button playquizbtn;

    @FXML
    private Label label;

    @FXML
    private void handlePlayButtonAction() {
        try {
            // Load the result.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/dictionaryapp/Quiz.fxml"));
            Parent root = loader.load();

            // Create a new stage and display the result interface
            Stage resultStage = new Stage();
            resultStage.setScene(new Scene(root));
            resultStage.show();

            // Close the current stage of the Game interface
            Stage currentStage = (Stage) playquizbtn.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
