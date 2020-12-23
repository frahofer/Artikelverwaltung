package application.controller;

import application.MyFXMLLoader;
import application.model.Priority;
import application.model.Status;
import application.model.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {

    public AnchorPane contentPane;
    public ListView<Ticket> ticketListView;
    public ComboBox<Status> filterStatusComboBox;
    public ComboBox<Priority> filterPriorityComboBox;
    public TextField filterNameTextField;

    public ObservableList<Priority> liste_priority = FXCollections.observableArrayList();
    public ObservableList<Status> liste_status = FXCollections.observableArrayList();
    public ObservableList<Ticket> liste_ticket = FXCollections.observableArrayList();

    public ObservableList<Ticket> listClone_ticket = FXCollections.observableArrayList();
    public ObservableList<Ticket> listClone_ticket_backup = FXCollections.observableArrayList();

    MyFXMLLoader loader = new MyFXMLLoader();
    Controller_ticket controller = new Controller_ticket();

    private String filename_ticket = "tickets.csv";
    private String filename_status = "stati.csv";
    private String filename_priority = "priorities.csv";

    private boolean new_clicked = false;

    private Controller_ticket active = null;

    private Ticket temp = new Ticket();

    public void initialize(){

        Parent root = this.loader.loadFXML("view/ticketPetko.fxml");
        contentPane.getChildren().add(root);

        this.controller = (Controller_ticket) this.loader.getController();
        liste_ticket = Ticket.loadFromFile(filename_ticket);
        liste_status = Status.loadFromFile(filename_status);
        liste_priority = Priority.loadFromFile(filename_priority);

        ticketListView.setItems(liste_ticket);
        filterStatusComboBox.setItems(liste_status);
        filterPriorityComboBox.setItems(liste_priority);

        listClone_ticket_backup = liste_ticket;

    }

    public void editPriorityClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/priorityPetko.fxml", "Priorität bearbeiten");
    }

    public void editUserClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/userFabian.fxml", "User bearbeiten");
    }

    /*
    public void editTicketClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/ticketPetko.fxml", "Ticket bearbeiten");
    }

     */

    public void editCommentClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/commentFabian.fxml", "Comment bearbeiten");
    }

    public void editDepartmentClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/departmentFabian.fxml", "Department bearbeiten");
    }

    public void editOrderClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/orderPetko.fxml", "Order bearbeiten");
    }

    public void editStatusClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/statusPetko.fxml", "Stati bearbeiten");
    }

    public void ticketListViewClicked(MouseEvent mouseEvent) {

        controller.passListView(ticketListView);

        /*
        if (ticketListView.getSelectionModel().getSelectedItem() != null) {

            MyFXMLLoader loader = new MyFXMLLoader();
            Parent root = loader.loadFXML("view/ticket.fxml");
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
            contentPane.getChildren().add(root);

            active = (Controller_ticket) loader.getController();
            active.setTicket(ticketListView.getSelectionModel().getSelectedItem());
            //active.passTicket(ticketListView);
        }
         */

    }


    public void status_ComboBox_Active(ActionEvent actionEvent) {

        listClone_ticket.clear();

        Status selectedStatus = (Status) filterStatusComboBox.getSelectionModel().getSelectedItem();

        for (Ticket ticket : liste_ticket) {

            if (ticket.status_id == selectedStatus.id) {
                listClone_ticket.add(ticket);
            }
        }

        listClone_ticket_backup = listClone_ticket;
        ticketListView.setItems(listClone_ticket_backup);

    }

    public void name_searchField_active(KeyEvent keyEvent) {

        listClone_ticket.clear();

        String searchText = filterNameTextField.getText();

        if (!searchText.equals("")) {

            for (Ticket ticket : liste_ticket) {

                if (ticket.name.contains(searchText)) {
                    listClone_ticket.add(ticket);
                }
            }
            ticketListView.setItems(listClone_ticket);
        } else {
            ticketListView.setItems(listClone_ticket_backup);
        }

        listClone_ticket_backup = listClone_ticket;

    }

    public void priority_ComboBox_Active(ActionEvent actionEvent) {

        listClone_ticket.clear();

        Priority selectedPriority = (Priority) filterPriorityComboBox.getSelectionModel().getSelectedItem();

        for (Ticket ticket : liste_ticket) {

            if (ticket.priority_id == selectedPriority.id) {
                listClone_ticket.add(ticket);
            }
        }

        listClone_ticket_backup = listClone_ticket;
        ticketListView.setItems(listClone_ticket_backup);

    }

    public void newClicked(ActionEvent actionEvent) {

        new_clicked = true;

        controller.newClicked(liste_ticket);

    }

    public void deleteClicked(ActionEvent actionEvent) {

        Ticket selectedTicket = (Ticket) ticketListView.getSelectionModel().getSelectedItem();

        liste_ticket = controller.deleteClicked(selectedTicket, liste_ticket);
        ticketListView.setItems(liste_ticket);

        ticketListView.refresh();
    }

    public void saveClicked(ActionEvent actionEvent) {

        temp = controller.getTicket();

        controller.saveClicked(temp, new_clicked);

        liste_ticket.add(temp);
        new_clicked = false;
        ticketListView.refresh();

        // Wenn Ticket neu -> laden des Tickets und hinzufügen zur Liste!
        // Datei aktualisieren
    }
}
