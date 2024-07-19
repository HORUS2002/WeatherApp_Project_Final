package dbcon;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    // Method to close Connection, Statement and ResultSet
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Overloaded method to close Connection and Statement
    public static void close(Connection conn, Statement stmt) {
        close(conn, stmt, null);
    }

    // Overloaded method to close only Connection
    public static void close(Connection conn) {
        close(conn, null, null);
    }
}