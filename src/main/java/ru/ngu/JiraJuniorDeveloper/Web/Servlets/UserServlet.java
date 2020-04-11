package ru.ngu.JiraJuniorDeveloper.Web.Servlets;

import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;
import ru.ngu.JiraJuniorDeveloper.Web.Forms.UserForm;
import ru.ngu.JiraJuniorDeveloper.Web.PersistenceUtils;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id")!=null){
            String userId=req.getParameter("id");
            EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
            UserDao users = new UserDao(manager);

            User foundedUser = null;
            try {
                foundedUser = users.findUserById(Integer.parseInt(userId));
            } finally {
                manager.close();
            }
            UserForm userForm=new UserForm();
            if(foundedUser!=null) {
                userForm.setId(foundedUser.getId());
                userForm.setName(foundedUser.getName());
                userForm.setPassword(foundedUser.getPassword());
                userForm.setRole(foundedUser.getRole());
            }
            req.setAttribute("form", userForm);
            req.getRequestDispatcher("/UsersPages/editUser.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("name");
        String password = req.getParameter("password");
        UserRole role=UserRole.valueOf(req.getParameter("role"));
        int id=Integer.parseInt(req.getParameter("id"));
        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        UserDao users = new UserDao(manager);
        try {
            users.editUser(id,login, password, role);
        } finally {
            manager.close();
        }

        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
