package ru.ngu.JiraJuniorDeveloper.Web.Forms;

import ru.ngu.JiraJuniorDeveloper.Model.User;
import java.util.List;
import java.util.Objects;

public class UserListForm {
    private List<User>  users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserListForm that = (UserListForm) o;
        return Objects.equals(getUsers(), that.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsers());
    }
}
