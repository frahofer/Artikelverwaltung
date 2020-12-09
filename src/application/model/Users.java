package application.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Users {
    public int id = 0;
    public String name = "";
    public String titel = "";
    public String street = "";
    public int zip = 0;
    public String city = "";
    public int depId = 0;

    private String filename = "users.csv";
    @Override
    public String toString() {
        return name + " " + titel + " " + street;
    }

    public static ObservableList<Users> loadFromFile(String filename) {
        ObservableList<Users> liste = FXCollections.observableArrayList();
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filename));
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
        return liste;
    }
}
