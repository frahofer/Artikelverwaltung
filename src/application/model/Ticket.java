package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class Ticket {
    public int id = 0;
    public String name = "";
    public String description = "";
    public int status_id = 0;
    public int priority_id = 0;

    @Override
    public String toString() {
        return id + ";" + name + ";" + description + ";" + status_id + ";" + priority_id;
    }

    public static ObservableList<Ticket> loadFromFile(String filename){
        ObservableList<Ticket> liste = FXCollections.observableArrayList();

        String line = "";
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(filename));

            try{

                while((line = reader.readLine()) != null){

                    String[] split = line.split(";");
                    Ticket temp = new Ticket();

                    temp.id = Integer.parseInt(split[0]);
                    temp.name = split[1];
                    temp.description = split[2];
                    temp.status_id = Integer.parseInt(split[3]);
                    temp.priority_id = Integer.parseInt(split[4]);

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

}
