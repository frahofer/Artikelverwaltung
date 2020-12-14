package application.controller;

import application.MyFXMLLoader;
import application.model.Status;
import application.model.Ticket;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {

    public AnchorPane contentPane;
    public ListView<Ticket> ticketListView;

    MyFXMLLoader loader = new MyFXMLLoader();
    Controller_ticket controller = new Controller_ticket();

    public void initialize(){

        Parent root = this.loader.loadFXML("view/ticketPetko.fxml");
        contentPane.getChildren().add(root);

        this.controller = (Controller_ticket) this.loader.getController();
        this.controller.initiateListView(ticketListView);

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

        controller.passTicket(ticketListView);

    }

}
