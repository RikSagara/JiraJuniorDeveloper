package ru.ngu.JiraJuniorDeveloper.Web.Forms;

import ru.ngu.JiraJuniorDeveloper.Model.User;
import java.util.List;
public class UserListForm {
    private List<User>  users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
