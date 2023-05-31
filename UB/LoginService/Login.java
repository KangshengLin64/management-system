package UB.LoginService;

import UB.FunctionService.BigFunction;
import UB.Main;
import UB.domain.User;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class Login extends JFrame{
    private JButton button1, button2;
    private JLabel label, label1, label2;

    private JTextField textField;
    private JPasswordField password;
    private JPanel panel1, panel2;

    public static User user;



    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");


    public Login(String title) {
        User.defaultUserMap();
        Font font = new Font("", Font.BOLD, 24);
        Font font1 = new Font("", Font.BOLD, 30);
        label = new JLabel("UB Stroage System");
        label.setFont(font1);

        label1 = new JLabel("Username:");
        label1.setFont(font);
        textField = new JTextField(10);
        textField.setFont(font);


        label2 = new JLabel("Password:");
        label2.setFont(font);
        password = new JPasswordField(10);
        password.setFont(font);



        button1 = new JButton("Login");
        button1.setFont(font);
        button2 = new JButton("Register");
        button2.setFont(font);


        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(label);
        panel1.add(Box.createVerticalStrut(40));

        panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());

        JPanel panel21 = new JPanel();
        panel21.setLayout(new FlowLayout());
        panel21.add(label1);
        panel21.add(textField);

        JPanel panel22 = new JPanel();
        panel22.setLayout(new FlowLayout());
        panel22.add(label2);
        panel22.add(password);

        panel22.add(Box.createHorizontalStrut(40));


        JPanel panel221 = new JPanel();
        panel221.add(button1);
        panel221.add(Box.createHorizontalStrut(80));
        panel221.add(button2);

        panel22.add(panel221);

        JPanel panel23 = new JPanel();
//
        panel23.add(Box.createVerticalStrut(60));

        panel2.add(panel21, BorderLayout.NORTH);
        panel2.add(panel22, BorderLayout.CENTER);
        panel2.add(panel23, BorderLayout.SOUTH);

        this.setLayout(new BorderLayout());
        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);

        setSize(420,348);
        setLocationRelativeTo(null);
        setTitle(title);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//按关闭的操作

        button1.addActionListener(new LoginButton(this));   //登录按钮监听
        button2.addActionListener(new LogoutButton());
    }


    public static Boolean isCheckUser(String usernameStr){ //检测用户是否注册
        try{
            String sql = "select username from User";
            Main.stmt = Main.conn.prepareStatement(sql);
            Main.rs = Main.stmt.executeQuery();
            while(Main.rs.next()){
                String username = Main.rs.getString("username");
                if(usernameStr.equals(username)){
                    return true;
                }
            }
            return false;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }





    public class LoginButton implements ActionListener{
        private Login login;
        public LoginButton(Login login){
            this.login = login;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                String usernameStr = textField.getText();


                String passwordStr = new String(password.getPassword());
                String sql = "select * from User";
                Main.stmt = Main.conn.prepareStatement(sql);
                Main.rs = Main.stmt.executeQuery();

                while(Main.rs.next()){
                    String username = Main.rs.getString("username");
                    String passWord = Main.rs.getString("password");

                    Integer userId = Main.rs.getInt("userId");
                    if(usernameStr.equals(username) && passwordStr.equals(passWord) ){
                        String re="sign in successful！";
                        JOptionPane.showMessageDialog(Login.this,re);
                        user = new User(userId,username, passWord, sdf1.format(Main.rs.getDate("createdTime"))+" "+sdf2.format(Main.rs.getTime("createdTime")));
                        textField.setText("");
                        password.setText("");


                        login.dispose();
                        new BigFunction("Function Menu");
                        return;
                    }

                }

                button1.setBackground(Color.RED);
                password.setText("");
                String re="The Username or password is incorrect, please check again！";
                JOptionPane.showMessageDialog(Login.this,re);

                if(!isCheckUser(usernameStr)){
                    int n = JOptionPane.showConfirmDialog(Login.this, "Haven't register!Do you want to jump to the registration?", "jump to registration", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        new Register("Registration");

                    }
                    else return;
                }

            }catch (Exception E1){
                System.out.println(E1);
            }


        }

    }

    public class LogoutButton implements ActionListener{    //注册监听
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == button2) {
                new Register("Registration");
            }
        }
    }
}
