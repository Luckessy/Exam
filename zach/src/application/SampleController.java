package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class SampleController implements Initializable{

    @FXML
    private Button Add_Button;

    @FXML
    private Button Delete_Button;

    @FXML
    private TableColumn<Rabotnik, String> TabFIO;

    @FXML
    private TableColumn<Rabotnik, Integer> TabID;

    @FXML
    private TableColumn<Rabotnik, String> TabZP;

    @FXML
    private TableView<Rabotnik> TableView;

    @FXML
    private TextField Text_FIO;
    
    @FXML
    private TextField Text_ZP;

    @FXML
    private Button Upd_Button;
    
    ObservableList<Rabotnik> Rab = FXCollections.observableArrayList();
 
    Connection conn;
    @FXML
    void Add(ActionEvent event) throws SQLException{
    	if(Text_FIO.getText()!="" && Text_ZP.getText()!="") {
    	connectToDatabase();
		String sql = "INSERT INTO Rabotnik(FIO, zarplata) VALUE(?,?)";
		var newSql = conn.prepareStatement(sql);
		newSql.setString(1, Text_FIO.getText());
		newSql.setString(2, Text_ZP.getText());
		
		newSql.executeUpdate();
		conn.close();
		updateTable();
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION, "Не все поля заполнены");
    		alert.showAndWait();
    	}
}
    void connectToDatabase() throws SQLException {
    	String url = "jdbc:mysql://localhost/DB";
    	String username = "root";
    	String password = "";
    	conn = DriverManager.getConnection(url,username,password);
    
    }
    @FXML
    void Del(ActionEvent event) throws SQLException {
    	connectToDatabase();
		String sql = "DELETE FROM Rabotnik WHERE id = ?";
		var newSql = conn.prepareStatement(sql);
		newSql.setInt(1, TableView.getSelectionModel().getSelectedItem().getId());
		newSql.executeUpdate();
		conn.close();
		updateTable();
    }

    @FXML
    void Upd(ActionEvent event) throws SQLException {

    	if(Text_FIO.getText()!="" && Text_ZP.getText()!="") {
    	connectToDatabase();
		String sql = "Update Rabotnik set FIO=?, zarplata=? where id=?";
		var newSql = conn.prepareStatement(sql);
		newSql.setString(1, Text_FIO.getText());
		newSql.setString(2, Text_ZP.getText());
		newSql.setInt(3, TableView.getSelectionModel().getSelectedItem().getId());
		newSql.executeUpdate();
		conn.close();
		updateTable();
    } else {
		Alert alert = new Alert(AlertType.INFORMATION, "Не заполнены поля");
		alert.showAndWait();
	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		TabID.setCellValueFactory(new PropertyValueFactory<Rabotnik, Integer>("id"));
		TabFIO.setCellValueFactory(new PropertyValueFactory<Rabotnik, String>("FIO"));
		TabZP.setCellValueFactory(new PropertyValueFactory<Rabotnik, String>("ZP"));
		Text_ZP.setTextFormatter(new TextFormatter<>(c->c.getControlNewText().matches("\\d{0,10}") ? c:null));
		try {
			updateTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 void updateTable() throws SQLException {
		 System.out.println("up");
	    	connectToDatabase();
			Statement state = conn.createStatement();
			String sql = "SELECT * FROM Rabotnik";
			
			ResultSet rs = state.executeQuery(sql);
			ArrayList<Rabotnik> Rab1 = new ArrayList<Rabotnik>();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String FIO = rs.getString("FIO");
				String ZP = rs.getString("zarplata");
				Rab1.add(new Rabotnik(id, FIO , ZP));
			};
			Rab = FXCollections.observableArrayList(Rab1);
			System.out.println(Rab);
			conn.close();
			TableView.setItems(Rab);
			TableView.refresh();
	    }

}
