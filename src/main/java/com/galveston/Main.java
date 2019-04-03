package com.galveston;

import com.galveston.objectFactory.RunTimeObjectHolder;
import com.galveston.objectFactory.RunTimeObjectLoader;
import com.galveston.services.LoaderImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application implements RunTimeObjectLoader {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Adding the Address of fxml file.
        URL url = new File("src/main/java/com/galveston/sample.fxml").toURL();
        //FXMLLoader fxmlLoader = FXMLLoader.load(url);
        //fxmlLoader.setController();
        Parent root = FXMLLoader.load(url);
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setTitle("Hello World");

        //Remove windows default toolbar
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        //Movable window
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        //move around here
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });


        primaryStage.setScene(new Scene(root));


        primaryStage.show();
        // Load the Object In ruTime Memory from Files
        fireUp();

    }


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void fireUp() {
        RunTimeObjectHolder.getInstance();
        LoaderImpl li = new LoaderImpl();
        try {
            li.userFireUpTimeLoader();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
