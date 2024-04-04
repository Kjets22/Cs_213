package Pagesfx;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class albumsController {


    public static List <String> albums = new ArrayList<>();

    public static List<String> getAlbums(){
        return albums;
    }

    public static void addAlbum(String name){
        albums.add(name);
    }



    @FXML
    Button quit, createAlbum;
    @FXML 
    ListView <String> list = new ListView<>();
    
    ObservableList<String> obsList;
    

    public void start() throws IOException{
        obsList = FXCollections.observableArrayList(albums);
        list.setItems(obsList);
        


    }
   

    public void Logout(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Username Page.fxml"));

        Stage window = (Stage) quit.getScene().getWindow();
        window.setScene(new Scene(root, 750, 500));

   }

   /*public void makeAlbum(ActionEvent event) throws IOException{
    Parent root = FXMLLoader.load(getClass().getResource("NewAlbumPage.fxml"));

    Stage window = (Stage) quit.getScene().getWindow();
    window.setScene(new Scene(root, 750, 500));

}*/
}
