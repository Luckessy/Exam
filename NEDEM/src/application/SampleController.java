package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SampleController implements Initializable{

    @FXML
    private ListView<Order> ListV;

    @FXML
    private Button TextAdd;

    @FXML
    private TextField TextClient;

    @FXML
    private TextField TextDate;

    @FXML
    private Button TextDelete;

    @FXML
    private TextField TextDescription;

    @FXML
    private TextField TextEquipment;

    @FXML
    private TextField TextOdrerNum;

    @FXML
    private Button TextRefresh;

    @FXML
    private TextField TextStatys;

    @FXML
    private TextField TextType;

    @FXML
    void TextAdd_click(ActionEvent event) {

    }

    @FXML
    void TextDelete_click(ActionEvent event) {

    }

    @FXML
    void TextRefresh_click(ActionEvent event) {

    }
    
    ObservableList<Order> order = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	ListV.setItems(order);	
	order.add(new Order(1,"12.12.23","Pc","xxx","ooo",1,"accepted"));
	}
    
    
}
