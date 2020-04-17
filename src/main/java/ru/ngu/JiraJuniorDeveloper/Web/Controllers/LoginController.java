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

    public static final String VERIFIED_USER_NAME = "verifiedUserName";
    public static final String VERIFIED_USER_ROLE = "verifiedUserRole";

    @GetMapping(path = "/login")
    public String loginPage(@RequestParam(required = false) String login) {
        return "UsersPages/login";
    }

    @PostMapping(path = "/login")
    public String doPost(HttpSession session,
                         @RequestParam("username") String username,
                         @RequestParam("password") String password) {

        if (session.getAttribute(VERIFIED_USER_NAME) != null) {
            return "redirect:/";
        }

        User foundedUser = users.findUserByUserName(username);


        if ((username != null&&(!username.isEmpty())) &&
                (password != null &&(!password.isEmpty()))) {

            if (foundedUser==null||(!password.equals(foundedUser.getPassword()))) {
                return "redirect:/login?login=" + username;
            } else {
                session.setAttribute(VERIFIED_USER_NAME, username);
                session.setAttribute(VERIFIED_USER_ROLE, foundedUser.getRole());
                if (foundedUser.getRole().equals(UserRole.LoggedUser)) {
                    return "redirect:/";
                } else {
                    return "redirect:/admin";
                }
            }
        }
        return "redirect:/login";
    }
}
