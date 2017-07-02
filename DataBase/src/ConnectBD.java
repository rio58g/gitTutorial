import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectBD {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		ResultSet res = null;
		
		String url = "jdbc:postgresql://localhost:5432/bd_test";
		String login = "postgres";
		String pass = "sdfet45";
		
		System.out.println("Table Users:");
		
		try {
			conn = DriverManager.getConnection(url, login, pass);
			st = conn.createStatement();
			res = st.executeQuery("SELECT * FROM \"USERS\"");
			
			while (res.next()){
				System.out.println(res.getString(1) + " " +  res.getString(2) + " " +  res.getString(3));
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}finally {
			try {
				res.close();
				st.close();
				conn.close();
			}catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
}
