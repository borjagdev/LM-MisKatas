package tdd;

public class User {
    private long id;
    private String name;
    private String profile;

    public User(long id, String name, String profile) {
        this.id = id;
        this.name = name;
        this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }

    public String getName() {
        return name;
    }
}
