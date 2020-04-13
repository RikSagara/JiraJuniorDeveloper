package ru.ngu.JiraJuniorDeveloper.Web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;
import ru.ngu.JiraJuniorDeveloper.Web.Forms.RegistrationForm;

@Controller
public class RegistrationController {
    @Autowired
    private UserDao user;

    @GetMapping(path = "/user/register")
    public String registrationPage(ModelMap model) {
        RegistrationForm form = new RegistrationForm();
        form.setName("");
        form.setPassword("");

        model.addAttribute("form", form);
        return "UsersPages/registerUser";
    }

    @PostMapping(path = "/user/register")
    public String processRegistrationForm(@RequestParam String name,
                                          @RequestParam String password) {

        user.createUser(name, password, UserRole.LoggedUser);

        return "redirect:/login?login=" + name;
    }
}
