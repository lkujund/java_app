package hr.algebra.model;

/**
 *
 * @author Luka
 */
public class User {
    private int id;
    private String username;
    private String password;
    private boolean adminRole;

    public User() {
    }

    public User(int id, String username, String password, boolean adminRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.adminRole = adminRole;
    }

    public User(String username, String password, boolean adminRole) {
        this.username = username;
        this.password = password;
        this.adminRole = adminRole;
    }

    
    
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdminRole() {
        return adminRole;
    }

    public int getAdminRoleToBit() {
        return adminRole ? 1 : 0;
    }
    
}
