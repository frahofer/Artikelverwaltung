package application.controller;

import application.model.Status;
import application.model.Ticket;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller_ticket {

    public TextField ticket_name;
    public TextArea ticket_description;

    private ListView<Ticket> selectedView;
    private String filename = "tickets.csv";
    public String newString = "";
    public String oldString = "";

    Ticket temp2 = new Ticket();

    public void initialize(){
        ticket_name.setText("");
        ticket_description.setText("");

    }

    public void passTicket(ListView<Ticket> selectedView){
        this.selectedView = selectedView;
        selectedView.setItems(Ticket.loadFromFile(this.filename));

    }

    public void cancelClicked(ActionEvent actionEvent) {

    }

    public void saveClicked(ActionEvent actionEvent) {


    }
}
