package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegController {

    @FXML
    private Button BurEx;

    @FXML
    private Button ButLog;

    @FXML
    private TextField TextLog;

    @FXML
    private TextField TextPass;

    @FXML
    void BurEx_click(ActionEvent event) throws IOException {
    	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Auth.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage)ButLog.getScene().getWindow();
		stage.setScene(scene);
    }
    Connection conn;
    @FXML
    void ButLog_click(ActionEvent event) throws SQLException, IOException {
    	if(TextLog.getText()!="" && TextPass.getText()!="") {
        	connectToDatabase();
    		String sql = "INSERT INTO User(Login, Pass) VALUE(?,?)";
    		var newSql = conn.prepareStatement(sql);
    		newSql.setString(1, TextLog.getText());
    		newSql.setString(2, TextPass.getText());
    		newSql.executeUpdate();
    		conn.close();
    		
    		Alert alert = new Alert(AlertType.INFORMATION, "Новый пользователь зарегистрирован!");
    		alert.showAndWait();
    		BurEx_click(event);
    		
        	} else {
        		Alert alert = new Alert(AlertType.INFORMATION, "Не все поля заполнены");
        		alert.showAndWait();
        	}
    }
	 //connect to database
	void connectToDatabase() throws SQLException {
   	String url = "jdbc:mysql://localhost/NeRab";
   	String username = "root";
   	String password = "";
		conn = DriverManager.getConnection(url, username, password);
   
}
	}
