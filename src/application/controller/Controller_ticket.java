package application.controller;

import application.model.Priority;
import application.model.Status;
import application.model.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller_ticket {

    public TextField ticket_name;
    public TextArea ticket_description;
    public ComboBox<Status> statusComboBox;
    public ComboBox<Priority> priorityComboBox;

    private String filename = "tickets.csv";
    private String filename_status = "stati.csv";
    private String filename_priority = "priorities.csv";
    public String newString = "";
    public String oldString = "";

    private int ticket_id = 0;

    public ObservableList<Priority> liste_priority = FXCollections.observableArrayList();
    public ObservableList<Status> liste_status = FXCollections.observableArrayList();

    private Ticket ticket = new Ticket();
    private Ticket temp2 = new Ticket();

    public void initialize(){
        ticket_name.setText("");
        ticket_description.setText("");

        liste_status = Status.loadList();
        liste_priority = Priority.loadList();

        statusComboBox.setItems(liste_status);
        priorityComboBox.setItems(liste_priority);

    }

    public void passListView(ListView<Ticket> listView){

        temp2 = (Ticket) listView.getSelectionModel().getSelectedItem();

        this.ticket_id = temp2.id;
        ticket_name.setText(temp2.name);
        ticket_description.setText(temp2.description);

        refreshComboBox(temp2);

        //oldString für saveClicked
        this.oldString = temp2.id + ";" + temp2.name + ";" + temp2.description + ";" + temp2.status_id + ";" + temp2.priority_id;

    }

    public ObservableList<Ticket> saveClicked(Ticket selectedTicket, boolean new_clicked, ObservableList<Ticket> liste) {

        Status selectedStatus = statusComboBox.getSelectionModel().getSelectedItem();
        Priority selectedPriority = priorityComboBox.getSelectionModel().getSelectedItem();

        selectedTicket.id = this.ticket_id;
        selectedTicket.name = ticket_name.getText();
        selectedTicket.description = ticket_description.getText();
        selectedTicket.status_id = selectedStatus.id;
        selectedTicket.priority_id = selectedPriority.id;

        if(new_clicked){
            selectedTicket.saveNewTicket(selectedTicket, this.filename);
            liste.add(selectedTicket);
        }else{
            newString = selectedTicket.id + ";" + selectedTicket.name + ";" + selectedTicket.description + ";" + selectedTicket.status_id + ";" + selectedTicket.priority_id;

            Ticket.writeToFile(this.oldString, newString, this.filename);
        }

        refreshComboBox(selectedTicket);
        //status_listView.refresh();

        return liste;

    }

    public ObservableList<Ticket> deleteClicked(Ticket selectedTicket, ObservableList<Ticket> liste_ticket) {

        Status selectedStatus = statusComboBox.getSelectionModel().getSelectedItem();
        Priority selectedPriority = priorityComboBox.getSelectionModel().getSelectedItem();

        // Laden des Tickets
        selectedTicket.id = ticket_id;
        selectedTicket.name = ticket_name.getText();
        selectedTicket.description = ticket_description.getText();
        selectedTicket.status_id = selectedStatus.id;
        selectedTicket.priority_id = selectedPriority.id;

        // Entfernen aus ListView
        liste_ticket.remove(selectedTicket);

        // Datei aktualisieren
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

        Status selectedStatus = statusComboBox.getSelectionModel().getSelectedItem();
        Priority selectedPriority = priorityComboBox.getSelectionModel().getSelectedItem();

        ticket.id = this.ticket_id;
        ticket.name = ticket_name.getText();
        ticket.description = ticket_description.getText();
        ticket.status_id = selectedStatus.id;
        ticket.priority_id = selectedPriority.id;

        return ticket;
    }

    public void newClicked(ObservableList<Ticket> liste) {
        int index = liste.size() - 1;

        ticket_id = liste.get(index).id + 1;
        ticket_name.clear();
        ticket_description.clear();
        statusComboBox.setItems(liste_status);
        priorityComboBox.setItems(liste_priority);

    }

    public void refreshComboBox(Ticket temp3){

        for(Status s: liste_status){
            if(temp3.status_id == s.id){
                statusComboBox.getSelectionModel().select(s);
            }
        }
        for(Priority p: liste_priority){
            if(temp3.priority_id == p.id){
                priorityComboBox.getSelectionModel().select(p);
            }
        }
    }

}
