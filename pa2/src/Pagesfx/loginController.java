package Pagesfx;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;



public class loginController {

    @FXML Button quit;
    @FXML TextField username;

    


    public void Login(ActionEvent event) throws IOException, ClassNotFoundException{
        if(username.getText().equals("Admin")){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Admin.fxml"));
            Parent root = loader.load();
            Stage window = (Stage) username.getScene().getWindow();
            window.setScene(new Scene(root, 750, 500));
            AdminController Controller = loader.getController();
            Controller.start();
        }
        else{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Albums Page.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) username.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500));
        albumsController Controller = loader.getController();
        Controller.start();
        }
    }

    public void Quit(ActionEvent event) {
        Platform.exit();
    }

    

}
