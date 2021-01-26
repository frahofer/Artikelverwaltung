package application.controller;

import application.model.Department;
import application.model.Priority;
import application.model.Ticket;
import application.model.Users;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.hsqldb.rights.User;

import java.io.*;

public class Controller_users {
    public TextField user_name;
    public TextField user_title;
    public TextField user_street;
    public TextField user_zip;
    public TextField user_city;
    public TextField user_country;
    public ListView<Users> listView_users;

    public ObservableList<Users> liste = FXCollections.observableArrayList();
    public ComboBox departmentComboBox;

    private String filename = "users.csv";
    private int user_id_temp = 0;

    Users tempUser = null;

    ObservableList<Department> list_department = FXCollections.observableArrayList();
    private String filename_department = "departments.csv";

    public void initialize() {
        listView_users.setItems(Users.loadList());
        list_department = Department.loadList();
        departmentComboBox.setItems(list_department);
    }

    public void usersListViewClicked(MouseEvent mouseEvent) {
        tempUser = listView_users.getSelectionModel().getSelectedItem();

        this.user_id_temp = tempUser.id;
        user_name.setText(tempUser.name);

        if (tempUser.titel != null) {
            user_title.setText(tempUser.titel);
        }else{
            user_title.setText("");
        }

        user_street.setText(tempUser.street);
        user_zip.setText(String.valueOf(tempUser.zip));
        user_city.setText(tempUser.city);
        user_country.setText(tempUser.country);

        departmentComboBox.setValue(tempUser.dep);

    }

    public void cancel_user_clicked(ActionEvent actionEvent) {
        this.user_id_temp = tempUser.id;
        user_name.setText(tempUser.name);

        if (tempUser.titel != null) {
            user_title.setText(tempUser.titel);
        }else{
            user_title.setText("");
        }
        user_street.setText(tempUser.street);
        user_zip.setText(String.valueOf(tempUser.zip));
        user_city.setText(tempUser.city);
        user_country.setText(tempUser.country);

        departmentComboBox.setValue(tempUser.dep);

        Platform.exit();
    }

    public void save_user_clicked(ActionEvent actionEvent) {
        Users selectedUser = listView_users.getSelectionModel().getSelectedItem();

        if(selectedUser != null){
            selectedUser = new Users(
                    user_id_temp,
                    user_name.getText(),
                    user_title.getText(),
                    user_street.getText(),
                    Integer.parseInt(user_zip.getText()),
                    user_city.getText(),
                    user_country.getText(),
                    (Department) departmentComboBox.getValue()
            );

            listView_users.refresh();

            selectedUser.update();
        }
    }

    public void deleteClicked(ActionEvent actionEvent) {
        Users selectedUser = (Users) listView_users.getSelectionModel().getSelectedItem();

        user_name.clear();
        user_title.clear();
        user_street.clear();
        user_zip.clear();
        user_city.clear();
        user_country.clear();
        departmentComboBox.setValue(null);

        listView_users.getItems().remove(selectedUser);
        selectedUser.delete();
    }
}
