package application.controller;

import application.model.Priority;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;

public class Controller_priority {

    public TextField priority_name;
    public ListView priority_listView;
    private String filename = "priorities.csv";

    public String newString = "";
    public String oldString = "";

    public ObservableList<Priority> liste = FXCollections.observableArrayList();

    Priority tempPrio = new Priority();

    public void initialize() {
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("priorities.csv"));
            try {

                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    Priority a = new Priority();

                    String[] words = s.split(";");
                    //a.id = Integer.parseInt(words[0]);
                    a.name = words[1];

                    liste.add(a); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        priority_listView.setItems(liste);
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

        writeToFile(this.oldString, this.newString);

        priority_listView.refresh();
    }


    public void writeToFile(String oldText, String newText){

        File fileToBeModified = new File(this.filename);

        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));

            String line = reader.readLine();

            while (line != null) {
                oldContent = oldContent + line + "\n";

                line = reader.readLine();
            }

            String newContent = oldContent.replaceAll(oldText, newText);

            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert reader != null;
                reader.close();

                assert writer != null;
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}