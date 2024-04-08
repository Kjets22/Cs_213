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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DisplayController {
    @FXML ImageView picture;
    @FXML ListView <String> list;
    @FXML Button quit, logout, back;
    @FXML Label caption, date;

    static List <user> users = new ArrayList<user>();
    ObservableList<String> obsList;
    public static List <String> pictures = new ArrayList<>();
    static user currentUser;
    static album currentAlbum;
    static photo currentPhoto;
    public static List <tag> tags = new ArrayList<tag>();
    List <String> tagStrings = new ArrayList<>();

    public void start() throws FileNotFoundException, ClassNotFoundException, IOException{
        users = readUserList();
        currentUser = loginController.getCurrentUser();
        currentAlbum = albumsController.getCurrentAlbum();
        currentPhoto = PhotosController.getCurrentPhoto();
        tags = getTagS();
        for(int i = 0; i < tags.size(); i++) {
            tagStrings.add(tags.get(i).getName() + ": " + tags.get(i).getValue());
        }

        picture.setImage(new Image (currentPhoto.getLocation()));
        caption.setText(currentPhoto.get_caption());
        date.setText(currentPhoto.getDate().getTime().toString());
        obsList = FXCollections.observableArrayList(tagStrings);
        list.setItems(obsList);
    }

    public static List<tag> getTagS(){
      List<tag> p = new ArrayList<tag>();
      for(int i = 0; i < users.size(); i++){
        if(users.get(i).get_username().equals(currentUser.get_username())){
          System.out.println("1");
            for(int j = 0; j < users.get(i).get_albums().size(); j++){
                if(users.get(i).get_albums().get(j).get_name().equals(currentAlbum.get_name())){
                  System.out.println("2");
                    for(int k = 0; k < users.get(i).get_albums().get(j).get_photos().size(); k++){
                        if(users.get(i).get_albums().get(j).get_photos().get(k).get_caption().equals(currentPhoto.get_caption())){
                          System.out.println("3");
                          p = users.get(i).get_albums().get(j).get_photos().get(k).getTags();
                        }
                    }
                }
            }
        }
    }
        return p;
    }  


    public void Back(ActionEvent event) throws IOException, ClassNotFoundException{
        writeUserList(users);
        pictures.clear();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Photos.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) back.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
        PhotosController Controller = loader.getController();
        Controller.start();
   }
  
      public void Logout(ActionEvent event) throws IOException{
        writeUserList(users);
        pictures.clear();
        Parent root = FXMLLoader.load(getClass().getResource("Username Page.fxml"));
  
        Stage window = (Stage) logout.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
  
   }
  
   public void Quit(ActionEvent event) throws FileNotFoundException, IOException{
        writeUserList(users);
        pictures.clear();
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
