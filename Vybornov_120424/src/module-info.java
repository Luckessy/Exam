module Vybornov_120424 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires spring.jdbc;
	requires java.sql;
	requires mysql.connector.java;
	
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
}
