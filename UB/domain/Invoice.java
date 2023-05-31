package UB.domain;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Invoice {
    private int invoiceID;
    private int clientID;
    private int itemID;
    private String clientName;
    private double amount;
    private Boolean paid;
    private String paymentMethod;
    private Date dueDate;
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public Invoice(int invoiceID, int clientID, int itemID, double amount, Boolean paid, String paymentMethod, Date dueDate) throws ParseException {
        this.invoiceID = invoiceID;
        this.clientID = clientID;
        this.itemID = itemID;
        this.clientName = clientName;
        this.amount = amount;
        this.paid = paid;
        this.paymentMethod = paymentMethod;

        this.dueDate = sdf.parse(String.valueOf(dueDate));
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String paidStr = (paid) ? "Paid" : "Unpaid";
        return String.format("Invoice ID: %d, Client ID: %d, Item ID: %d, Client Name: %s, Amount: %.2f, Status: %s, Payment Method: %s, Due Date: %s",
                invoiceID, clientID, itemID, clientName, amount, paidStr, paymentMethod, dateFormat.format(dueDate));
    }




}