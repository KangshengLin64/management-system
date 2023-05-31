package UB.FunctionService.Invoice;
import UB.FunctionService.BigFunction;
import UB.Main;
import UB.domain.Invoice;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InvoiceList extends JFrame{
    private InvoiceList invoicelist;
    private static DefaultTableModel model = null;
    private static JTable table;

    public static Map<Integer, Invoice> invoiceMap = new HashMap<>();
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public InvoiceList(String title) {
        // 创建内容面板
        invoicelist = this;
        JPanel panel = new JPanel();
        JPanel panel1=new JPanel();
        JLabel lbl1=new JLabel("Invoice",JLabel.CENTER);
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
        String[] columnNames = {"Invoice ID","Client ID", "Amount", "Paid", "Payment Method","Due Date"};
        Object[][] rowData;
        // 创建一个表格，指定 表头 和 所有行数据

        defaultInvoiceMap();

        if (invoiceMap.size() > 0) {
            rowData = new Object[invoiceMap.size()][6];
            int i = 0;
            for (Map.Entry<Integer, Invoice> entry : invoiceMap.entrySet()) {
                int invoiceId = entry.getKey();
                int clientId = entry.getValue().getClientID();
                //String invoiceName=entry.getValue().getClientName();
                double invoiceAmount = entry.getValue().getAmount();
                Boolean invoicePaid = entry.getValue().getPaid();
                String invoicePamentMethod = entry.getValue().getPaymentMethod();
                Date invoiceDueDate =entry.getValue().getDueDate();

                Object[] rowDataItem = {invoiceId, clientId, invoiceAmount, invoicePaid,invoicePamentMethod,invoiceDueDate};
                rowData[i] = rowDataItem;
                i++;
            }
        } else {
            rowData = new Object[0][6];
        }




        model = new DefaultTableModel( rowData, columnNames);
        table = new JTable(model);
        table.setRowHeight(30);




        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        //table.getColumnModel().getColumn(6).setPreferredWidth(150);
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));

        table.setSelectionForeground(Color.DARK_GRAY);// 选中后字体颜色


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


        btn5.addActionListener(new InvoiceList.Exit());

    }



    public class Exit implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            invoicelist.dispose();
            BigFunction.bigFunction.setVisible(true);

        }
    }

    public static Map<Integer, Invoice> defaultInvoiceMap(){
        try{
            invoiceMap.clear();
            String sql = "select * from Invoice";
            Main.stmt = Main.conn.prepareStatement(sql);
            Main.rs = Main.stmt.executeQuery();

            while(Main.rs.next()){
                int invoiceID = Main.rs.getInt("invoiceID");
                int clientID = Main.rs.getInt("clientId_fk");
                int itemID = Main.rs.getInt("itemId_fk");
                float amount = Main.rs.getFloat("amount");
                Boolean paid = Main.rs.getBoolean("paid");
                String paymentMethod = Main.rs.getString("paymentMethod");
                Date dueDate = Main.rs.getDate("dueDate");


                invoiceMap.put(invoiceID, new Invoice(invoiceID, clientID, itemID,amount,paid,paymentMethod,dueDate));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return invoiceMap;
    }



}






