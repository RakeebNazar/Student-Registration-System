package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Database implements Initializable {

    @FXML
    private JFXRadioButton student;

    @FXML
    private JFXRadioButton course;

    @FXML
    private JFXButton go;

    Stage stage3 = new Stage();
    Stage stage4 = new Stage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup answer = new ToggleGroup();
        student.setToggleGroup(answer);
        course.setToggleGroup(answer);
        go.setOnAction(e->nextPage());


    }

        void nextPage(){
            if(student.isSelected()){
                stage4.setTitle("Student Info");
                go.getScene().getWindow().hide();
                stage4.setTitle("Student Info");
                FXMLLoader loader = new FXMLLoader();
                URL xmlUrl = getClass().getResource("/sample/view/Student.fxml");
                loader.setLocation(xmlUrl);

                try {
                    loader.load();


                }catch(IOException e) {
                    e.printStackTrace();
                }

                Parent root2 = loader.getRoot();

                stage4 = new Stage();
                stage4.setScene(new Scene(root2));

                stage4.maximizedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue)
                        stage4.setMaximized(false);
                });

                stage4.show();
            }
            else {

                go.getScene().getWindow().hide();
                stage3.setTitle("Course Info");
                FXMLLoader loader = new FXMLLoader();
                URL xmlUrl = getClass().getResource("/sample/view/Course.fxml");
                loader.setLocation(xmlUrl);

                try {
                    loader.load();


                }catch(IOException e) {
                    e.printStackTrace();
                }

                Parent root2 = loader.getRoot();

                stage3 = new Stage();
                stage3.setScene(new Scene(root2));

                stage3.maximizedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue)
                        stage3.setMaximized(false);
                });


                stage3.show();

            }

        }
}
