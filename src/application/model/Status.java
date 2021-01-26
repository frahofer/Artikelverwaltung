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

    public Status(int id, String name){
        this.id = id;
        this.name = name;

    }

    public static Status getbyId(int id){
        Status obj = null;
        try{
            Connection connection = AccessDb.getConnection();

            Statement statement = null;

            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM stati WHERE status_id = " + id);

            if (result.next()){
                obj = new Status(result.getInt("status_id"), result.getString("name"));

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
                Status p = new Status(
                        result.getInt("status_id"),
                        result.getString("name")
                );

                list.add(p);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
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

}
