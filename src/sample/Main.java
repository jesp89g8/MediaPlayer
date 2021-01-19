package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @ Group Jesper Raheela Zia and Fei
 * @ create 2021-01-05-08.39
 * @ grade CS20_EASV_SØNDERBORG
 * @ Description This is the Main class which is the start of the application.
 * @ Version 1.0
 *
 */

public class Main extends Application {

    final String GUI = "MediaPlayer.fxml";
    final String TITLE = "MediaPlayer";
    final int RES_WIDTH = 1000;
    final int RES_HEIGHT = 600;

    public static void main(String[] args) {
        launch(args);


    }

    /**
     * This is the basic part of showing the main window
     * @param primaryStage Stage the base stage of the application
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("loading the scene...");
        Parent root = FXMLLoader.load(getClass().getResource(GUI));
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(root, RES_WIDTH, RES_HEIGHT));
        primaryStage.show();
        System.out.println("The scene have been launch...");


    }
}
