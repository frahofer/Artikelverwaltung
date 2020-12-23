package application.controller;

import application.model.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private Ticket ticket = new Ticket();
    private Ticket temp2 = new Ticket();

    public void initialize(){
        ticket_name.setText("");
        ticket_description.setText("");
        status_nr.setText("");
        priority_nr.setText("");

    }

    public void passListView(ListView<Ticket> listView){

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

    public void saveClicked(Ticket selectedTicket, boolean new_clicked) {

        selectedTicket.id = this.ticket_id;
        selectedTicket.name = ticket_name.getText();
        selectedTicket.description = ticket_description.getText();
        selectedTicket.status_id = Integer.parseInt(status_nr.getText());
        selectedTicket.priority_id = Integer.parseInt(priority_nr.getText());


        if(new_clicked){
            selectedTicket.saveNewTicket(selectedTicket, this.filename);
        }else{
            newString = selectedTicket.id + ";" + selectedTicket.name + ";" + selectedTicket.description + ";" + selectedTicket.status_id + ";" + selectedTicket.priority_id;

            Ticket.writeToFile(this.oldString, newString, this.filename);
        }

        //status_listView.refresh();

    }

    public ObservableList<Ticket> deleteClicked(Ticket selectedTicket, ObservableList<Ticket> liste_ticket) {
        // Laden des Tickets
        selectedTicket.id = ticket_id;
        selectedTicket.name = ticket_name.getText();
        selectedTicket.description = ticket_description.getText();
        selectedTicket.status_id = Integer.parseInt(status_nr.getText());
        selectedTicket.priority_id = Integer.parseInt(priority_nr.getText());

        // Entfernen aus ListView
        liste_ticket.remove(selectedTicket);

        // Datei aktualisierne
        String oldString2 = selectedTicket.id + ";" + selectedTicket.name + ";" + selectedTicket.description + ";" + selectedTicket.status_id + ";" + selectedTicket.priority_id + "\n";

        Ticket.writeToFile(oldString2, "", this.filename);

        return liste_ticket;
    }

    public void setTicket(Ticket t) {
        this.ticket = t;

        ticket_name.setText(t.name);
        ticket_description.setText(t.description);
    }

    public Ticket getTicket() {

        //übergeben der Ticket-Daten

        ticket.id = this.ticket_id;
        ticket.name = ticket_name.getText();
        ticket.description = ticket_description.getText();
        ticket.status_id = Integer.parseInt(status_nr.getText());
        ticket.priority_id = Integer.parseInt(priority_nr.getText());

        return ticket;
    }

    public void newClicked(ObservableList<Ticket> liste) {
        int index = liste.size() - 1;

        ticket_id = liste.get(index).id + 1;
        ticket_name.clear();
        ticket_description.clear();
        status_nr.clear();
        priority_nr.clear();

    }
}
