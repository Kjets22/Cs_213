package Pagesfx;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class StockAlbumsController {
    @FXML Button logout, quit, openStock;
    @FXML ListView <String> list = new ListView<>();

    private final Image IMAGE_FOLDER = new Image("https://i.pinimg.com/originals/37/29/61/3729617452425f23b079bb0de458293a.png");

    ObservableList<String> obsList;
    String stock;

    public void start() throws IOException, ClassNotFoundException{
        stock = ("stock");
        List <String> albums = new ArrayList<>();
        albums.add(stock);
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

    public void openAlbum(ActionEvent event) throws IOException, ClassNotFoundException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Stock.fxml"));
        Parent root = loader.load();
        Stage window = (Stage) openStock.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
        StockController Controller = loader.getController();
        Controller.start();
    }

    public void Logout(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Username Page.fxml"));
        Stage window = (Stage) quit.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));

   }

   public void Quit(ActionEvent event) throws FileNotFoundException, IOException{
        Platform.exit();
   }
    
}
