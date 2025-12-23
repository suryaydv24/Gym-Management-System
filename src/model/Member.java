package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Member {
    // Your form fields
    private String memberId;
    private String name;
    private String motherName;
    private String fatherName;
    private String mobileNumber;
    private String email;
    private String citizenshipNumber;
    private String gender;
    private int age;
    private double amountPerMonth;
    private LocalTime gymStartTime;
    private LocalTime gymEndTime;
    private LocalDate joinDate;
    private String status; // "Active", "Inactive"
    
    // Constructor
    public Member(String memberId, String name, String motherName, String fatherName,
                  String mobileNumber, String email, String citizenshipNumber,
                  String gender, int age, double amountPerMonth,
                  LocalTime gymStartTime, LocalTime gymEndTime) {
        this.memberId = memberId;
        this.name = name;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.citizenshipNumber = citizenshipNumber;
        this.gender = gender;
        this.age = age;
        this.amountPerMonth = amountPerMonth;
        this.gymStartTime = gymStartTime;
        this.gymEndTime = gymEndTime;
        this.joinDate = LocalDate.now();
        this.status = "Active";
    }
    
    // Getters and Setters (Generate with Alt+Insert)
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getMotherName() { return motherName; }
    public void setMotherName(String motherName) { this.motherName = motherName; }
    
    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }
    
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getCitizenshipNumber() { return citizenshipNumber; }
    public void setCitizenshipNumber(String citizenshipNumber) { this.citizenshipNumber = citizenshipNumber; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public double getAmountPerMonth() { return amountPerMonth; }
    public void setAmountPerMonth(double amountPerMonth) { this.amountPerMonth = amountPerMonth; }
    
    public LocalTime getGymStartTime() { return gymStartTime; }
    public void setGymStartTime(LocalTime gymStartTime) { this.gymStartTime = gymStartTime; }
    
    public LocalTime getGymEndTime() { return gymEndTime; }
    public void setGymEndTime(LocalTime gymEndTime) { this.gymEndTime = gymEndTime; }
    
    public LocalDate getJoinDate() { return joinDate; }
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    // Get gym time as formatted string
    public String getGymTimeFormatted() {
        return gymStartTime.toString() + " to " + gymEndTime.toString();
    }
    
    // Validation methods
    public boolean isValidEmail() {
        return email != null && email.contains("@") && email.contains(".");
    }
    
    public boolean isValidPhone() {
        return mobileNumber != null && mobileNumber.matches("\\d{10}");
    }
    
    public boolean isAdult() {
        return age >= 18;
    }
}