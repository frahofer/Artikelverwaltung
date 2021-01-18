package application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccessDb {

    static {
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private static Connection connection = null;

    public static Connection getConnection(){
        if(connection == null){
            try{
                connection = DriverManager.getConnection("jdbc:ucanaccess://db/ER_Diagramm_frahofer.accdb");
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }

        return connection;

    }


}
