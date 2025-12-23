/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

public class Payment {
 private String paymentId;
    private String memberId;
    private double amount;
    private LocalDate paymentDate;
    private String paymentMethod; // "Cash", "Card", "Online"
    private String status; // "Paid", "Pending", "Overdue"
    
    // Constructor, getters, setters
    public Payment(String paymentId, String memberId, double amount, 
                   String paymentMethod) {
        this.paymentId = paymentId;
        this.memberId = memberId;
        this.amount = amount;
        this.paymentDate = LocalDate.now();
        this.paymentMethod = paymentMethod;
        this.status = "Paid";
    }
    
    // Getters and setters (generate with Alt+Insert)
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }
    
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }   
    
}
