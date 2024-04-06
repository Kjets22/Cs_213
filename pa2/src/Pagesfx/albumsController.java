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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;

import app.User;

public class albumsController {

    @FXML Label username;
    @FXML Button quit, createAlbum, logout;
    @FXML ListView <String> list = new ListView<>();
    @FXML TextField newAlbum;
    
    List <User> users = new ArrayList<User>();
    ObservableList<String> obsList;
    public static List <String> albums = new ArrayList<>();
    User currentUser;
    

    public void start() throws IOException, ClassNotFoundException{
        currentUser = loginController.getCurrentUser();
        System.out.println(currentUser.getUsername());
        users = readUserList();
        albums = getAlbumNames(currentUser, albums);
        username.setText(loginController.getCurrentUsername());
        obsList = FXCollections.observableArrayList(albums);
        list.setItems(obsList);
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
        if(users.get(i).getUsername().equals(currentUser.getUsername())){
            users.get(i).add_album(newAlbum.getText().trim());
        }
        }
        albums.add(newAlbum.getText().trim());
        newAlbum.clear();
        update();
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

    public List<String> getAlbumNames(User u, List<String> album){
        for(int i = 0; i < u.getAlbums().size(); i++){
                album.add(u.getAlbums().get(i).getName());
        }
        return album;
    }  
    

    public static void writeUserList(List <User> guys) throws FileNotFoundException, IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("file.ser"))) {
          oos.writeObject(guys);
         oos.close();
         System.out.println("Data has been serialized successfully");
        }
      }

    @SuppressWarnings("unchecked")
    public static List<User> readUserList() throws FileNotFoundException, IOException, ClassNotFoundException{
        List <User> deserialized = new ArrayList<User>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("file.ser"))) {
          deserialized = (List<User>) ois.readObject();
          return deserialized;
        }
      }

    

}
