package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AuthController {

    @FXML
    private Button BurEx;

    @FXML
    private Button ButLog;

    @FXML
    private TextField TextLog;

    @FXML
    private TextField TextPass;

    @FXML
    void BurEx_click(ActionEvent event) {
		Stage stage = (Stage)BurEx.getScene().getWindow();
		stage.close();
    }
    ResultSet resultSet;
    @FXML
    void ButLog_click(ActionEvent event) throws IOException {
    	
    	MysqlDataSource dataSource;
    	dataSource = new MysqlDataSource();
    	
    		dataSource.setServerName("localhost");
    		dataSource.setPortNumber(3306);
    		dataSource.setDatabaseName("FilmBD");
    		dataSource.setUser("root");
    		dataSource.setPassword("");
    		
    		JdbcTemplate JdbcTemplate = new JdbcTemplate(dataSource);
    		
    	String sql = String.format("select * from Users where Login='%s' " + "and Pass='%s' ", 
    			TextLog.getText(),TextPass.getText());
    	System.out.println(sql);
    	
    	List<String> users = JdbcTemplate.query(sql, 
    			(resultSet, rowNum)->{
    				String user="";
    				return user;
    			});
    	if(!users.isEmpty()) {
    	BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage)ButLog.getScene().getWindow();
		stage.setScene(scene);
		
    	}
    }

}
