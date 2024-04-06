package Pagesfx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import app.User;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminController {
    @FXML Button createUser, deleteUser, logout, quit;
    @FXML TextField createField;
    @FXML ListView <String> userList;

    public static List <User> users = new ArrayList<User>();

    List<String> people = new ArrayList<String>();




    public static List<User> getUsers() throws FileNotFoundException, ClassNotFoundException, IOException{
        List<User> peep = readUserList();
        return peep;
    }

    public static void addUser(String name) throws FileNotFoundException, IOException{
        users.add(new User (name));
    }
    
    
    

    public void CreateUser(ActionEvent event) throws IOException, ClassNotFoundException{
        if (searchUsers(createField.getText().trim()) == -1){
            addUser(createField.getText().trim());
            people.add(createField.getText().trim());
            createField.clear();
            update();
        }
        
    }

    public void DeleteUser(ActionEvent event) throws ClassNotFoundException, IOException{
        int index = searchUsers(userList.getSelectionModel().getSelectedItem());
				users.remove(index);
                people.remove(index);
				update();
    }

    public void start() throws IOException, ClassNotFoundException{
        users = readUserList();
        if(users != null){
        for(int i=0; i<users.size(); i++){
            people.add(users.get(i).getUsername());
        }
        }
        ObservableList <String> usersList = FXCollections.observableArrayList(people);
        userList.setItems(usersList);
    }

    public void update() throws IOException, ClassNotFoundException{
        ObservableList <String> usersList = FXCollections.observableArrayList(people);
        userList.setItems(usersList);
    }


    public int searchUsers(String name){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUsername().equals(name)){
                return i;
            }
        }
        return -1;
    }

    public void Logout(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Username Page.fxml"));

        Stage window = (Stage) quit.getScene().getWindow();
        window.setScene(new Scene(root, 600, 400));
    }

    public void Quit (ActionEvent event) throws FileNotFoundException, IOException{
        writeUserList(users);
        Platform.exit();
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
