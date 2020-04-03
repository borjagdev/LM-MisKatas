package tdd;

import java.util.List;

public class UserReport {
    private String targetProfile;
    private List<User> users;

    public UserReport(String targetProfile, List<User> users) {
        this.targetProfile = targetProfile;
        this.users = users;
    }
}
