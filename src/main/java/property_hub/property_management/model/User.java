package property_hub.property_management.model;

import jakarta.persistence.*;

@Entity
@Table(name="user_data")
public class User {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private Integer id;  // Primary key field

    @Column(name="Name")
    private String username;

    @Column(name="Email")
    private String email;

    @Column(name="Password")
    private String password;

    @Column(name="Role")
    private String role;  // Role field to distinguish between Buyer and Seller

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
