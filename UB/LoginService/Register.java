package UB.LoginService;

import UB.Main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.*;

import static UB.LoginService.Login.isCheckUser;

public class Register extends JFrame {
    private JTextField txtUser;//用户名账号
    private JPasswordField txtPwd1,txtPwd2;//用户名密码，输密码的时候密吗不会显现出来
    private JLabel lblUser,lblPwd1,lblPwd2;
    private JButton Confirm;//注册按钮

    public Register(String title) {
        lblUser = new JLabel("Username");
        lblPwd1 = new JLabel("Password");
        lblPwd2 = new JLabel("Confirm Password");
        lblUser.setBounds(20,20,120,20);//设置用户位置大小长度
        lblPwd1.setBounds(20,50,120,20);
        lblPwd2.setBounds(20,80,120,20);
        setLayout(null);//布局管理器设为空
        txtUser=new JTextField(10);//设置用户栏文本框只显示十个字符的长度
        txtPwd1=new JPasswordField(10);
        txtPwd2=new JPasswordField(10);
        txtUser.setBounds(150,20,100,20);
        txtPwd1.setBounds(150,50,100,20);
        txtPwd2.setBounds(150,80,100,20);

        Confirm=new JButton("Confirm");
        Confirm.setFont(new Font("Times New Roman",Font.BOLD,15));

        Confirm.setBounds(20, 110, 220, 20);
        add(lblUser);
        add(lblPwd1);
        add(lblPwd2);
        add(txtUser);
        add(txtPwd1);
        add(txtPwd2);

        add(Confirm);
        setSize(280,200);
        setLocationRelativeTo(null);
        setTitle(title);
        setVisible(true);


        Confirm.addActionListener(new RegisterButton(this));
    }

    public class ResetButton implements ActionListener{
        //重置
        @Override
        public void actionPerformed(ActionEvent e) {
            txtPwd2.setText("");
        }
    }

    public class RegisterButton implements ActionListener{
        private Register register;
        public RegisterButton(Register register){
            this.register = register;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String usernameStr = txtUser.getText();
            String passwordStr1 = new String(txtPwd1.getPassword());
            String passwordStr2 = new String(txtPwd2.getPassword());
            if(usernameStr.equals("") || passwordStr1.equals("") || passwordStr2.equals("")){
                String re="Username or password cannot be empty!";
                JOptionPane.showMessageDialog(Register.this,re);
            }else{
                if(!passwordStr1.equals(passwordStr2)){
                    String re="passwords do not match!";
                    JOptionPane.showMessageDialog(Register.this,re);
                }else{
                    if(isCheckUser(usernameStr)){
                        String re="Sorry, this user is already registered!";
                        JOptionPane.showMessageDialog(Register.this,re);
                    }else{
                        try{
                            String sql = "INSERT INTO User(username,password,createdTime) VALUES(?,?,?)";
                            Main.stmt = Main.conn.prepareStatement(sql);
                            Main.stmt.setString(1, usernameStr);
                            Main.stmt.setString(2, passwordStr1);
                            //Main.stmt.setBoolean(3, false);
                            Main.stmt.setDate(3, new Date(new java.util.Date().getTime()));
                            Main.stmt.executeUpdate();
                            String re="registration success！";
                            JOptionPane.showMessageDialog(Register.this,re);
                            register.dispose();             //彻底摧毁这个界面
                        }catch (Exception e1){
                            System.out.println(e1);
                        }
                    }
                }
            }
        }
    }
}