package ru.ngu.JiraJuniorDeveloper.Web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserRepository;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;
import ru.ngu.JiraJuniorDeveloper.Web.Forms.RegistrationForm;
import ru.ngu.JiraJuniorDeveloper.Web.Forms.UserForm;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserRepository users;

    @ModelAttribute("form")
    public UserForm UserForm(){
        UserForm form=new UserForm();
        return form;
    }

    @GetMapping(path="/user")
    public String ShowEditUserForm(@RequestParam int id, ModelMap model,
                                   @ModelAttribute("form") UserForm userForm){
        User foundedUser = users.findUserById(id);
        if(foundedUser!=null) {
            userForm.setId(foundedUser.getId());
            userForm.setUserName(foundedUser.getUserName());
            userForm.setPassword(foundedUser.getPassword());
            userForm.setRole(foundedUser.getRole());
        }

        return  "/UsersPages/editUser";
    }

    @PostMapping(path="/user")
    public String doPost(ModelMap model,
                         @Validated
                         @ModelAttribute("form") UserForm form,
                         BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            return "UsersPages/editUser";
        }
        User editedUser=new User();
        editedUser.setId(form.getId());
        editedUser.setRole(form.getRole());
        editedUser.setPassword(form.getPassword());
        editedUser.setUserName(form.getUserName());
        users.save(editedUser);
        return "redirect:/users";
    }
}
