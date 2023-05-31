package UB.FunctionService.Stock;
import UB.FunctionService.BigFunction;

import UB.Main;

import UB.domain.Stock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StockList extends JFrame {
    private StockList stockList;
    private static DefaultTableModel model = null;
    private static JTable table;
    public static Map<Integer, Stock> stockMap = new HashMap<>();

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public StockList(String title) {
        // 创建内容面板
        stockList = this;
        JPanel panel = new JPanel();
        JPanel panel1=new JPanel();
        JLabel lbl1=new JLabel("Stock",JLabel.CENTER);
        JLabel lbl2=new JLabel("                            ");
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
        String[] columnNames = {"Item ID", "Name", "Type", "Price","Quantity", "Purchase Company","Storage Date"};
        Object[][] rowData;
        // 创建一个表格，指定 表头 和 所有行数据
        defaultStockMap();


        if (stockMap.size() > 0) {
            rowData = new Object[stockMap.size()][5];
            int i = 0;
            for (Map.Entry<Integer, Stock> entry : stockMap.entrySet()) {
                int itemId = entry.getKey();
                String itemName = entry.getValue().getItemName();
                String itemType = entry.getValue().getItemType();
                int itemQuantity = entry.getValue().getItemQuantity();
                double price = entry.getValue().getItemPrice();
                String purchaseCompany = entry.getValue().getCompanyName();
                Date storageDate = entry.getValue().getDate();

                Object[] rowDataItem = {itemId, itemName, itemType, itemQuantity, price,purchaseCompany,storageDate};
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






        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);// 第一列列宽设置为80
        table.getColumnModel().getColumn(2).setPreferredWidth(40);// 第一列列宽设置为80
        table.getColumnModel().getColumn(3).setPreferredWidth(40);// 第一列列宽设置为80
        table.getColumnModel().getColumn(4).setPreferredWidth(40);// 最后一列列宽设置为150
        table.getColumnModel().getColumn(5).setPreferredWidth(80);
        table.getColumnModel().getColumn(6).setPreferredWidth(150);
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


        btn5.addActionListener(new Exit());
        //btn2.addActionListener(new FlushGoodsListButton());
    }



    public class Exit implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            stockList.dispose();
            BigFunction.bigFunction.setVisible(true);

        }
    }


    public static Map<Integer, Stock> defaultStockMap(){
        try{
            stockMap.clear();
            String sql = "select * from Stock";
            Main.stmt = Main.conn.prepareStatement(sql);
            Main.rs = Main.stmt.executeQuery();

            while(Main.rs.next()){
                Integer itemId = Main.rs.getInt("itemId");
                Integer userId = Main.rs.getInt("userId_fk");
                String itemName = Main.rs.getString("itemName");
                String itemType = Main.rs.getString("itemType");
                Integer itemQuantity = Main.rs.getInt("itemQuantity");
                float itemPrice = Main.rs.getFloat("itemPrice");
                String companyName = Main.rs.getString("purchaseCompany");
                Date purchaseDate = Main.rs.getDate("purchaseDate");

                stockMap.put(itemId, new Stock(itemId, userId, itemName,itemType,itemQuantity,itemPrice,companyName, purchaseDate ));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    return stockMap;
    }



}
