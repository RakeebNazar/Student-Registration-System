package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animation.Animation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private JFXButton login;

    @FXML
    private Label Error;

    Stage stage2=new Stage();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        login.setOnAction(e->loginAction());




    }


    void loginAction(){
       String name,pass;

       name= username.getText();
       pass=password.getText();

       if(name.toUpperCase().equals("CINEC")&&pass.toUpperCase().equals("CINEC123")){

           username.getScene().getWindow().hide();

           FXMLLoader loader = new FXMLLoader();
           URL xmlUrl = getClass().getResource("/sample/view/Database.fxml");
           loader.setLocation(xmlUrl);

           try {
               loader.load();


           }catch(IOException e) {
               e.printStackTrace();
           }

           Parent root2 = loader.getRoot();

           stage2 = new Stage();
           stage2.setScene(new Scene(root2));

           stage2.maximizedProperty().addListener((observable, oldValue, newValue) -> {
               if (newValue)
                   stage2.setMaximized(false);
           });

           stage2.setTitle("Database");
           stage2.showAndWait();

       }
       else{
           Animation an = new Animation();

           username.clear();
           password.clear();
           Error.setVisible(true);
           an.shaker(username);
           an.shaker(password);
       }


    }



}
