package org.example.dictionaryapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import javafx.scene.image.Image;
public class DictionaryApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/dictionaryapp/MainUI.fxml"));
        primaryStage.setTitle("Dictionary App");
        URL url = getClass().getResource("icon.png");
        Image image = new Image(url.toString());
        primaryStage.getIcons().add(image);
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
