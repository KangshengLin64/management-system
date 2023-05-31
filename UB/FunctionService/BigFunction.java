package UB.FunctionService;


import UB.FunctionService.Client.ClientList;
import UB.FunctionService.Invoice.InvoiceList;
import UB.FunctionService.Stock.StockList;
import UB.LoginService.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;

public class BigFunction extends JFrame{
    private JLabel label, label1;
    private JButton button1, button2, button3, button4;
    private JPanel panel1, panel2;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static BigFunction bigFunction;

    public BigFunction(String title){
        bigFunction = this;
        Font font = new Font("", Font.BOLD, 22);
        label = new JLabel("UB management");
        label.setFont(new Font("", Font.BOLD, 30));
        label1 = new JLabel();
        label1.setFont(font);

        button1 = new JButton("Stock");
        button1.setFont(font);
        button2 = new JButton("Client");
        button2.setFont(font);
        button3 = new JButton("Invoice");
        button3.setFont(font);
        button4 = new JButton("Exit");
        button4.setFont(font);

        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(label);

        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        Box box = Box.createVerticalBox();
        box.setBounds(40, 80, 120, 200);

        box.add(button1);
        box.add(Box.createVerticalStrut(10));
        box.add(button2);
        box.add(Box.createVerticalStrut(10));
        box.add(button3);
        box.add(Box.createVerticalStrut(10));
        box.add(button4);
        box.add(Box.createVerticalStrut(30));
        panel2.add(box);

        setSize(330, 400);
        setLocationRelativeTo(null);
        setTitle(title);
        setVisible(true);
        this.setLayout(new BorderLayout());
        this.add(panel1, BorderLayout.NORTH);
        this.add(new TimePanel(), BorderLayout.CENTER);     //重写JPanel的paint方法，在JPanel上画上实时时bai间的字符串
        this.add(panel2, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//按关闭的操作

        try{
            Timer timer = new Timer();              //多线程实现实时动态时间
            timer.schedule(new ShowTime(), new Date(), 1000);
        }catch (Exception e){
            System.out.println(e);
        }

        button1.addActionListener(new Stockbutton());
        button2.addActionListener(new ClientButton());
        button3.addActionListener(new InvoiceButton());
        button4.addActionListener(new ExitButton());
}

    public class ClientButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            new ClientList("Client");
            bigFunction.setVisible(false);
        }
    }

    public class InvoiceButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            new InvoiceList("Invoice");
            bigFunction.setVisible(false);
        }
    }

    public class Stockbutton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            new StockList("Stocks");
            bigFunction.setVisible(false);
        }
    }

    public class ExitButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new Login("User Login");
            bigFunction.dispose();
            bigFunction.setVisible(false);
        }
    }

    public class ShowTime extends TimerTask {
        public void run() {
            // 刷新
            repaint();
        }
    }
    public class TimePanel extends JPanel {
        public void paint(Graphics g) {
            super.paint(g);
            // 显示时间
            g.setFont(new Font("", Font.BOLD, 14));
            g.drawString(sdf.format(new Date()), 90, 12);
        }
    }
}
