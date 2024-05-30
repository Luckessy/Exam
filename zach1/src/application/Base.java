package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Base {

	public static boolean isAdmin = false;
	
	public static Connection GetCon() throws SQLException{
		var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB","root","");
		return conn;
	}
	private static List<Rabotnik> getRa() throws SQLException{
		var conn = GetCon();
		var prepareStatement = conn.prepareStatement("select * from Rabotnik");
		var resultSet = prepareStatement.executeQuery();
		ArrayList<Rabotnik> rab = new ArrayList<Rabotnik>();
		while(resultSet.next())
		{
			var id = resultSet.getString("id");
		}
		return rab;
		
	}
}
