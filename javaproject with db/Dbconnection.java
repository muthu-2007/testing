import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {
	public static Connection con = null;
	public Dbconnection() {}
	
	public static Connection getConnection() {
		if (con == null) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_management", "root" , "Muthu@2007");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
}
