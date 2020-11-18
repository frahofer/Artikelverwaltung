import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    //user.fxml

    public ListView user_listView;
    public TextField user_name;
    public TextField user_id;
    public TextField user_title;
    public TextField user_street;
    public TextField user_zip;
    public TextField user_city;
    public TextField user_country;
    public ChoiceBox user_department_dropdown;

    //order.fxml

    public ListView orders_listView;
    public TextField order_id;
    public TextField order_name;
    public TextArea order_description_textArea;

    //department.fxml

    public ListView department_listView;
    public TextField department_id;
    public TextField department_name;

    //status.fxml

    public ListView status_listView;
    public TextField status_id;
    public TextField status_name;

    //prioity.fxml

    public ListView priority_listView;
    public TextField priority_id;
    public TextField priority_name;
    
    //ticket.fxml

    public ListView ticket_listView;
    public ChoiceBox ticket_order_id_dropdown;
    public ChoiceBox ticket_priority_dropdown;
    public ChoiceBox ticket_status_id_dropdown;
    public TextArea ticket_description;
    
    //comment.fxml
    
    public ListView comment_listView;
    public TextField comment_id;
    public TextField comment_title;
    public TextField comment_date;
    public TextArea comment_texArea;
    public ChoiceBox comment_ticket_id_dropdown;
    public ChoiceBox comment_user_id_dropdown;

    public void new_user_clicked(ActionEvent actionEvent) {
    }

    public void edit_user_clicked(ActionEvent actionEvent) {
    }

    public void delete_user_clicked(ActionEvent actionEvent) {
    }

    public void new_order_clicked(ActionEvent actionEvent) {
    }

    public void edit_order_clicked(ActionEvent actionEvent) {
    }

    public void delete_order_clicked(ActionEvent actionEvent) {
    }

    public void new_department_clicked(ActionEvent actionEvent) {
    }

    public void edit_department_clicked(ActionEvent actionEvent) {
    }

    public void delete_department_clicked(ActionEvent actionEvent) {
    }

    public void new_status_clicked(ActionEvent actionEvent) {
    }

    public void edit_status_clicked(ActionEvent actionEvent) {
    }

    public void delete_status_clicked(ActionEvent actionEvent) {
    }

    public void new_priority_clicked(ActionEvent actionEvent) {
    }

    public void edit_priority_clicked(ActionEvent actionEvent) {
    }

    public void delete_priority_clicked(ActionEvent actionEvent) {
    }

    public void new_ticket_clicked(ActionEvent actionEvent) {
    }

    public void edit_ticket_clicked(ActionEvent actionEvent) {
    }

    public void delete_ticket_clicked(ActionEvent actionEvent) {
    }

    public void new_comment_clicked(ActionEvent actionEvent) {
    }

    public void edit_comment_clicked(ActionEvent actionEvent) {
    }

    public void delete_comment_clicked(ActionEvent actionEvent) {
    }
}
