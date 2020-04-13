package ru.ngu.JiraJuniorDeveloper.Web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @GetMapping(path="/admin")
    public String GetAdminPage(HttpSession sesson){
        if (sesson.getAttribute("verifiedUserName") != null) {
            String username = sesson.getAttribute("verifiedUserName").toString();
            if (username != null || (!username.isEmpty())) {
                UserRole role = (UserRole) sesson.getAttribute("verifiedUserRole");
                if (role.equals(UserRole.Admin)) {
                    return "/UsersPages/admin";
                }
            }
        }
        return "redirect:/login";
    }
}
