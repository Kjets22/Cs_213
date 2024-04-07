package Pagesfx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import app.user;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;



public class loginController {

    @FXML Button quit;
    @FXML TextField username;

    List <user> users = new ArrayList<user>();

    public static user currentUser;
    

    public void Login(ActionEvent event) throws IOException, ClassNotFoundException{
        if(username.getText().equals("Admin")){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Admin.fxml"));
            Parent root = loader.load();
            Stage window = (Stage) username.getScene().getWindow();
            window.setScene(new Scene(root, 600, 400));
            AdminController Controller = loader.getController();
            Controller.start();
        }
        else {
            int k = 0;
            users = readUserList();
        for (int i = 0; i < users.size(); i++){
            if(username.getText().trim().equals(users.get(i).get_username())){
                k++;
                currentUser = users.get(i);
            }
        }
        if(k > 0){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Albums Page.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) username.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
        albumsController Controller = loader.getController();
        Controller.start();
        }
        else{
            Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Error");
			alert.setContentText("Must input a username");
			alert.show();
			return;
        }

        }
    }

    public void Quit(ActionEvent event) {
        Platform.exit();
    }

    @SuppressWarnings("unchecked")
    public static List<user> readUserList() throws FileNotFoundException, IOException, ClassNotFoundException{
        List <user> deserialized = new ArrayList<user>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("file.ser"))) {
          deserialized = (List<user>) ois.readObject();
          return deserialized;
        }
      }

      public static user getCurrentUser(){
        return currentUser;
      }

      public static String getCurrentUsername(){
        return currentUser.get_username();
      }

    

}
