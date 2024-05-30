module NEDEM {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires mysql.connector.java;
	requires spring.jdbc;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
}
