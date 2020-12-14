package application.controller;

import application.model.Department;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;

public class Controller_department {
    public TextField department_name;
    public ListView<Department> department_listView;

    private String filename = "departments.csv";
    public String newString = "";
    public String oldString = "";

    private int department_id = 0;

    Department temp2 = new Department();

    public void initialize(){

        department_listView.setItems(Department.loadFromFile(this.filename));

    }


    public void cancelClicked(ActionEvent actionEvent) {

        department_id = temp2.id;
        department_name.setText(temp2.name);

    }

    public void saveClicked(ActionEvent actionEvent) {

        Department selectedArticle = department_listView.getSelectionModel().getSelectedItem();
        selectedArticle.name = department_name.getText();
        selectedArticle.id = department_id;

        newString = selectedArticle.id + ";" + selectedArticle.name;

        Department.writeToFile(this.oldString, newString, this.filename);

        department_listView.refresh();

    }

    public void handleMouseClicked(MouseEvent mouseEvent) {
        temp2 = department_listView.getSelectionModel().getSelectedItem();

        //fülle Felder rechts

        department_id = temp2.id;
        department_name.setText(temp2.name);

        //olString für saveClicked
        this.oldString = temp2.id + ";" + temp2.name;

    }

}
