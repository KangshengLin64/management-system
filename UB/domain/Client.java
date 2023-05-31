package UB.domain;



public class Client {
    private int clientId;
    private int userId;
    private String clientName;
    private String clientAddress;
    private String clientPhoneNumber;
    private double clientDebtTotal;


    // Constructor
    public Client(int clientId,int userId, String clientName, String clientAddress, String clientPhoneNumber, double clientDebtTotal) {
        this.clientId = clientId;
        this.userId = userId;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.clientPhoneNumber = clientPhoneNumber;
        this.clientDebtTotal = clientDebtTotal;
    }

    // Getters and Setters
    public int getclientID() {
        return clientId;
    }

    public void setclientID(int clientID) {
        this.clientId = clientID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setclientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public double getClientDebtTotal() {
        return clientDebtTotal;
    }

    public void setClientDebtTotal(double clientDebtTotal) {
        this.clientDebtTotal = clientDebtTotal;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", userId=" + userId +
                ", clientName='" + clientName + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                ", clientPhoneNumber='" + clientPhoneNumber + '\'' +
                ", clientDebtTotal=" + clientDebtTotal +
                '}';
    }




}