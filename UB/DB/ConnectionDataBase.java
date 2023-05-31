package UB.DB;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionDataBase {
    public static Connection ConnectDB() throws Exception{
        Properties prop = new Properties();
        prop.load(new FileReader("database.txt"));      //读取database.txt文件的内容

        String drivers = prop.getProperty("drivers");
        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        Class.forName(drivers);                                 // 加载驱动程序
        Connection conn = DriverManager.getConnection(url, user, password); // 连续数据库
        if(conn == null) {
            System.out.println("connection fail.");
        }
        return conn;
    }
}
