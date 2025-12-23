package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class GymManager {
    private List<Member> members;
    private Queue<Member> recentMembers;
    
    public GymManager() {
        this.members = new ArrayList<>();
        this.recentMembers = new LinkedList<>();
        initializeSampleData();
    }
    
    private void initializeSampleData() {
        // Add sample members with your fields
        addMember(new Member("M001", "John Doe", "Mary Doe", "Robert Doe",
                "1234567890", "john@email.com", "CTZ001",
                "Male", 25, 5000.0,
                LocalTime.of(5, 0), LocalTime.of(11, 0)));
        
        addMember(new Member("M002", "Jane Smith", "Anna Smith", "David Smith",
                "0987654321", "jane@email.com", "CTZ002",
                "Female", 22, 4500.0,
                LocalTime.of(6, 0), LocalTime.of(12, 0)));
    }
    
    // Generate automatic member ID (M001, M002, etc.)
    public String generateMemberId() {
        int nextNumber = members.size() + 1;
        return String.format("M%03d", nextNumber);
    }
    
    // CREATE - Add new member with validation
    public boolean addMember(Member member) {
        // Check duplicate member ID
        for (Member m : members) {
            if (m.getMemberId().equals(member.getMemberId())) {
                return false;
            }
            // Check duplicate email
            if (m.getEmail().equalsIgnoreCase(member.getEmail())) {
                return false;
            }
            // Check duplicate citizenship number
            if (m.getCitizenshipNumber().equals(member.getCitizenshipNumber())) {
                return false;
            }
        }
        
        members.add(member);
        
        // Add to recent queue
        recentMembers.add(member);
        if (recentMembers.size() > 5) {
            recentMembers.poll();
        }
        
        return true;
    }
    
    // READ - Get all members
    public List<Member> getAllMembers() {
        return new ArrayList<>(members);
    }
    
    // READ - Get member by ID
    public Member getMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }
    
    // READ - Get member by email
    public Member getMemberByEmail(String email) {
        for (Member member : members) {
            if (member.getEmail().equalsIgnoreCase(email)) {
                return member;
            }
        }
        return null;
    }
    
    // UPDATE - Update member
    public boolean updateMember(Member updatedMember) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getMemberId().equals(updatedMember.getMemberId())) {
                members.set(i, updatedMember);
                return true;
            }
        }
        return false;
    }
    
    // DELETE - Remove member
    public boolean deleteMember(String memberId) {
        return members.removeIf(member -> member.getMemberId().equals(memberId));
    }
    
    // SEARCH - Search by name (linear search)
    public List<Member> searchByName(String name) {
        List<Member> results = new ArrayList<>();
        for (Member member : members) {
            if (member.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(member);
            }
        }
        return results;
    }
    
    // SORT - Sort by name (alphabetical)
    public List<Member> sortByName() {
        List<Member> sorted = new ArrayList<>(members);
        sorted.sort((m1, m2) -> m1.getName().compareToIgnoreCase(m2.getName()));
        return sorted;
    }
    
    // Filter by gender
    public List<Member> filterByGender(String gender) {
        List<Member> results = new ArrayList<>();
        for (Member member : members) {
            if (member.getGender().equalsIgnoreCase(gender)) {
                results.add(member);
            }
        }
        return results;
    }
    
    // Statistics
    public int getTotalMembers() {
        return members.size();
    }
    
    public int getMaleCount() {
        return filterByGender("Male").size();
    }
    
    public int getFemaleCount() {
        return filterByGender("Female").size();
    }
    
    public double getMonthlyRevenue() {
        double total = 0;
        for (Member member : members) {
            if (member.getStatus().equals("Active")) {
                total += member.getAmountPerMonth();
            }
        }
        return total;
    }
    
    // Get recent members (Queue)
    public Queue<Member> getRecentMembers() {
        return new LinkedList<>(recentMembers);
    }
}