package application.model;

public class Department {
    public int id = 0;
    public String  name = "";

    @Override
    public String toString(){
        return id + " " + name;
    }

}
