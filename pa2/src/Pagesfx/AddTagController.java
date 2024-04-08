package Pagesfx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import app.album;
import app.photo;
import app.tag;
import app.user;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTagController {
    @FXML Button quit, logout, back, add;
    @FXML TextField value;
    @FXML ChoiceBox <String> type;

    List <user> users = new ArrayList<user>();
    ObservableList<String> obsList;
    public static List <String> tags = new ArrayList<>();
    public static List <tag> tagSS = EditTagsController.getTagS();
    static user currentUser;
    static photo currentPhoto;
    static album currentAlbum;
    public static List<String> tagTypes = new ArrayList<>();

    public void start() throws FileNotFoundException, ClassNotFoundException, IOException{
        users = readUserList();
        currentUser = loginController.getCurrentUser();
        currentAlbum = albumsController.getCurrentAlbum();
        currentPhoto = PhotosController.getCurrentPhoto();
        tagTypes.add("Location");
        tagTypes.add("Person");
        tagTypes.add("Genre");
        tagTypes.add("Event");
        type.setItems(FXCollections.observableArrayList(tagTypes));
    }


    public void addTag(ActionEvent event){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).get_username().equals(currentUser.get_username())){
                for(int j = 0; j < users.get(i).get_albums().size(); j++){
                    if(users.get(i).get_albums().get(j).get_name().equals(currentAlbum.get_name())){
                        for(int k = 0; k < users.get(i).get_albums().get(j).get_photos().size(); k++){
                            if(users.get(i).get_albums().get(j).get_photos().get(k).get_caption().equals(currentPhoto.get_caption())){
                            tag adding = new tag(type.getValue().toString(), value.getText());
                            tagSS.add(adding);
                            users.get(i).get_albums().get(j).get_photos().get(k).setTags(tagSS);
                            value.clear();
                            Alert alert = new Alert(AlertType.CONFIRMATION);
			                alert.setHeaderText("TAG ADDED");
			                alert.setContentText("You have succesfully added a tag to the photo");
			                alert.show();
                            }
                        }
                    }
                }
            }
        }
    }

    public void Back(ActionEvent event) throws IOException, ClassNotFoundException{
        writeUserList(users);
        tags.clear();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EditTags.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) back.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
        EditTagsController Controller = loader.getController();
        Controller.start();
   }
  
      public void Logout(ActionEvent event) throws IOException{
        writeUserList(users);
        tags.clear();
        Parent root = FXMLLoader.load(getClass().getResource("Username Page.fxml"));
  
        Stage window = (Stage) logout.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
  
   }
  
   public void Quit(ActionEvent event) throws FileNotFoundException, IOException{
        writeUserList(users);
        tags.clear();
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
