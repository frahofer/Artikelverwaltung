package application.model;

public class Status {

    public int id = 0;
    public String  name = "";

    @Override
    public String toString(){
        return id + " " + name;
    }

}
