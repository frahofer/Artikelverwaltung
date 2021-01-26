package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.*;

public class Ticket {
    public int id = 0;
    public String name = "";
    public String description = "";
    public Status status = null;
    public int status_id = 0;
    public Priority priority = null;
    public int priority_id = 0;
    public int order_id = 0;

    @Override
    public String toString() {
        return id + ";" + name + ";" + description + ";" + status_id + ";" + priority_id;
    }

    public Ticket(int id, String name, String description, Status status, Priority priority){
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;

    }

    public void update(){
        try{
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE ticket SET name = ?, description = ?, priority_id(FK) = ?, status_id(FK) = ? WHERE ticket_id = ?");
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setInt(3, priority.id);
            statement.setInt(4, status.id);
            statement.setInt(5, id);

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void saveNewTicket() {
        try{
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("INSERT INTO ticket SET ticket_id = ?, name = ?, description = ?, priority_id(FK) = ?, status_id(FK) = ?");
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setInt(4, priority.id);
            statement.setInt(5, status.id);

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static ObservableList<Ticket> loadList(){
        ObservableList<Ticket> list = FXCollections.observableArrayList();

        try{
            Connection connection = AccessDb.getConnection();

            Statement statement = null;

            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM ticket");

            while(result.next()){
                Ticket t = new Ticket(
                        result.getInt("ticket_id"),
                        result.getString("name"),
                        result.getString("description"),
                        Status.getbyId(result.getInt("order_id(FK)")),
                        Priority.getbyId(result.getInt("priority_id(FK)"))
                );

                list.add(t);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

}
