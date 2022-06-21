package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException{
	try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestiondocument", "root", "Math0623736244")){
		
		try(PreparedStatement prepstmt = 
				connection.prepareStatement("Select * from topic")){
			try(ResultSet rs = prepstmt.executeQuery()){
				while(rs.next()) {
					String data = rs.getString("topicname");
					System.out.println("topic name : " + data);
				}
			}
		}
						
	}catch(SQLException ex) {
		System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	}	
	}

}
