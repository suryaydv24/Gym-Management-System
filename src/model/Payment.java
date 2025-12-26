package model;

import java.time.LocalDate;

public class Payment {
    private String memberId;
    private LocalDate paymentDate;
    private String name;
    private String mobileNumber;
    private String email;
    private double amount;
    private String paymentMethod; // Optional: "Cash", "Card", "Online"
    private String status; // Optional: "Paid", "Pending", "Overdue"
    
    // Default constructor
    public Payment() {
        this.paymentDate = LocalDate.now();
        this.status = "Paid"; // Default status
        this.paymentMethod = "Cash"; // Default payment method
    }
    
    // Constructor with basic fields (matching your form)
    public Payment(String memberId, LocalDate paymentDate, String name, 
                   String mobileNumber, String email, double amount) {
        this.memberId = memberId;
        this.paymentDate = paymentDate;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.amount = amount;
        this.status = "Paid";
        this.paymentMethod = "Cash";
    }
    
    // Full constructor
    public Payment(String memberId, LocalDate paymentDate, String name, 
                   String mobileNumber, String email, double amount,
                   String paymentMethod, String status) {
        this.memberId = memberId;
        this.paymentDate = paymentDate;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }
    
    // Getters and Setters
    public String getMemberId() {
        return memberId;
    }
    
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getMobileNumber() {
        return mobileNumber;
    }
    
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    // Validation method
    public boolean isValid() {
        return memberId != null && !memberId.trim().isEmpty() &&
               name != null && !name.trim().isEmpty() &&
               mobileNumber != null && mobileNumber.matches("\\d{10,15}") &&
               email != null && email.contains("@") &&
               amount > 0;
    }
    
    @Override
    public String toString() {
        return "Payment{" +
               "memberId='" + memberId + '\'' +
               ", paymentDate=" + paymentDate +
               ", name='" + name + '\'' +
               ", mobileNumber='" + mobileNumber + '\'' +
               ", email='" + email + '\'' +
               ", amount=" + amount +
               ", paymentMethod='" + paymentMethod + '\'' +
               ", status='" + status + '\'' +
               '}';
    }

    void setPaymentId(String generatePaymentId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}