package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GymManager {
    private List<Member> members;
    private List<Payment> payments;
    private static final String MEMBERS_FILE = "members.dat";
    private static final String PAYMENTS_FILE = "payments.dat";
    
    // SINGLETON INSTANCE
    private static GymManager instance;
    
    // Private constructor for singleton
    private GymManager() {
        members = new ArrayList<>();
        payments = new ArrayList<>();
        loadData();
    }
    
    // Get singleton instance
    public static GymManager getInstance() {
        if (instance == null) {
            instance = new GymManager();
        }
        return instance;
    }
    
    // Rest of your existing methods remain the same...
    // Member Operations
    public boolean addMember(Member member) {
        if (member.getMemberId() == null || member.getMemberId().isEmpty()) {
            member.setMemberId(generateMemberId());
        }
        members.add(member);
        return saveMembers();
    }

    public boolean updateMember(String memberId, Member updatedMember) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getMemberId().equals(memberId)) {
                members.set(i, updatedMember);
                return saveMembers();
            }
        }
        return false;
    }

    public boolean deleteMember(String memberId) {
        boolean removed = members.removeIf(m -> m.getMemberId().equals(memberId));
        if (removed) {
            return saveMembers();
        }
        return false;
    }

    public Member getMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(members);
    }

    public List<Member> searchMembers(String keyword) {
        List<Member> result = new ArrayList<>();
        keyword = keyword.toLowerCase();
        
        for (Member member : members) {
            if (member.getMemberId().toLowerCase().contains(keyword) ||
                member.getName().toLowerCase().contains(keyword) ||
                member.getMobileNumber().contains(keyword) ||
                member.getEmail().toLowerCase().contains(keyword)) {
                result.add(member);
            }
        }
        return result;
    }

    // Payment Operations
    public boolean addPayment(Payment payment) {
        payment.setPaymentId(generatePaymentId());
        payments.add(payment);
        return savePayments();
    }

    public List<Payment> getPaymentsByMember(String memberId) {
        List<Payment> result = new ArrayList<>();
        for (Payment payment : payments) {
            if (payment.getMemberId().equals(memberId)) {
                result.add(payment);
            }
        }
        return result;
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<>(payments);
    }

    // Helper Methods
    private String generateMemberId() {
        return "MEM" + String.format("%04d", members.size() + 1);
    }

    private String generatePaymentId() {
        return "PAY" + String.format("%04d", payments.size() + 1);
    }

    // Data Persistence
    private void loadData() {
        loadMembers();
        loadPayments();
    }

    @SuppressWarnings("unchecked")
    private void loadMembers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MEMBERS_FILE))) {
            members = (List<Member>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File will be created on first save
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    @SuppressWarnings("unchecked")
    private void loadPayments() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PAYMENTS_FILE))) {
            payments = (List<Payment>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File will be created on first save
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    private boolean saveMembers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MEMBERS_FILE))) {
            oos.writeObject(members);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean savePayments() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PAYMENTS_FILE))) {
            oos.writeObject(payments);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    // Reset instance (for testing/logout)
    public static void resetInstance() {
        instance = null;
    }
}