package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;

public class Status {

    public int id = 0;
    public String  name = "";

    @Override
    public String toString(){
        return id + " " + name;
    }

    public void update(){
        try{
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE stati SET name = ? WHERE status_id = ?");
            statement.setString(1, name);
            statement.setInt(2, id);

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static ObservableList<Status> loadList(){
        ObservableList<Status> list = FXCollections.observableArrayList();

        try{
            Connection connection = AccessDb.getConnection();

            Statement statement = null;

            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM stati");

            while(result.next()){
                Status p = new Status();
                p.id = result.getInt("status_id");
                p.name = result.getString("name");
                list.add(p);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }


    public static ObservableList<Status> loadFromFile(String filename){
        ObservableList<Status> liste = FXCollections.observableArrayList();

        String line = "";
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(filename));

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

        return liste;

    }

    public void delete() {

        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;

            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM stati WHERE status_id = " + id);

        } catch (SQLException throwables) {
        }
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
