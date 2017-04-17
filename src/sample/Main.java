package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class Main extends Application {

    WorkerThread workerThread;
    Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Clock");
        primaryStage.setScene(new Scene(root, 396, 236));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
