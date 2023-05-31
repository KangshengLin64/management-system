package UB.FunctionService.Client;
import UB.FunctionService.BigFunction;


import UB.Main;
import UB.domain.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ClientList extends JFrame{
    private ClientList clientList;
    private static DefaultTableModel model = null;
    private static JTable table;
    public static Map<Integer, Client> clientMap = new HashMap<Integer, Client>();

    public ClientList(String title) {
        // 创建内容面板
        clientList = this;
        JPanel panel = new JPanel();
        JPanel panel1=new JPanel();
        JLabel lbl1=new JLabel("Client",JLabel.CENTER);
        JLabel lbl2=new JLabel(" Sort By ",JLabel.LEFT);
        lbl1.setFont(new Font(null, Font.BOLD, 20));
        JButton btn1=new JButton("New");
        JButton btn2=new JButton("Edit");
        JButton btn3=new JButton("Detail");
        JButton btn4=new JButton("Sort");
        JButton btn5=new JButton("Return");
        btn1.setFont(new Font(null, Font.BOLD, 20));
        btn2.setFont(new Font(null, Font.BOLD, 20));
        btn3.setFont(new Font(null, Font.BOLD, 20));
        btn4.setFont(new Font(null, Font.BOLD, 20));
        btn5.setFont(new Font(null, Font.BOLD, 20));
        btn1.setFont(new Font(null, Font.BOLD, 20));
        btn2.setFont(new Font(null, Font.BOLD, 20));
        String[] columnNames = {"Client ID", "Name", "Address", "Phone Number", "Debt Total"};
        Object[][] rowData;

        // 创建一个表格，指定 表头 和 所有行数据
        // table = new JTable(rowData, columnNames);
        defaultClientMap();



        //model = new DefaultTableModel( rowData, columnNames);



        if (clientMap.size() > 0) {
            rowData = new Object[clientMap.size()][5];
            int i = 0;
            for (Map.Entry<Integer, Client> entry : clientMap.entrySet()) {
                int clientId = entry.getKey();
                String clientName = entry.getValue().getClientName();
                String clientAddress = entry.getValue().getClientAddress();
                String clientPhoneNumber = entry.getValue().getClientPhoneNumber();
                double clientDebtTotal = entry.getValue().getClientDebtTotal();

                Object[] rowDataItem = {clientId, clientName, clientAddress, clientPhoneNumber, clientDebtTotal};
                rowData[i] = rowDataItem;
                i++;
            }
        } else {
            rowData = new Object[0][5];
        }

// Update the table to reflect the changes in the model
        model = new DefaultTableModel( rowData, columnNames);
        table = new JTable(model);
        table.setRowHeight(30);








        table.getColumnModel().getColumn(0).setPreferredWidth(40);// 第一列列宽设置为80
        table.getColumnModel().getColumn(1).setPreferredWidth(40);// 第一列列宽设置为80
        table.getColumnModel().getColumn(2).setPreferredWidth(80);// 第一列列宽设置为80
        table.getColumnModel().getColumn(3).setPreferredWidth(80);// 第一列列宽设置为80
        table.getColumnModel().getColumn(4).setPreferredWidth(50);// 最后一列列宽设置为150
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));// 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）*/

        table.setSelectionForeground(Color.DARK_GRAY);// 选中后字体颜色

// 设置 内容面板 到 窗口
        this.setLayout(new BorderLayout());
        this.add(lbl1,BorderLayout.NORTH);

        this.add(new JScrollPane(table),BorderLayout.CENTER);
        this.pack();
        this.setTitle(title);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        panel1.add(btn1);
        panel1.add(btn2);
        panel1.add(btn3);
        panel1.add(btn4);
        panel1.add(btn5);
        this.add(panel1,BorderLayout.SOUTH);


        btn5.addActionListener(new ClientList.Exit());

    

    }



    public class Exit implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            clientList.dispose();
            BigFunction.bigFunction.setVisible(true);

        }
    }

    public static Map<Integer, Client> defaultClientMap(){
        try{
            clientMap.clear();
            String sql = "select * from Client";
            Main.stmt = Main.conn.prepareStatement(sql);
            Main.rs = Main.stmt.executeQuery();

            while(Main.rs.next()){
                int clientId = Main.rs.getInt("clientId");
                int userId = Main.rs.getInt("userId_fk");
                String clientName = Main.rs.getString("clientName");
                String clientAddress = Main.rs.getString("clientAddress");
                String clientPhoneNumber = Main.rs.getString("phoneNumber");
                double clientDebtTotal = Main.rs.getFloat("debtTotal");


                clientMap.put(clientId, new Client(clientId, userId, clientName,clientAddress,clientPhoneNumber,clientDebtTotal ));


            }



        }catch (Exception e){
            System.out.println(e);
        }
    return clientMap;
    }



}

