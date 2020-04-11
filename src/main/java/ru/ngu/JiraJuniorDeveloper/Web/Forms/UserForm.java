package ru.ngu.JiraJuniorDeveloper.Web.Forms;

import ru.ngu.JiraJuniorDeveloper.Model.UserRole;

public class UserForm extends RegistrationForm{
    private UserRole role;
    private int id;
    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
