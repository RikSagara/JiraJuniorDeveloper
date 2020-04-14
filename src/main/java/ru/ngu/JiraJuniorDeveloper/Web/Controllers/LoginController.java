package ru.ngu.JiraJuniorDeveloper.Web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserRepository;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserRepository users;


    @GetMapping(path = "/login")
    public String loginPage(@RequestParam(required = false) String login) {
        return "UsersPages/login";
    }

    @PostMapping(path = "/login")
    public String doPost(HttpSession session,
                         @RequestParam("username") String username,
                         @RequestParam("password") String password) {
        if (session.getAttribute("verifiedUserName") != null) {
            return "redirect:/";
        }

        User foundedUser = users.findUserByUserName(username);

        if (username != null && password != null && password.equals(foundedUser.getPassword())) {
            session.setAttribute("verifiedUserName", username);
            session.setAttribute("verifiedUserRole",foundedUser.getRole());
            if(foundedUser.getRole().equals(UserRole.LoggedUser)) {
                return "redirect:/";
            }else{
                return "redirect:/admin";
            }
        } else {
            return "redirect:/login?login=" + username;
        }
    }
}
