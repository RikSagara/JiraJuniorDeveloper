package ru.ngu.JiraJuniorDeveloper.Web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;
import ru.ngu.JiraJuniorDeveloper.Web.Forms.UserForm;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserDao users;

    @GetMapping(path="/user")
    public String ShowEditUserForm(@RequestParam int id, HttpSession session){
        User foundedUser = users.findUserById(id);
        UserForm userForm=new UserForm();
        if(foundedUser!=null) {
            userForm.setId(foundedUser.getId());
            userForm.setName(foundedUser.getName());
            userForm.setPassword(foundedUser.getPassword());
            userForm.setRole(foundedUser.getRole());
        }
        session.setAttribute("form", userForm);
        return  "/UsersPages/editUser";
    }

    @PostMapping(path="/user")
    public String doPost(@RequestParam String name,
                         @RequestParam String password,
                         @RequestParam UserRole role,
                         @RequestParam int userId) {
        users.editUser(userId,name, password, role);
        return "redirect:/users";
    }
}
