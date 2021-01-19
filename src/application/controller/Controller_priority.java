package application.controller;

import application.model.Priority;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller_priority {

    public TextField priority_name;
    public ListView<Priority> priority_listView;

    public String newString = "";
    public String oldString = "";

    public ObservableList<Priority> liste = FXCollections.observableArrayList();

    Priority tempPrio = new Priority();
    private String filename = "priorities.csv";

    public void initialize() {
        priority_listView.setItems(Priority.loadList());
    }

    public void priorityMouseClicked(MouseEvent mouseEvent) {
        tempPrio = (Priority) priority_listView.getSelectionModel().getSelectedItem();

        priority_name.setText(tempPrio.name);

        this.oldString = tempPrio.name;

    }


    public void priorityCancelClicked() {
        priority_name.setText(tempPrio.name);
        Platform.exit();
    }

    public void saveClicked(ActionEvent actionEvent) {
        Priority selectedPriority = (Priority) priority_listView.getSelectionModel().getSelectedItem();
        selectedPriority.name = priority_name.getText();

        newString = selectedPriority.name;

        Priority.writeToFile(this.oldString, this.newString, this.filename);

        priority_listView.refresh();
    }

    public void deleteClicked(ActionEvent actionEvent) {
        Priority selectedPriority = (Priority) priority_listView.getSelectionModel().getSelectedItem();

        priority_name.clear();
        priority_listView.getItems().remove(selectedPriority);

        selectedPriority.delete();
    }

    public void newClicked(ActionEvent actionEvent) {
    }
}