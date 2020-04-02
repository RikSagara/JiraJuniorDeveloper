package ru.ngu.JiraJuniorDeveloper.Model;

public enum UserRole {
    Admin(1),
    LoggedUser(2);

    private int role;

    UserRole(int roleToSet){
        role=roleToSet;
    }

    public int getRole() {
        return role;
    }
}
