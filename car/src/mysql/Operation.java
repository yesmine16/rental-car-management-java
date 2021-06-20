package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Operation {public static boolean isLogin(String username, String password){
    try{
        Connection myConn =MySQLConnection.getConnection();
        String mySqlQuery = "SELECT * FROM `login` WHERE `username` =? AND `password` =?";
       
        PreparedStatement ps = myConn.prepareStatement(mySqlQuery);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet resultSet = ps.executeQuery();
        
        while(resultSet.next()){
           return true;
           }

    }catch (Exception ex){
   
    }
	return false;
    
}

}
