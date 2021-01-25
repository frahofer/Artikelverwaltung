package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;

public class Department {
    public int id = 0;
    public String  name = "";

    @Override
    public String toString(){
        return id + " " + name;
    }


    public static Department getbyId(int id){
        Department obj = new Department();
        try{
            Connection connection = AccessDb.getConnection();

            Statement statement = null;

            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM departments WHERE department_id = " + id);

            if (result.next()){
                obj.id = result.getInt("department_id");
                obj.name = result.getString("name");

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    };

    public void update(){
        try{
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE departments SET name = ? WHERE priority_id = ?");
            statement.setString(1, name);
            statement.setInt(2, id);

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static ObservableList<Department> loadList(){
        ObservableList<Department> list = FXCollections.observableArrayList();

        try{
            Connection connection = AccessDb.getConnection();

            Statement statement = null;

            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM departments");

            while(result.next()){
                Department d= new Department();
                d.id = result.getInt("department_id");
                d.name = result.getString("name");

                list.add(d);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public static ObservableList<Department> loadFromFile(String filename){
        ObservableList<Department> liste = FXCollections.observableArrayList();

        String line = "";
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(filename));

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

    public void delete() {

        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;

            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM departments WHERE department_id = " + id);

        } catch (SQLException throwables) {
        }
    }

}
