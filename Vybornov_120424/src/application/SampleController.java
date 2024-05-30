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
import javafx.scene.control.cell.PropertyValueFactory;

public class SampleController implements Initializable{

    @FXML
    private Button AddBut;

    @FXML
    private Button DelBut;

    @FXML
    private Button RefBut;

    @FXML
    private TableView<Product> TableV;

    @FXML
    private TextField Text1;

    @FXML
    private TextField Text2;
    
    ObservableList<Product> f = FXCollections.observableArrayList();
    Connection conn;
    //add button
    @FXML
    void AddBut_click(ActionEvent event) throws SQLException {
    	
    	if(Text1.getText()!="" && Text2.getText()!="") {
    	connectToDatabase();
		String sql = "INSERT INTO Product(Product_Name, Cost) VALUE(?,?)";
		var newSql = conn.prepareStatement(sql);
		newSql.setString(1, Text1.getText());
		newSql.setFloat(2, Float.valueOf(Text2.getText()));
		newSql.executeUpdate();
		conn.close();
		updateTable();
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION, "Не все поля заполнены");
    		alert.showAndWait();
    	}
    }
//delete button
    @FXML
    void DelBut_click(ActionEvent event) throws SQLException{
    	
        	connectToDatabase();
    		String sql = "DELETE FROM Product WHERE id = ?";
    		var newSql = conn.prepareStatement(sql);
    		newSql.setInt(1, TableV.getSelectionModel().getSelectedItem().getId());
    		newSql.executeUpdate();
    		conn.close();
    		updateTable();
    }
    //update button
    @FXML
    void UpBut_click(ActionEvent event) throws SQLException {
    	
    	if(Text1.getText()!="" && Text2.getText()!="") {
    	connectToDatabase();
		String sql = "Update Product set Product_Name=?, Cost=? where id=?";
		var newSql = conn.prepareStatement(sql);
		newSql.setString(1, Text1.getText());
		newSql.setFloat(2, Float.valueOf(Text2.getText()));
		newSql.setInt(3, TableV.getSelectionModel().getSelectedItem().getId());
		newSql.executeUpdate();
		conn.close();
		updateTable();
    } else {
		Alert alert = new Alert(AlertType.INFORMATION, "Не все поля заполнены");
		alert.showAndWait();
	}
    }
//refresh button
    @FXML
    void RefBut_click(ActionEvent event) throws Exception {
    	updateTable();
    }
//initialize
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		TableColumn<Product, Integer> idcol = new TableColumn<Product, Integer>("#");
		idcol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
		TableV.getColumns().add(idcol);
		
		TableColumn<Product, String> Product_Namecol = new TableColumn<Product, String>("Наименование продукта");
		Product_Namecol.setCellValueFactory(new PropertyValueFactory<Product, String>("Product_Name"));
		TableV.getColumns().add(Product_Namecol);
		
		TableColumn<Product, String> Costcol = new TableColumn<Product, String>("Цена");
		Costcol.setCellValueFactory(new PropertyValueFactory<Product, String>("Cost"));
		TableV.getColumns().add(Costcol);
		
		try {
			updateTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//update tables
	 void updateTable() throws SQLException {
		 System.out.println("up");
	    	connectToDatabase();
			Statement state = conn.createStatement();
			String sql = "SELECT * FROM Product";
			
			ResultSet rs = state.executeQuery(sql);
			ArrayList<Product> f1 = new ArrayList<Product>();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String Product_Name = rs.getString("Product_Name");
				float Cost = rs.getFloat("Cost");
				f1.add(new Product(id, Product_Name, Cost));
			};
			f = FXCollections.observableArrayList(f1);
			System.out.println(f);
			
			conn.close();
			
			TableV.setItems(f);
			TableV.refresh();
	    }
	 //connect to database
	void connectToDatabase() throws SQLException {
    	String url = "jdbc:mysql://localhost/NeRab";
    	String username = "root";
    	String password = "";
		conn = DriverManager.getConnection(url, username, password);
    }
}
