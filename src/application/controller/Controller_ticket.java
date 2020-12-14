package application.controller;

import application.model.Ticket;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller_ticket {

    public TextField ticket_name;
    public TextArea ticket_description;
    public TextField status_nr;
    public TextField priority_nr;

    private String filename = "tickets.csv";
    public String newString = "";
    public String oldString = "";

    private int ticket_id = 0;

    Ticket temp2 = new Ticket();

    public void initialize(){
        ticket_name.setText("");
        ticket_description.setText("");
        status_nr.setText("");
        priority_nr.setText("");

    }

    public void initiateListView(ListView<Ticket> listView){
        listView.setItems(Ticket.loadFromFile(this.filename));
    }

    public void passTicket(ListView<Ticket> listView){

        temp2 = (Ticket) listView.getSelectionModel().getSelectedItem();

        this.ticket_id = temp2.id;
        ticket_name.setText(temp2.name);
        ticket_description.setText(temp2.description);
        status_nr.setText(Integer.toString(temp2.status_id));
        priority_nr.setText(Integer.toString(temp2.priority_id));

        //oldString für saveClicked
        this.oldString = temp2.id + ";" + temp2.name + ";" + temp2.description + ";" + temp2.status_id + ";" + temp2.priority_id;
    }

    public void cancelClicked(ActionEvent actionEvent) {

        //setzte Felder zurück auf den letzten ausgewählten status

        this.ticket_id = temp2.id;
        ticket_name.setText(temp2.name);
        ticket_description.setText(temp2.description);
        status_nr.setText(Integer.toString(temp2.status_id));
        priority_nr.setText(Integer.toString(temp2.priority_id));

    }

    public void saveClicked(ActionEvent actionEvent) {

        temp2.id = this.ticket_id;
        temp2.name = ticket_name.getText();
        temp2.description = ticket_description.getText();
        temp2.status_id = Integer.parseInt(status_nr.getText());
        temp2.priority_id = Integer.parseInt(priority_nr.getText());

        newString = temp2.id + ";" + temp2.name + ";" + temp2.description + ";" + temp2.status_id + ";" + temp2.priority_id;

        Ticket.writeToFile(this.oldString, newString, this.filename);

        //status_listView.refresh();

    }
}
