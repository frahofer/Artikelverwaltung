package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class Status {

    public int id = 0;
    public String  name = "";

    @Override
    public String toString(){
        return id + " " + name;
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
