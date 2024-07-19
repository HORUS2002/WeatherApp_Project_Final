package dbcon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	//JDBC URL, User name and Password of MySQL Server
	private static final String URL = "jdbc:mysql://localhost:3306/clinic";
    private static final String USER = "root";             //Change to your user name
    private static final String PASSWORD = "wrTp-yO%m@S7"; //Change to your password   
    private static Connection connection = null;

    // Method to establish a database connection
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to database successfully!");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new SQLException("MySQL JDBC driver not found.", e);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException("Failed to connect to database.", e);
            }
        }
        return connection;
    }
}