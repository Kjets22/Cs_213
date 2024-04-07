package Pagesfx;

import java.io.*;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;

import app.album;
import app.user;

public class albumsController {

    @FXML Label username, pop;
    @FXML Button quit, createAlbum, logout, delete, open;
    @FXML ListView <String> list = new ListView<>();
    @FXML TextField newAlbum;
    
    List <user> users = new ArrayList<user>();
    ObservableList<String> obsList;
    public static List <String> albums = new ArrayList<>();
    user currentUser;
    public static album currentAlbum;
    private final Image IMAGE_FOLDER = new Image("https://i.pinimg.com/originals/37/29/61/3729617452425f23b079bb0de458293a.png");

    public void openAlbum(ActionEvent event) throws IOException, ClassNotFoundException{
        writeUserList(users);
        int index = searchAlbums(list.getSelectionModel().getSelectedItem());
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).get_username().equals(currentUser.get_username())){
                currentAlbum = users.get(i).get_albums().get(index);
                System.out.println(currentAlbum.get_name());
            }
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Photos.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) username.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
        PhotosController Controller = loader.getController();
        albums.clear();
        Controller.start();
    }
    

    public void start() throws IOException, ClassNotFoundException{
        currentUser = loginController.getCurrentUser();
        System.out.println(currentUser.get_username());
        users = readUserList();
        albums = getAlbumNames(currentUser, albums);
        username.setText(loginController.getCurrentUsername());
        obsList = FXCollections.observableArrayList(albums);
        list.setItems(obsList);
        
       
        list.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(IMAGE_FOLDER);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    setText(name);
                    setGraphic(imageView);
                }
            }
        });
    }

    public void update() throws IOException, ClassNotFoundException{
        ObservableList <String> usersList = FXCollections.observableArrayList(albums);
        list.setItems(usersList);
    }
   

    public void Logout(ActionEvent event) throws IOException{
        writeUserList(users);
        albums.clear();
        Parent root = FXMLLoader.load(getClass().getResource("Username Page.fxml"));

        Stage window = (Stage) quit.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));

   }

   public void Quit(ActionEvent event) throws FileNotFoundException, IOException{
        writeUserList(users);
        albums.clear();
        Platform.exit();
   }

   public void makeAlbum(ActionEvent event) throws ClassNotFoundException, IOException {
    if (searchAlbums(newAlbum.getText().trim()) == -1){
        for(int i = 0; i < users.size(); i++){
        if(users.get(i).get_username().equals(currentUser.get_username())){
            users.get(i).add_album(newAlbum.getText().trim());
        }
        }
        albums.add(newAlbum.getText().trim());
        newAlbum.clear();
        update();
    }
   }

   public void deleteAlbum(ActionEvent event) throws ClassNotFoundException, IOException{
    int index = searchAlbums(list.getSelectionModel().getSelectedItem());
    for(int i = 0; i < users.size(); i++){
        if(users.get(i).get_username().equals(currentUser.get_username())){
            users.get(i).get_albums().remove(index);
            albums.remove(index);
            update();
        }
        }
           
    }

   public int searchAlbums(String name){
    for(int i = 0; i < albums.size(); i++){
        if(albums.get(i).equals(name)){
            return i;
        }
    }
    return -1;
}


    public List<String> getAlbumNames(user u, List<String> album){
        for(int i = 0; i < u.get_albums().size(); i++){
                album.add(u.get_albums().get(i).get_name());
        }
        return album;
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

      public static album getCurrentAlbum(){
            return currentAlbum;
      }
    

}
