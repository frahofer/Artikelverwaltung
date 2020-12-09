package application.controller;

import application.model.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;

public class Controller_status {
    public TextField status_name;
    public ListView<Status> status_listView;
    public TextField status_id;

    private String filename = "stati.csv";
    public String newString = "";
    public String oldString = "";

    Status temp2 = new Status();

    public void initialize(){

        //liste = Status.loadFromFile(filename);

        status_listView.setItems(Status.loadFromFile(this.filename));

    }

    public void cancelClicked(ActionEvent actionEvent) {

        //setzte Felder zurück auf den letzten ausgewählten status

        status_id.setText(Integer.toString(temp2.id));
        status_name.setText(temp2.name);

    }

    public void saveClicked(ActionEvent actionEvent) {

        Status selectedArticle = (Status) status_listView.getSelectionModel().getSelectedItem();
        selectedArticle.name = status_name.getText();
        selectedArticle.id = Integer.parseInt(status_id.getText());

        newString = selectedArticle.id + ";" + selectedArticle.name;

        Status.writeToFile(this.oldString, newString, this.filename);

        status_listView.refresh();

    }

    public void handleMouseClicked(MouseEvent mouseEvent) {
        temp2 = (Status) status_listView.getSelectionModel().getSelectedItem();

        //fülle Felder rechts

        status_id.setText(Integer.toString(temp2.id));
        status_name.setText(temp2.name);

        //olString für saveClicked
        this.oldString = temp2.id + ";" + temp2.name;

    }

}
