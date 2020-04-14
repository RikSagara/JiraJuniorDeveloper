package ru.ngu.JiraJuniorDeveloper.Web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserRepository;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;
import ru.ngu.JiraJuniorDeveloper.Web.Forms.RegistrationForm;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository user;

    @ModelAttribute("form")
    public RegistrationForm getRegistrationForm() {
        RegistrationForm form = new RegistrationForm();
        form.setUserName("");
        form.setPassword("");
        return form;
    }

    @GetMapping(path = "/user/register")
    public String registrationPage(ModelMap model,
                                   @ModelAttribute("form") RegistrationForm form) {

        return "UsersPages/registerUser";
    }

    @PostMapping(path = "/user/register")
    public String processRegistrationForm ( ModelMap model,
                                            @Validated
                                            @ModelAttribute("form") RegistrationForm form,
                                            BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            return "UsersPages/registerUser";
        }
        try {
            User createdUser=new User();
            createdUser.setUserName(form.getUserName());
            createdUser.setPassword(form.getPassword());
            createdUser.setRole(UserRole.LoggedUser);
             user.save(createdUser);
        } catch (Throwable cause) {
            validationResult.addError(
                    new FieldError("form", "userName",
                            "User with name " + form.getUserName()
                                    + " is already registered."));

            return "UsersPages/registerUser";
        }


        return "redirect:/login?login=" + form.getUserName();
    }
}
