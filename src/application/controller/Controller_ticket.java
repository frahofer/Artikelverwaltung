package application.controller;

import application.MyFXMLLoader;
import application.model.Priority;
import application.model.Status;
import application.model.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class Controller_ticket {

    public TextField ticket_name;
    public TextArea ticket_description;
    public ComboBox<Status> statusComboBox;
    public ComboBox<Priority> priorityComboBox;
    public ListView ticket_users_listView;
    public Button edit_users;

    private String filename = "tickets.csv";
    private String filename_status = "stati.csv";
    private String filename_priority = "priorities.csv";

    private int ticket_id = 0;

    public ObservableList<Priority> liste_priority = FXCollections.observableArrayList();
    public ObservableList<Status> liste_status = FXCollections.observableArrayList();

    private Ticket ticket = null;
    private Ticket temp2 = null;

    public void initialize(){
        ticket_name.setText("");
        ticket_description.setText("");

        liste_status = Status.loadList();
        liste_priority = Priority.loadList();

        statusComboBox.setItems(liste_status);
        priorityComboBox.setItems(liste_priority);

    }

    public void passListView(ListView<Ticket> listView){

        temp2 = listView.getSelectionModel().getSelectedItem();

        this.ticket_id = temp2.id;
        ticket_name.setText(temp2.name);
        ticket_description.setText(temp2.description);

        statusComboBox.setValue(temp2.status);
        priorityComboBox.setValue(temp2.priority);

    }

    public ObservableList<Ticket> saveClicked(Ticket selectedTicket, boolean new_clicked, ObservableList<Ticket> liste) {

        selectedTicket = new Ticket(
                ticket_id,
                ticket_name.getText(),
                ticket_description.getText(),
                statusComboBox.getValue(),
                priorityComboBox.getValue()
        );

        if(new_clicked){
            selectedTicket.saveNewTicket();
            liste.add(selectedTicket);
        }else{
            selectedTicket.update();
        }

        refreshComboBox(selectedTicket);

        return liste;

    }

    public ObservableList<Ticket> deleteClicked(Ticket selectedTicket, ObservableList<Ticket> liste_ticket) {

        Status selectedStatus = statusComboBox.getSelectionModel().getSelectedItem();
        Priority selectedPriority = priorityComboBox.getSelectionModel().getSelectedItem();

        // Laden des Tickets
        selectedTicket = new Ticket(
                ticket_id,
                ticket_name.getText(),
                ticket_description.getText(),
                Status.getbyId(selectedStatus.id),
                Priority.getbyId(selectedPriority.id)
        );

        // Entfernen aus ListView
        liste_ticket.remove(selectedTicket);

        selectedTicket.update();

        return liste_ticket;
    }

    public void setTicket(Ticket t) {
        this.ticket = t;

        ticket_name.setText(t.name);
        ticket_description.setText(t.description);
    }

    public Ticket getTicket() {

        //übergeben der Ticket-Daten

        ticket = new Ticket(
                ticket_id,
                ticket_name.getText(),
                ticket_description.getText(),
                statusComboBox.getValue(),
                priorityComboBox.getValue()
        );

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

    public void ticket_users_listView_clicked(MouseEvent mouseEvent) {
    }

    public void edit_users_clicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/users_to_ticket.fxml", "Edit Users");
    }
}
