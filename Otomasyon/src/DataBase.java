import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
	
	static Connection myConnection;
	static Statement myStatement;
	
	static ResultSet veriAl(String sorgu) {
		
		ResultSet myResultSet = null;
		
		try {
			String url = "jdbc:mysql://localhost:3306/otomasyon?Unicode=true&useLegacyDatetime=false&serverTimezone=Turkey";
			myConnection = DriverManager.getConnection(url , "root" , "0545");
			myStatement = myConnection.createStatement();
			myResultSet = myStatement.executeQuery(sorgu);
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return myResultSet;
	}
	
	void ekle_guncelle_sil (String mySQL_sorgu) {
		
		try {
			
			String url = "jdbc:mysql://localhost:3306/otomasyon?Unicode=true&useLegacyDatetime=false&serverTimezone=Turkey";
			myConnection = DriverManager.getConnection(url , "root" , "0545");
			myStatement = myConnection.createStatement();
			
			myStatement.executeUpdate(mySQL_sorgu);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}