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
    public ListView status_listView;
    public TextField status_id;

    private String filename ="stati.csv";
    public ObservableList<Status> liste = FXCollections.observableArrayList();

    public String newString = "";
    public String oldString = "";

    Status temp2 = new Status();

    public void initialize(){

        String line = "";
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(this.filename));

            try{

                while((line = reader.readLine()) != null){

                    String[] split = line.split(";");
                    Status temp = new Status();

                    temp.id = Integer.parseInt(split[0]);
                    temp.name = split[1];

                    liste.add(temp);

                }

            }finally {
                reader.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        status_listView.setItems(liste);

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

        writeToFile(this.oldString, newString);

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
}
