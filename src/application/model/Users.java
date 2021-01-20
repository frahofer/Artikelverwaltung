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
    public int depId = 0;

    private String filename = "users.csv";
    @Override
    public String toString() {
        return name + " " + titel + " " + street;
    }

    public void update(){
        try{
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE users SET name = ? WHERE priority_id = ?");
            statement.setString(1, name);
            statement.setInt(2, id);

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

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
    public static ObservableList<Users> loadList(){
        ObservableList<Users> list = FXCollections.observableArrayList();

        try{
            Connection connection = AccessDb.getConnection();

            Statement statement = null;

            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM users");

            while(result.next()){
                Users u = new Users();
                u.id = result.getInt("user_id");
                u.titel = result.getString("title");
                u.name = result.getString("name");
                u.street = result.getString("street");
                u.zip = result.getInt("zip");
                u.city = result.getString("city");
                u.depId = result.getInt("department_id(FK)");

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
