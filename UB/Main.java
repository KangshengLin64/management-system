package UB;


import UB.DB.ConnectionDataBase;
import UB.LoginService.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static Connection conn = null;
    public static PreparedStatement stmt = null;
    public static ResultSet rs = null;
    public static void main(String[] args) throws Exception {
        conn = ConnectionDataBase.ConnectDB();
        if(conn == null){
            System.out.println("Connection fail, please check database information");
        }
	    new Login("User Login");
    }
}