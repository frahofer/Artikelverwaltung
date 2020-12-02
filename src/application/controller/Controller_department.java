package application.controller;

import application.model.Department;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;

public class Controller_department {
    public TextField department_name;
    public ListView department_listView;
    public TextField department_id;

    private String filename = "departments.csv";
    public ObservableList<Department> liste = FXCollections.observableArrayList();

    public String newString = "";
    public String oldString = "";

    Department temp2 = new Department();

    public void initialize(){

        String line = "";
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(this.filename));

            try{

                while((line = reader.readLine()) != null){

                    String[] split = line.split(";");
                    Department temp = new Department();

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

        department_listView.setItems(liste);

    }


    public void cancelClicked(ActionEvent actionEvent) {

        department_id.setText(Integer.toString(temp2.id));
        department_name.setText(temp2.name);

    }

    public void saveClicked(ActionEvent actionEvent) {

        Department selectedArticle = (Department) department_listView.getSelectionModel().getSelectedItem();
        selectedArticle.name = department_name.getText();
        selectedArticle.id = Integer.parseInt(department_id.getText());

        newString = selectedArticle.id + ";" + selectedArticle.name;

        writeToFile(this.oldString, newString);

        department_listView.refresh();

    }

    public void handleMouseClicked(MouseEvent mouseEvent) {
        temp2 = (Department) department_listView.getSelectionModel().getSelectedItem();

        //fülle Felder rechts

        department_id.setText(Integer.toString(temp2.id));
        department_name.setText(temp2.name);

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
