package mysql;
import java.sql.DriverManager;
import java.sql.Connection;

public class MySQLConnection {
    public static Connection getConnection() throws Exception{
        String url = "jdbc:mysql://localhost:3306/car";
        String user = "root";
        String password = "";
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con =DriverManager.getConnection(url, user, password);
        return con;
    }
}


