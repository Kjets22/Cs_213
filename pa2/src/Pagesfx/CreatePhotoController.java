package Pagesfx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import java.util.Calendar;

import app.album;
import app.photo;
import app.tag;
import app.user;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreatePhotoController {
    @FXML Button createPhoto, back, quit, logout;
    @FXML TextField loc, caption;
    @FXML Label albumlabel;

    List <user> users = new ArrayList<user>();
    List <photo> photos;
    List<tag> tags = new ArrayList<tag>();

    user currentUser;
    album currentAlbum;
    String currentAlbumName;

    public void start() throws FileNotFoundException, ClassNotFoundException, IOException{
        users = readUserList();
        currentUser = loginController.getCurrentUser();
        currentAlbumName = PhotosController.getCurrentAlbumName();
        albumlabel.setText("Add album to " + currentAlbumName);
    }


    public void create_photo(ActionEvent event) throws IOException, ClassNotFoundException{
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).get_username().equals(currentUser.get_username())){
                for(int j = 0; j < users.get(i).get_albums().size(); j++){
                    if(users.get(i).get_albums().get(j).get_name().equals(currentAlbumName)){
                        List <tag> tags = new ArrayList<>();
                        photo add = new photo(loc.getText(), Calendar.getInstance());
                        add.add_album(currentAlbum);
                        add.change_capation(caption.getText().trim());
                        add.setTags(tags);
                        users.get(i).get_albums().get(j).add_photo(add);
                        loc.clear();
                        caption.clear();
                        Alert alert = new Alert(AlertType.CONFIRMATION);
			            alert.setHeaderText("PHOTO ADDED");
			            alert.setContentText("You have succesfully added a photo to " + currentAlbumName);
			            alert.show();
                    }
                }
            }
        }
    }

    public void back(ActionEvent event) throws IOException, ClassNotFoundException{
        writeUserList(users);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Photos.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) quit.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
        PhotosController Controller = loader.getController();
        Controller.start();

   }

   public void Logout(ActionEvent event) throws IOException{
    writeUserList(users);
    loc.clear();
    caption.clear();
    Parent root = FXMLLoader.load(getClass().getResource("Username Page.fxml"));
    Stage window = (Stage) logout.getScene().getWindow();
    window.setScene(new Scene(root, 600, 400));
}

    public void quit(ActionEvent event) throws FileNotFoundException, IOException{
        writeUserList(users);
        Platform.exit();
   }


    public static void writeUserList(List <user> guys) throws FileNotFoundException, IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("file.ser"))) {
          oos.writeObject(guys);
         oos.close();
         System.out.println("Data has been serialized successfully");
        }
      }

    @SuppressWarnings("unchecked")
    public static List<user> readUserList() throws FileNotFoundException, IOException, ClassNotFoundException{
        List <user> deserialized = new ArrayList<user>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("file.ser"))) {
          deserialized = (List<user>) ois.readObject();
          return deserialized;
        }
      }




}