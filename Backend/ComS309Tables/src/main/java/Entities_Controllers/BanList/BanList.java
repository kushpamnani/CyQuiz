//package Entities_Controllers.BanList;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//
//@Entity
//public class BanList {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String userName; // The user who owns this ban list
//    private String bannedUsers; // Comma-separated list of usernames who are banned
//
//    public BanList() {
//    }
//
//    public BanList(String userName, String bannedUsers) {
//        this.userName = userName;
//        this.bannedUsers = bannedUsers;
//    }
//
//    // Getters and setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getBannedUsers() {
//        return bannedUsers;
//    }
//
//    public void setBannedUsers(String bannedUsers) {
//        this.bannedUsers = bannedUsers;
//    }
//
//    // Helper methods (if necessary)
//    // For example, to add or remove a user from the banned list
//    public void addBannedUser(String user) {
//        if (this.bannedUsers.isEmpty()) {
//            this.bannedUsers = user;
//        } else {
//            this.bannedUsers += "," + user;
//        }
//    }
//
//    public void removeBannedUser(String user) {
//        // This would require parsing the bannedUsers string and removing the user,
//        // then reconstructing the string without the removed user.
//        // Implementation will depend on the specific requirements and usage context.
//    }
//
//}
