package application.controller;

import application.MyFXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {

    public TextField comment_title;
    public TextField comment_date;
    public TextArea comment_texArea;
    public AnchorPane contentPane;
    public ListView ticketListView;


    public void editPriorityClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/priorityPetko.fxml", "Priorität bearbeiten");
    }

    public void editUserClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/userFabian.fxml", "Priorität bearbeiten");
    }

    public void editTicketClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/ticketPetko.fxml", "Priorität bearbeiten");
    }

    public void editCommentClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/commentFabian.fxml", "Priorität bearbeiten");
    }

    public void editDepartmentClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/departmentFabian.fxml", "Priorität bearbeiten");
    }

    public void editOrderClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/orderPetko.fxml", "Priorität bearbeiten");
    }

    public void editStatusClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/statusPetko.fxml", "Stati bearbeiten");
    }

    public void ticketListViewClicked(MouseEvent mouseEvent) {

        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root = loader.loadFXML("view/ticketPetko.fxml");
        contentPane.getChildren().add(root);

        Controller_ticket controller = (Controller_ticket) loader.getController();
    }

}
