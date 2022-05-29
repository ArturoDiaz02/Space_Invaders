package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    FXController controller;

    public Main() throws IOException {
        controller = new FXController();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Window.fxml"));
        fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Space Invaders");
        stage.show();
        stage.setHeight(503);
        stage.setWidth(900);
        stage.setResizable(false);
        controller.initialize(scene);

    }

    @Override
    public void init() throws ClassNotFoundException, IOException{


    }

}
