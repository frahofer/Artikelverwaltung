package application.model;

public class Users {
    public int id = 0;
    public String name = "";
    public String titel = "";
    public String street = "";
    public int zip = 0;
    public String city = "";
    public int depId = 0;

    @Override
    public String toString() {
        return name + " " + titel + " " + street;
    }
}
