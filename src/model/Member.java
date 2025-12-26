package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.io.Serializable;  // ADD THIS for serialization

public class Member implements Serializable {  // ADD "implements Serializable"
    private String memberId;
    private String name;
    private String mobileNumber;
    private String email;
    private String gender;
    private String fatherName;
    private String motherName;
    private String gymTime;
    private String citizenshipNumber;
    private int age;
    private double amount;
    private LocalDate joinDate;
    private String status;
    
    // ============ ADD THIS DEFAULT CONSTRUCTOR ============
    public Member() {
        // Default constructor for serialization
        this.joinDate = LocalDate.now();
        this.status = "Active";
    }
    
    // Your existing constructors...
    public Member(String memberId, String name, String motherName, String fatherName,
                  String mobileNumber, String email, String citizenshipNumber,
                  String gender, int age, double amount,
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
        this.amount = amount;
        this.gymTime = formatTime(gymStartTime) + " to " + formatTime(gymEndTime);
        this.joinDate = LocalDate.now();
        this.status = "Active";
    }
    
    // ============ STEP 5: ADD ALL GETTERS AND SETTERS ============
    
    // Getter for memberId
    public String getMemberId() { 
        return memberId; 
    }
    
    // Setter for memberId (ADD THIS)
    public void setMemberId(String memberId) { 
        this.memberId = memberId; 
    }
    
    // Getter for name
    public String getName() { 
        return name; 
    }
    
    // Setter for name (ADD THIS)
    public void setName(String name) { 
        this.name = name; 
    }
    
    // Getter for mobileNumber
    public String getMobileNumber() { 
        return mobileNumber; 
    }
    
    // Setter for mobileNumber (ADD THIS)
    public void setMobileNumber(String mobileNumber) { 
        this.mobileNumber = mobileNumber; 
    }
    
    // Getter for email
    public String getEmail() { 
        return email; 
    }
    
    // Setter for email (ADD THIS)
    public void setEmail(String email) { 
        this.email = email; 
    }
    
    // Getter for gender
    public String getGender() { 
        return gender; 
    }
    
    // Setter for gender (ADD THIS)
    public void setGender(String gender) { 
        this.gender = gender; 
    }
    
    // Getter for fatherName
    public String getFatherName() { 
        return fatherName; 
    }
    
    // Setter for fatherName (ADD THIS)
    public void setFatherName(String fatherName) { 
        this.fatherName = fatherName; 
    }
    
    // Getter for motherName
    public String getMotherName() { 
        return motherName; 
    }
    
    // Setter for motherName (ADD THIS)
    public void setMotherName(String motherName) { 
        this.motherName = motherName; 
    }
    
    // Getter for gymTime
    public String getGymTime() { 
        return gymTime; 
    }
    
    // Setter for gymTime (ADD THIS)
    public void setGymTime(String gymTime) { 
        this.gymTime = gymTime;
        parseGymTimeString(gymTime);
    }
    
    // Getter for citizenshipNumber
    public String getCitizenshipNumber() { 
        return citizenshipNumber; 
    }
    
    // Setter for citizenshipNumber (ADD THIS)
    public void setCitizenshipNumber(String citizenshipNumber) { 
        this.citizenshipNumber = citizenshipNumber; 
    }
    
    // Getter for age
    public int getAge() { 
        return age; 
    }
    
    // Setter for age (ADD THIS)
    public void setAge(int age) { 
        this.age = age; 
    }
    
    // Getter for amount
    public double getAmount() { 
        return amount; 
    }
    
    // Setter for amount (ADD THIS)
    public void setAmount(double amount) { 
        this.amount = amount; 
    }
    
    // Getter for joinDate
    public LocalDate getJoinDate() { 
        return joinDate; 
    }
    
    // Setter for joinDate (ADD THIS)
    public void setJoinDate(LocalDate joinDate) { 
        this.joinDate = joinDate; 
    }
    
    // Getter for status
    public String getStatus() { 
        return status; 
    }
    
    // Setter for status (ADD THIS)
    public void setStatus(String status) { 
        this.status = status; 
    }
    
    // ============ KEEP YOUR EXISTING METHODS BELOW ============
    
    // Your existing methods (formatTime, parseTime, toString, etc.)
    public static String formatTime(LocalTime time) {
        if (time == null) {
            return "05:00 AM";
        }
        int hour = time.getHour();
        int minute = time.getMinute();
        
        String amPm = "AM";
        if (hour >= 12) {
            amPm = "PM";
            if (hour > 12) {
                hour -= 12;
            }
        }
        if (hour == 0) {
            hour = 12;
        }
        
        return String.format("%02d:%02d %s", hour, minute, amPm);
    }
    
    public static LocalTime parseTime(String timeString) {
        try {
            String time = timeString.trim().toUpperCase();
            time = time.replaceAll("\\s+", " ");
            
            if (time.contains("AM") || time.contains("PM")) {
                java.time.format.DateTimeFormatter formatter = 
                    java.time.format.DateTimeFormatter.ofPattern("hh:mm a");
                return LocalTime.parse(time, formatter);
            } else {
                return LocalTime.parse(time);
            }
        } catch (Exception e) {
            System.err.println("Error parsing time: " + timeString + " - " + e.getMessage());
            return LocalTime.of(5, 0);
        }
    }
    
    private void parseGymTimeString(String gymTimeStr) {
        try {
            String[] parts = gymTimeStr.split(" to ");
            if (parts.length == 2) {
                // We parse but don't store LocalTime objects since we're using String
                // This method is kept for compatibility
            }
        } catch (Exception e) {
            System.err.println("Error parsing gym time: " + gymTimeStr);
        }
    }
    
    public String getAmountFormatted() {
        return String.format("Rs. %.2f", amount);
    }
    
    public String getAgeString() {
        return String.valueOf(age);
    }
    
    public Object[] getTableRow() {
        return new Object[] {
            memberId,
            name,
            mobileNumber,
            email,
            gender,
            fatherName,
            motherName,
            gymTime,
            citizenshipNumber,
            String.valueOf(age),
            getAmountFormatted()
        };
    }
    
    @Override
    public String toString() {
        return String.format(
            "Member[ID=%s, Name=%s, Mobile=%s, Email=%s, Gender=%s, Age=%d, Amount=Rs.%.2f, GymTime=%s]",
            memberId, name, mobileNumber, email, gender, age, amount, gymTime
        );
    }
}