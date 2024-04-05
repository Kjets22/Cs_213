package Pagesfx;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.List;

public class NewAlbumController {
    @FXML TextField newalbumname;
    @FXML Button calbum2;

    public List <String> albums = albumsController.getAlbums();



    public void createAlbum(ActionEvent event) throws IOException{
        if (searchAlbums(newalbumname.getText()) == 1){
            albumsController.addAlbum(newalbumname.getText());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Albums Page.fxml"));
            Parent root = loader.load();
            Stage window = (Stage) calbum2.getScene().getWindow();
            window.setScene(new Scene(root, 750, 500));
            albumsController Controller = loader.getController();
            Controller.start();
        }   
    }

    public int searchAlbums(String name){
        if(albums != null){
            for(int i = 0; i < albums.size(); i++){
                if(albums.get(i).equals(newalbumname.getText())){
                    return -1;
                }
            }
        }
        if(albums == null){
            return -1;
        }
        return 1;
    }
}
