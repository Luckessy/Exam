package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.cell.PropertyValueFactory;

public class SampleController implements Initializable{

    @FXML
    private Button AddBut;

    @FXML
    private Button DelBut;

    @FXML
    private Button RefBut;

    @FXML
    private TableView<film> TableV;

    @FXML
    private TextField Text1;

    @FXML
    private TextField Text2;
    
    ObservableList<film> f = FXCollections.observableArrayList();
    Connection conn;
    
    @FXML
    void AddBut_click(ActionEvent event) throws SQLException {
    	
    	if(Text1.getText()!="" && Text2.getText()!="") {
    	connectToDatabase();
		String sql = "INSERT INTO Film(operator, rejiser) VALUE(?,?)";
		var newSql = conn.prepareStatement(sql);
		newSql.setString(1, Text1.getText());
		newSql.setString(2, Text2.getText());
		newSql.executeUpdate();
		conn.close();
		updateTable();
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION, "Не все поля заполнены");
    		alert.showAndWait();
    	}
    }

    @FXML
    void DelBut_click(ActionEvent event) throws SQLException{
    	
        	connectToDatabase();
    		String sql = "DELETE FROM Film WHERE id = ?";
    		var newSql = conn.prepareStatement(sql);
    		newSql.setInt(1, TableV.getSelectionModel().getSelectedItem().getId());
    		newSql.executeUpdate();
    		conn.close();
    		updateTable();
    }
    
    @FXML
    void UpBut_click(ActionEvent event) throws SQLException {
    	
    	if(Text1.getText()!="" && Text2.getText()!="") {
    	connectToDatabase();
		String sql = "Update Film set operator=?, rejiser=? where id=?";
		var newSql = conn.prepareStatement(sql);
		newSql.setString(1, Text1.getText());
		newSql.setString(2, Text2.getText());
		newSql.setInt(3, TableV.getSelectionModel().getSelectedItem().getId());
		newSql.executeUpdate();
		conn.close();
		updateTable();
    } else {
		Alert alert = new Alert(AlertType.INFORMATION, "Не все поля заполнены");
		alert.showAndWait();
	}
    }

    @FXML
    void RefBut_click(ActionEvent event) throws Exception {
    	updateTable();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		TableColumn<film, Integer> idcol = new TableColumn<film, Integer>("#");
		idcol.setCellValueFactory(new PropertyValueFactory<film, Integer>("id"));
		TableV.getColumns().add(idcol);
		
		TableColumn<film, String> operatorcol = new TableColumn<film, String>("Оператор");
		operatorcol.setCellValueFactory(new PropertyValueFactory<film, String>("operator"));
		TableV.getColumns().add(operatorcol);
		
		TableColumn<film, String> rejisercol = new TableColumn<film, String>("Режисёр");
		rejisercol.setCellValueFactory(new PropertyValueFactory<film, String>("rejiser"));
		TableV.getColumns().add(rejisercol);
		
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
			String sql = "SELECT * FROM Film";
			
			ResultSet rs = state.executeQuery(sql);
			ArrayList<film> f1 = new ArrayList<film>();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String operator = rs.getString("operator");
				String rejiser = rs.getString("rejiser");
				f1.add(new film(id, operator, rejiser));
			};
			f = FXCollections.observableArrayList(f1);
			System.out.println(f);
			
			conn.close();
			
			TableV.setItems(f);
			TableV.refresh();
	    }
	void connectToDatabase() throws SQLException {
    	String url = "jdbc:mysql://localhost/FilmBD";
    	String username = "root";
    	String password = "";
		conn = DriverManager.getConnection(url, username, password);
    }
}
