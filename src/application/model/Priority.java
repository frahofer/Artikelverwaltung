package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;

public class Priority {
    public int id = 0;
    public String name = "";

    private String filename = "priorities.csv";

    @Override
    public String toString() {
        return name;
    }

    public Priority(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static Priority getbyId(int id){
        Priority obj = null;
        try{
            Connection connection = AccessDb.getConnection();

            Statement statement = null;

            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM priorities WHERE priority_id = " + id);

            if (result.next()){
                obj = new Priority(result.getInt(
                        "priority_id"),
                        result.getString("name")
                );

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
            statement = connection.prepareStatement("UPDATE priorities SET name = ? WHERE priority_id = ?");
            statement.setString(1, name);
            statement.setInt(2, id);

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void delete() {

        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;

            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM priorities WHERE priority_id = " + id);

        } catch (SQLException throwables) {
        }
    }

    public static ObservableList<Priority> loadList(){
        ObservableList<Priority> list = FXCollections.observableArrayList();

        try{
            Connection connection = AccessDb.getConnection();

            Statement statement = null;

            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM priorities");

            while(result.next()){
                Priority p = new Priority(
                        result.getInt("priority_id"),
                        result.getString("name")
                );
                list.add(p);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

}

