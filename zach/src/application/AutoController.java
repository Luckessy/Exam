package application;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.protocol.ResultsetRow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AutoController implements Initializable{

    @FXML
    private Button Button_Click;

    @FXML
    private TextField Text_Login;

    @FXML
    private TextField Text_Password;
    
    ResultSet resultSet;
	MysqlDataSource datasource;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
    @FXML
    void Click(ActionEvent event) throws IOException {
    	
    	datasource = new MysqlDataSource();
		datasource.setServerName("localhost");
		datasource.setPort(3306);
		datasource.setDatabaseName("DB");
		datasource.setUser("root");
		datasource.setPassword("");

		
		JdbcTemplate jb = new JdbcTemplate(datasource) ;
		String sql = String.format("select * from Users where login='%s'" + "and pass ='%s'", Text_Login.getText(),Text_Password.getText());
			System.out.println(sql);
			
		List<String> users = jb.query(sql,
				(resultSet,rowNum)->{
					
					String user="";
					return user;
				});
		
		if(!users.isEmpty())
		{
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root);
			Stage stage =(Stage)Button_Click.getScene().getWindow();
			stage.setScene(scene);
			
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("ERROR");
			alert.show();
		}
			}
		
    }



