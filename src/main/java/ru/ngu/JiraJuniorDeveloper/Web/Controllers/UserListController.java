package ru.ngu.JiraJuniorDeveloper.Web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.User;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserListController {
    @Autowired
    private UserDao users;
    @GetMapping(path = "/users")
    public String registrationPage(HttpSession session) {
        List<User> userList = users.findAllUsers();
        session.setAttribute("users", userList);
        return "/UsersPages/usersList";
    }
}
