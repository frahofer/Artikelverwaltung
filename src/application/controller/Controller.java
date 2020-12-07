package application.controller;

import application.MyFXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    //departmentFabian.fxml

    public TextField department_name;

    //commentFabian.fxml

    public TextField comment_title;
    public TextField comment_date;
    public TextArea comment_texArea;


    public void editStatiClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/statusPetko.fxml", "Stati bearbeiten");
    }

    public void editPriorityClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/priorityPetko.fxml", "Priorität bearbeiten");
    }
}
