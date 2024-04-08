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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PhotosController {
    @FXML ListView <String> photos = new ListView<String>();
    @FXML Button add, rid, recaption, logout, quit, back, disp, edit, slide;
    @FXML Label albumName;
    @FXML TextField newName;

    List <user> users = new ArrayList<user>();
    ObservableList<String> obsList;
    public static List <String> pictures = new ArrayList<>();
    static user currentUser;
    static album currentAlbum;
    static photo currentPhoto;

    public void start() throws IOException, ClassNotFoundException{
        users = readUserList();
        currentUser = loginController.getCurrentUser();
        currentAlbum = albumsController.getCurrentAlbum();
        pictures = getPhotoNames(currentAlbum);
        albumName.setText("Photos in " + currentAlbum.get_name());
        pictures = getPhotoNames(currentAlbum);
        obsList = FXCollections.observableArrayList(pictures);
        photos.setItems(obsList);

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
    }

    public void update() throws IOException, ClassNotFoundException{
      ObservableList <String> picList = FXCollections.observableArrayList(pictures);
      photos.setItems(picList);
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

    public void removePhoto(ActionEvent event) throws ClassNotFoundException, IOException {
      int index = searchPhotos(photos.getSelectionModel().getSelectedItem());
      for(int i = 0; i < users.size(); i++){
      if(users.get(i).get_username().equals(currentUser.get_username())){
                  for(int j = 0; j < users.get(i).get_albums().size(); j++){
                      if(users.get(i).get_albums().get(j).get_name().equals(currentAlbum.get_name())){
                            users.get(i).get_albums().get(j).get_photos().remove(index);
                            pictures.remove(index);
                            update();
                      }
                  }
              }
          }
      }

      public void changeCaption(ActionEvent event) throws ClassNotFoundException, IOException{
        int index = searchPhotos(photos.getSelectionModel().getSelectedItem());
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).get_username().equals(currentUser.get_username())){
              for(int j = 0; j < users.get(i).get_albums().size(); j++){
                if(users.get(i).get_albums().get(j).get_name().equals(currentAlbum.get_name())){
                      users.get(i).get_albums().get(j).get_photos().get(i).change_capation(newName.getText());
                      newName.clear();
                      update();
                      Alert alert = new Alert(AlertType.INFORMATION);
			                alert.setHeaderText("CAPTION CHANGED");
			              alert.setContentText("Log out then back in to see the caption change");
	                alert.show();
                }
            }
            }
            }
               
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

    public void displayPic(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException{
      writeUserList(users);
        int index = searchPhotos(photos.getSelectionModel().getSelectedItem());
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).get_username().equals(currentUser.get_username())){
                currentPhoto = currentAlbum.get_photos().get(index);
  
            }
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DisplayPage.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) disp.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
        DisplayController Controller = loader.getController();
        pictures.clear();
        Controller.start();
    }

    public void editTags(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException{
      writeUserList(users);
      int index = searchPhotos(photos.getSelectionModel().getSelectedItem());
      for(int i = 0; i < users.size(); i++){
          if(users.get(i).get_username().equals(currentUser.get_username())){
              currentPhoto = currentAlbum.get_photos().get(index);
              System.out.println("Yes");
          }
      }
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("EditTags.fxml"));
      Parent root = loader.load();
      Stage window = (Stage) disp.getScene().getWindow();
      window.setScene(new Scene(root, 600, 400));
      EditTagsController Controller = loader.getController();
      pictures.clear();
      Controller.start();
    }

    public int searchPhotos(String name){
      for(int i = 0; i < currentAlbum.get_photos().size(); i++){
          if(currentAlbum.get_photos().get(i).get_caption().equals(name)){
              return i;
          }
      }
      return -1;
  }

    public static String getCurrentAlbumName(){
      return currentAlbum.get_name();
    }

    public static album getCurrentAlbum(){
      return currentAlbum;
    }

    public static photo getCurrentPhoto(){
      return currentPhoto;
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
