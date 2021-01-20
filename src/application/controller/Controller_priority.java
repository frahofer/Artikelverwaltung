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

    public String oldString = "";
    public String newString = "";

    public ObservableList<Priority> liste = FXCollections.observableArrayList();

    Priority tempPrio = new Priority();
    private String filename = "priorities.csv";
    private int priority_id_temp = 0;

    public void initialize() {
        priority_listView.setItems(Priority.loadList());
    }

    public void priorityMouseClicked(MouseEvent mouseEvent) {
        tempPrio = (Priority) priority_listView.getSelectionModel().getSelectedItem();

        priority_name.setText(tempPrio.name);
        priority_id_temp = tempPrio.id;

        //this.oldString = tempPrio.name;

    }

    public void priorityCancelClicked() {
        priority_name.setText(tempPrio.name);
        priority_id_temp = tempPrio.id;

        Platform.exit();
    }

    public void saveClicked(ActionEvent actionEvent) {
        Priority selectedPriority = (Priority) priority_listView.getSelectionModel().getSelectedItem();
        if(selectedPriority != null){
            selectedPriority.name = priority_name.getText();
            selectedPriority.id = priority_id_temp;

            //newString = selectedPriority.name;
            //Priority.writeToFile(this.oldString, this.newString, this.filename);

            priority_listView.refresh();

            selectedPriority.update();
        }
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