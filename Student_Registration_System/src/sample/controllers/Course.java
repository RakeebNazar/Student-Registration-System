package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.xdevapi.Table;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Objects.CourseObject;
import sample.databaseHandler.DAO;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;

public class Course implements Initializable {
    @FXML
    private TableView<CourseObject> Tableview;

    @FXML
    private Button save;

    @FXML
    private Button update;

    @FXML
    private Button delete;

    @FXML
    private Button load;



    @FXML
    private TextField name;

    @FXML
    private TextField duration;

    @FXML
    private TextField fee;


    @FXML
    private Button clear;



    Stage stage4 = new Stage();



    private ObservableList<CourseObject> CourseList = FXCollections.observableArrayList();



    TableColumn<CourseObject,Integer> CourseID_Column = null;
    TableColumn<CourseObject,String> Nameofthecourse_column = null;
    TableColumn<CourseObject,String> duration_Column=null;
    TableColumn<CourseObject,Integer> coursefee_Column = null;
    @Override
    public void initialize(URL location, ResourceBundle resources)  {

        CourseID_Column = new TableColumn<>("CourseID");
        CourseID_Column.setMinWidth(100);

        Nameofthecourse_column = new TableColumn<>("Course");
        Nameofthecourse_column.setMinWidth(125);

        duration_Column = new TableColumn<>("Duration");
        duration_Column.setMinWidth(125);


        coursefee_Column = new TableColumn<>("CourseFee");
        coursefee_Column.setMinWidth(125);
        Tableview.getColumns().addAll(CourseID_Column,Nameofthecourse_column,duration_Column,coursefee_Column);





        Tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                CourseObject cosobj  = Tableview.getSelectionModel().getSelectedItem();
                name.setText(cosobj.getNameOfTheCourse());
                duration.setText(cosobj.getDuration());
                fee.setText(Integer.toString(cosobj.getCourseFee()));
            }
        });



        //methods//
        update.setOnAction(e->update());

        clear.setOnAction(e->cleardata());         //clear method call
        load.setOnAction(e-> {                    //load method call
        try {
            load();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        });



        save.setOnAction(e -> insert());        //insert method call

        delete.setOnAction(e->delete());        //delete method call


    }



    // Definintions//

    void insert() {
        DAO dao = new DAO();
        try {
            dao.insertCourse(name.getText(),duration.getText(),Integer.parseInt(fee.getText()));
            load();     // check jst creating a new course obj with details and storing it in the obsvble list works?
        } catch (SQLException e) {
            e.printStackTrace();
        }
        name.clear();
        duration.clear();
        fee.clear();


    }

    void load() throws SQLException {       //loads the data

        Tableview.getItems().clear();       //clears the table data..because when we insert a new record we have to call this method again so if we dont clear the data...the will duplicate itself
        DAO dao = new DAO();
        ResultSet rs=null;

        rs=dao.getCourse();     //getting course liot from database


        while(rs.next()){
            CourseObject cos = new CourseObject();
            cos.setCourseID(rs.getInt(1));
            cos.setNameOfTheCourse(rs.getString(2));
            cos.setDuration(rs.getString(3));
            cos.setCourseFee(rs.getInt(4));
            CourseList.add(cos);
        }




        CourseID_Column.setCellValueFactory(new PropertyValueFactory<>("CourseID"));
        Nameofthecourse_column.setCellValueFactory(new PropertyValueFactory<>("NameOfTheCourse"));
        duration_Column.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        coursefee_Column.setCellValueFactory(new PropertyValueFactory<>("CourseFee"));

        Tableview.setItems(CourseList);


    }



    void delete(){
        DAO dao = new DAO();
       CourseObject selectedcos= Tableview.getSelectionModel().getSelectedItem();


                            try {
                                dao.delete(selectedcos.getCourseID());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            CourseList.remove(selectedcos);     //no load call but simply removing it from the table

    }


    void cleardata(){
            name.clear();
            duration.clear();
            fee.clear();

    }

    void update(){
        DAO dao = new DAO();
        CourseObject coso=  Tableview.getSelectionModel().getSelectedItem();
        try {
            dao.update(name.getText(),duration.getText(),Integer.parseInt(fee.getText()),coso.getCourseID());
        } catch (SQLException e) {
            e.printStackTrace();
        }



        try {
            load();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}