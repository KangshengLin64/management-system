package UB.domain;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Stock {
    private int itemId;
    private int userId;
    private String itemName;
    private String itemType;
    private int itemQuantity;
    private double itemPrice;
    private String unit;

    private String companyName;
    private Date date;



    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Stock(int itemId, int userId, String itemName, String itemType, int itemQuantity, double itemPrice, String companyName, Date date) throws ParseException {
        this.itemId = itemId;
        this.userId = userId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
        this.companyName = companyName;
        this.date = sdf.parse(String.valueOf(date));
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemID(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "itemID=" + itemId +
                ", userID='" + userId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemQuantity=" + itemQuantity +
                ", itemPrice=" + itemPrice +
                ", companyName='" + companyName + '\'' +
                ", date=" + sdf.format(date) +
                '}';
    }

}