package UB.domain;

import UB.LoginService.Login;
import UB.Main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User {
    private Integer userId;
    private String username;
    private String password;
    private Date createdTime;

    public static Map<String, User> userMap = new HashMap<>();

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public User(Integer userId, String username, String password, String createdTime) throws ParseException {
        this.userId = userId;
        this.username = username;
        this.password = password;

        this.createdTime = Login.sdf.parse(createdTime);
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "user{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }

    public static void defaultUserMap(){
        try{
            userMap.clear();
            String sql = "select * from User";
            Main.stmt = Main.conn.prepareStatement(sql);
            Main.rs = Main.stmt.executeQuery();

            while(Main.rs.next()){
                Integer userId = Main.rs.getInt("userId");
                String username = Main.rs.getString("username");
                String passWord = Main.rs.getString("password");

                userMap.put(username, new User(userId, username, passWord, sdf.format(Main.rs.getDate("createdTime")) ));
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }

}