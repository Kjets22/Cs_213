package Pagesfx;

import java.io.IOException;

import app.album;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SlideshowController {
    @FXML Button nextPhoto, backPhoto, quit, exit;
    @FXML ImageView photo;

    static album currentAlbum;
    int index;

    public void start(){
        index = 0;
        currentAlbum = PhotosController.getCurrentAlbum();
        photo.setImage(new Image(currentAlbum.get_photos().get(index).getLocation()));
    }

    public void nextPic(ActionEvent event){
        index ++;
        if(index >= currentAlbum.get_photos().size()){
            Alert alert = new Alert(AlertType.ERROR);
			    alert.setHeaderText("END OF SLIDESHOW");
			    alert.setContentText("You have reached the end of the slideshow");
			    alert.show();
                index --;
        }
        else{
            photo.setImage(new Image(currentAlbum.get_photos().get(index).getLocation()));
        }
    }

    public void prevPic(ActionEvent event){
        index --;
        if(index < 0){
            Alert alert = new Alert(AlertType.ERROR);
			    alert.setHeaderText("START OF SLIDESHOW");
			    alert.setContentText("You have reached the start of the slideshow");
			    alert.show();
                index ++;
        }
        else{
            photo.setImage(new Image(currentAlbum.get_photos().get(index).getLocation()));
        }
    }

    public void quit(ActionEvent event){
        Platform.exit();
    }

    public void exit(ActionEvent event) throws IOException, ClassNotFoundException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Photos.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) quit.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
        PhotosController Controller = loader.getController();
        Controller.start();
    }
}
