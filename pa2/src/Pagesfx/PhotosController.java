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
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PhotosController {
    @FXML ListView <String> photos = new ListView<String>();
    @FXML Button add, rid, rename, logout, quit, back;
    @FXML Label albumName;

    List <user> users = new ArrayList<user>();
    ObservableList<String> obsList;
    public static List <String> pictures = new ArrayList<>();
    static user currentUser;
    static album currentAlbum;

    public void start() throws IOException, ClassNotFoundException{
        users = readUserList();
        currentUser = loginController.getCurrentUser();
        currentAlbum = albumsController.getCurrentAlbum();
        pictures = getPhotoNames(currentAlbum);
        albumName.setText("Photos in " + currentAlbum.get_name());
        pictures = getPhotoNames(currentAlbum);
        obsList = FXCollections.observableArrayList(pictures);
        

        photos.setCellFactory(param -> new ListCell<String>() {
          private ImageView imageView = new ImageView();
          @Override
          public void updateItem(String name, boolean empty) {
              super.updateItem(name, empty);
              if (empty) {
                setText(null);
                setGraphic(null);
            }
            else{
              for(int i = 0; i < currentAlbum.get_photos().size(); i++){
                if(name.equals(currentAlbum.get_photos().get(i).get_caption())){
                imageView.setImage(new Image(currentAlbum.get_photos().get(i).getLocation()));
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                setText(name);
                setGraphic(imageView);
                }
              }
            }
          }
      });

      photos.setItems(obsList);
    }
    

    public void add_photo(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException{
      writeUserList(users);
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("CreatePhoto.fxml"));
      Parent root = loader.load();
      Stage window = (Stage) add.getScene().getWindow();
      window.setScene(new Scene(root, 600, 400));
      CreatePhotoController controller = loader.getController();
      controller.start();
    }

    public List<String> getPhotoNames(album a){
      List<String> p = new ArrayList<String>();
      for(int i = 0; i < users.size(); i++){
        if(users.get(i).get_username().equals(currentUser.get_username())){
            for(int j = 0; j < users.get(i).get_albums().size(); j++){
                if(users.get(i).get_albums().get(j).get_name().equals(currentAlbum.get_name())){
                    for(int k = 0; k < users.get(i).get_albums().get(j).get_photos().size(); k++){
                        p.add(users.get(i).get_albums().get(j).get_photos().get(k).get_caption());
                    }
                }
            }
        }
    }
        return p;
    }  

  

    public void openSlideshow(ActionEvent event) throws IOException{
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("Slideshow.fxml"));
      Parent root = loader.load();
      Stage window = (Stage) add.getScene().getWindow();
      window.setScene(new Scene(root, 600, 400));
      SlideshowController controller = loader.getController();
      controller.start();
    }

    public static String getCurrentAlbumName(){
      return currentAlbum.get_name();
    }

    public static album getCurrentAlbum(){
      return currentAlbum;
    }

    public void Back(ActionEvent event) throws IOException, ClassNotFoundException{
      writeUserList(users);
      pictures.clear();
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("Albums Page.fxml"));
      Parent root = loader.load();
      Stage window = (Stage) back.getScene().getWindow();
      window.setScene(new Scene(root, 600, 400));
      albumsController Controller = loader.getController();
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
