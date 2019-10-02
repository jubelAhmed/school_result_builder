import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.ResultSet;

public class ConnectDatabase {

    public static Connection getConnection() throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test","root","1234");
            return con;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}

