package application.controller;

import application.model.Priority;
import application.model.Users;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;

public class Controller_users {
    public TextField user_name;
    public TextField user_title;
    public TextField user_street;
    public TextField user_zip;
    public TextField user_city;
    public TextField user_country;
    public ChoiceBox user_department_dropdown;
    public ListView listView_users;

    public ObservableList<Users> liste = FXCollections.observableArrayList();

    private String filename = "users.csv";

    Users tempUser = new Users();

    public String newString = "";
    public String oldString = "";

    public void initialize() {
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("users.csv"));
            try {

                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    Users a = new Users();
                    String[] split = s.split(";");
                    a.id = Integer.parseInt(split[0]);
                    a.titel = split[1];
                    a.name = split[2];
                    a.street = split[3];
                    a.zip = Integer.parseInt(split[4]);
                    a.city = split[5];
                    a.depId = Integer.parseInt(split[6]);

                    liste.add(a);
                }
            } finally {
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        listView_users.setItems(liste);
    }

    public void usersListViewClicked(MouseEvent mouseEvent) {
        tempUser = (Users) listView_users.getSelectionModel().getSelectedItem();
        user_name.setText(tempUser.name);
        if (tempUser.titel != null) {
            user_title.setText(tempUser.titel);
        }
        user_street.setText(tempUser.street);
        user_zip.setText(String.valueOf(tempUser.zip));
        user_city.setText(tempUser.city);
        //this.oldString = tempUser.name;
    }

    public void cancel_user_clicked(ActionEvent actionEvent) {
        user_name.setText(tempUser.name);
        user_title.setText(tempUser.titel);
        user_street.setText(tempUser.street);
        user_zip.setText(String.valueOf(tempUser.zip));
        user_city.setText(tempUser.city);
        Platform.exit();
    }

    public void save_user_clicked(ActionEvent actionEvent) {
        Users selectedUser = (Users) listView_users.getSelectionModel().getSelectedItem();
        selectedUser.name = user_name.getText();
        selectedUser.titel = user_title.getText();
        selectedUser.street = user_street.getText();
        //selectedUser.toString(zip) = user_zip.getText();
        selectedUser.city = user_city.getText();

        newString = selectedUser.name;

        writeToFile(this.oldString, this.newString);

        listView_users.refresh();
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
