package application.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hsqldb.rights.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Users {
    public int id = 0;
    public String name = "";
    public String titel = "";
    public String street = "";
    public int zip = 0;
    public String city = "";
    public String country = "";
    public Department dep = null;

    private String filename = "users.csv";
    @Override
    public String toString() {
        return name + " " + titel + " " + street;
    }


    public Users(int id, String name, String title, String street, int zip, String city, String country, Department dep){
        this.id = id;
        this.name = name;
        this.titel = title;
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.country = country;
        this.dep = dep;

    }

    public static Users getbyId(int id){
        Users obj = null;
        try{
            Connection connection = AccessDb.getConnection();

            Statement statement = null;

            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users WHERE user_id = " + id);

            if (result.next()){
                obj = new Users(
                        result.getInt("user_id"),
                        result.getString("name"),
                        result.getString("title"),
                        result.getString("street"),
                        result.getInt("zip"),
                        result.getString("city"),
                        result.getString("country"),
                        Department.getbyId(result.getInt("department_id(FK)"))
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
            statement = connection.prepareStatement("UPDATE users SET name = ?, title = ?, street = ?, zip = ?, city = ?, country = ?, department_id(FK) = ? WHERE user_id = ?");
            statement.setString(1, name);
            statement.setString(2, titel);
            statement.setString(3, street);
            statement.setInt(4, zip);
            statement.setString(5, city);
            statement.setString(6, country);
            statement.setInt(7, dep.id);
            statement.setInt(8, id);

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static ObservableList<Users> loadList(){
        ObservableList<Users> list = FXCollections.observableArrayList();

        try{
            Connection connection = AccessDb.getConnection();

            Statement statement = null;

            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users");

            while(result.next()){
                Users u = new Users(
                        result.getInt("user_id"),
                        result.getString("name"),
                        result.getString("title"),
                        result.getString("street"),
                        result.getInt("zip"),
                        result.getString("city"),
                        result.getString("country"),
                        Department.getbyId(result.getInt("department_id(FK)"))
                );

                list.add(u);

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
            statement.executeUpdate("DELETE FROM users WHERE user_id = " + id);

        } catch (SQLException throwables) {
        }
    }
}
