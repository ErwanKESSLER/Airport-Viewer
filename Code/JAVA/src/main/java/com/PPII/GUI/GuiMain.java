package com.PPII.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;


public class GuiMain extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL file=getClass().getClassLoader().getResource("gui.fxml");
        if (file==null){
            throw new Error("Missing the GUI");
        }
        Parent root = FXMLLoader.load(file);

        primaryStage.setTitle("Planes Flights");
        primaryStage.setScene(new Scene(root, 1600, 800));
        primaryStage.show();

    }
}