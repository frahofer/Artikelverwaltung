package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//test
public class Priority {
    public int id = 0;
    public String name = "";
    private String filename = "priorities.csv";

    @Override
    public String toString() {
        return name;
    }

    public static ObservableList<Priority> loadList(){
        ObservableList<Priority> list = FXCollections.observableArrayList();

        try{
            Connection connection = AccessDb.getConnection();

            Statement statement = null;

            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM priorities");

            while(result.next()){
                Priority p = new Priority();
                p.id = result.getInt("priority_id");
                p.name = result.getString("name");
                list.add(p);
                System.out.println("yeet 2");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }


    public static ObservableList<Priority> loadFromFile(String filename) {
        ObservableList<Priority> liste = FXCollections.observableArrayList();
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filename));
            try {

                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    Priority a = new Priority();

                    String[] words = s.split(";");
                    a.id = Integer.parseInt(words[0]);
                    a.name = words[1];


                    liste.add(a); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return liste;
    }
    public static void writeToFile(String oldText, String newText, String filename){

        File fileToBeModified = new File(filename);

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

