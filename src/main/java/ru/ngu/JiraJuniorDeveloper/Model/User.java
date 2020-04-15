package ru.ngu.JiraJuniorDeveloper.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false,unique = true)
    private String userName;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private UserRole role;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getUserName() { return userName; }

    public void setUserName(String name) { this.userName = name; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

}
