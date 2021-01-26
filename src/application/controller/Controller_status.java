package application.controller;

import application.model.Priority;
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

    private String filename = "stati.csv";
    private int status_id_temp = 0;

    Status temp2 = null;

    public void initialize(){

        status_listView.setItems(Status.loadList());

    }

    public void cancelClicked(ActionEvent actionEvent) {

        //setzte Felder zurück auf den letzten ausgewählten status

        this.status_id_temp = temp2.id;
        status_name.setText(temp2.name);

    }

    public void saveClicked(ActionEvent actionEvent) {

        Status selectedStatus = status_listView.getSelectionModel().getSelectedItem();

        if(selectedStatus != null){

            selectedStatus = new Status(
                    status_id_temp,
                    status_name.getText()
            );

            status_listView.refresh();
            selectedStatus.update();
        }

    }

    public void handleMouseClicked(MouseEvent mouseEvent) {
        temp2 = status_listView.getSelectionModel().getSelectedItem();

        //fülle Felder rechts

        this.status_id_temp = temp2.id;
        status_name.setText(temp2.name);

    }

    public void deleteClicked(ActionEvent actionEvent) {
        Status selectedPriority = (Status) status_listView.getSelectionModel().getSelectedItem();

        status_name.clear();
        status_listView.getItems().remove(selectedPriority);

        selectedPriority.delete();
    }

}
