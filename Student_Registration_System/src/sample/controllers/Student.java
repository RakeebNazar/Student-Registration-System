package sample.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import sample.Objects.StudentObjects;
import sample.databaseHandler.DAO;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Student implements Initializable {
    @FXML
    private TableView<StudentObjects> Tableview;

    @FXML
    private TextField name;

    @FXML
    private TextField datebirth;

    @FXML
    private TextField number;

    @FXML
    private Button save;

    @FXML
    private Button update;

    @FXML
    private Button delete;

    @FXML
    private Button load;

    @FXML
    private Button clear;

    @FXML
    private ChoiceBox<CourseObject> choicebox;

    @FXML
    private JFXButton logout;

    Stage stage4 = new Stage();
    private ObservableList<StudentObjects> StudentList = FXCollections.observableArrayList();
    private ObservableList<CourseObject> CourseListForChoiceBox = FXCollections.observableArrayList();



    TableColumn<StudentObjects,Integer> StudentID_Column = null;
    TableColumn<StudentObjects,String> name_column = null;
    TableColumn<StudentObjects,String> datebirth_column=null;
    TableColumn<StudentObjects,String> number_Column = null;
    TableColumn<StudentObjects,Integer> courseid_Column = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StudentID_Column = new TableColumn<>("Student ID");
        StudentID_Column.setMinWidth(90);

        name_column = new TableColumn<>("Student Name");
        name_column.setMinWidth(90);

        datebirth_column = new TableColumn<>("Date Of Birth");
        datebirth_column.setMinWidth(90);

        number_Column = new TableColumn<>("Contact Number");
        number_Column.setMinWidth(110);

        courseid_Column = new TableColumn<>("Course ID");
        courseid_Column.setMinWidth(90);

        Tableview.getColumns().addAll(StudentID_Column,name_column,datebirth_column,number_Column,courseid_Column);



        Tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                StudentObjects cosobj  = Tableview.getSelectionModel().getSelectedItem();
                name.setText(cosobj.getStudentName());
                number.setText(Integer.toString(cosobj.getContactNumber()));
                datebirth.setText(cosobj.getDateOfBirth());
            }
        });





        //method calls//

        clear.setOnAction(e->cleardata());

        //dropdown
        try {
            dropdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        save.setOnAction(e -> insertS());

        //load
        load.setOnAction(e-> {
            try {
                load();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        update.setOnAction(e->update());
        delete.setOnAction(e->delete());
        logout.setOnAction(e->logout());

    }


    //Method Definitions//

    void cleardata(){
        name.clear();
        datebirth.clear();
        number.clear();

    }

    void dropdown() throws SQLException {
        DAO dao = new DAO();
        ResultSet rs=null;

        rs=dao.getCourse();


        while(rs.next()){
            CourseObject cos = new CourseObject();
            cos.setCourseID(rs.getInt(1));
            cos.setNameOfTheCourse(rs.getString(2));
            cos.setDuration(rs.getString(3));
            cos.setCourseFee(rs.getInt(4));
            CourseListForChoiceBox.add(cos);
        }

        choicebox.setItems(CourseListForChoiceBox);
        choicebox.setValue(CourseListForChoiceBox.get(0));

    }




    void insertS() {
        DAO dao = new DAO();

        try {
            dao.insertStudent(name.getText(),datebirth.getText(),Integer.parseInt(number.getText()),choicebox.getSelectionModel().getSelectedItem().getCourseID());

        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            load();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        name.clear();
        datebirth.clear();
        number.clear();


    }




    void load() throws SQLException {

        Tableview.getItems().clear();
        DAO dao = new DAO();
        ResultSet rs=null;

        rs=dao.getStudent();


        while(rs.next()){
            StudentObjects cos = new StudentObjects();
            cos.setStudentId(rs.getInt(1));
            cos.setDateOfBirth(rs.getString(2));
            cos.setStudentName(rs.getString(3));
            cos.setContactNumber(rs.getInt(4));
            cos.setCourseID(rs.getInt(5));
            StudentList.add(cos);
        }




        StudentID_Column.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        name_column.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        datebirth_column.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        number_Column.setCellValueFactory(new PropertyValueFactory<>("ContactNumber"));
        courseid_Column.setCellValueFactory(new PropertyValueFactory<>("CourseID"));

        Tableview.setItems(StudentList);




    }



    void update(){
        DAO dao = new DAO();
        StudentObjects coso=  Tableview.getSelectionModel().getSelectedItem();
        try {
            dao.updateS(name.getText(),datebirth.getText(),Integer.parseInt(number.getText()),coso.getStudentId(),choicebox.getSelectionModel().getSelectedItem().getCourseID());
        } catch (SQLException e) {
            e.printStackTrace();
        }



        try {
            load();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    void delete(){
        DAO dao = new DAO();
        StudentObjects selectedcos= Tableview.getSelectionModel().getSelectedItem();


        try {
            dao.deleteS(selectedcos.getStudentId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        StudentList.remove(selectedcos);

    }


    void logout(){
        name.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/sample/view/Login.fxml");
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


}
