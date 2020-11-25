package application.controller;

import application.model.Priority;
import application.model.Status;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.*;

public class Controller_priority {
    public TextField priority_name;
    public ListView priority_listView;
    private String filename = "priorities.csv";
    public ObservableList<Priority> liste = FXCollections.observableArrayList();
    public String newString = "";
    public String oldString = "";


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
                    a.id = Integer.parseInt(words[0]);
                    a.name = words[1];

                    priority_listView.add(a); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
        }

        Priority.setItems(liste);

    public void writeToFile(String oldText, String newText){

        File fileToBeModified = new File(this.filename);

        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));

            String line = reader.readLine();

            while (line != null) {
                oldContent = oldContent + line + "\\n";

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

    public void priorityCancelClicked(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void saveClicked(ActionEvent actionEvent) {
        Status selectedArticle = (Status) priority_listView.getSelectionModel().getSelectedItem();
        selectedArticle.name = priority_name.getText();

        newString = selectedArticle.id + ";" + selectedArticle.name;

        writeToFile(this.oldString, newString);

        priority_listView.refresh();
    }
}
