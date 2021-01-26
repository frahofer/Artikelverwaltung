package application.controller;

import application.model.Department;
import application.model.Status;
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

    private int department_id = 0;

    Department temp2 = null; //wird sofort überschrieben

    public void initialize(){

        department_listView.setItems(Department.loadList());

    }


    public void cancelClicked(ActionEvent actionEvent) {

        department_id = temp2.id;
        department_name.setText(temp2.name);

    }

    public void saveClicked(ActionEvent actionEvent) {

        Department selectedDepartment = department_listView.getSelectionModel().getSelectedItem();

        if(selectedDepartment != null){
            selectedDepartment = new Department(
                    department_id,
                    department_name.getText()
            );

            department_listView.refresh();

            selectedDepartment.update();
        }

    }

    public void handleMouseClicked(MouseEvent mouseEvent) {
        temp2 = department_listView.getSelectionModel().getSelectedItem();

        //fülle Felder rechts

        department_id = temp2.id;
        department_name.setText(temp2.name);

    }

    public void deleteClicked(ActionEvent actionEvent) {
        Department selectedPriority = (Department) department_listView.getSelectionModel().getSelectedItem();

        department_name.clear();
        department_listView.getItems().remove(selectedPriority);

        selectedPriority.delete();
    }
}
