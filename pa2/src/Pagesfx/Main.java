package Pagesfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Username Page.fxml"));
        Parent root = loader.load();
        Scene opening = new Scene(root, 400, 300);
        primaryStage.setScene(opening);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}


