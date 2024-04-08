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
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class StockController {
   @FXML Button quit, back, logout;
   @FXML ListView <ImageView> stockPhotos;

   private List <ImageView> stockPics = new ArrayList<ImageView>();
   ObservableList<ImageView> obsList;

   public void start(){
    stockPics.add(new ImageView(new Image("https://pbs.twimg.com/media/GIH86ItWoAAQCx2.png")));
    stockPics.add(new ImageView(new Image("https://images.ctfassets.net/sfnkq8lmu5d7/2fms1QQ9mCnpP7GMd0HRcA/34e2dd298f8812a9604daab227631c03/The-Wildest_Editorial_Can-My-Cat-Eat-CheeseHero.jpg")));
    stockPics.add(new ImageView(new Image("https://m.media-amazon.com/images/I/81hU5usPbzL.jpg")));
    stockPics.add(new ImageView(new Image("https://www.cabq.gov/artsculture/biopark/news/10-cool-facts-about-penguins/@@images/1a36b305-412d-405e-a38b-0947ce6709ba.jpeg")));
    stockPics.add(new ImageView(new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ64jq4ltSadmkHbJHeFGEfYvP3EyLDfohGYr0P9L_WGg&s")));
    stockPics.add(new ImageView(new Image("https://cdn.britannica.com/06/202006-050-64C85CC7/Neil-deGrasse-Tyson-2018.jpg?w=400&h=300&c=crop")));
    for(int i = 0; i < stockPics.size(); i++){
        stockPics.get(i).setFitHeight(100);
        stockPics.get(i).setFitWidth(140);
    }
    obsList = FXCollections.observableArrayList(stockPics);
    stockPhotos.setItems(obsList);
    stockPhotos.autosize();
   }


   public void Back() throws IOException, ClassNotFoundException{
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("StockAlbum.fxml"));
    Parent root = loader.load();
    Stage window = (Stage) back.getScene().getWindow();
    window.setScene(new Scene(root, 600, 400));
    StockAlbumsController Controller = loader.getController();
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
