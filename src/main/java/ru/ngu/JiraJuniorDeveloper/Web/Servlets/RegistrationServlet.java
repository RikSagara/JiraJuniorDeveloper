package ru.ngu.JiraJuniorDeveloper.Web.Servlets;

import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;
import ru.ngu.JiraJuniorDeveloper.Web.Forms.RegistrationForm;
import ru.ngu.JiraJuniorDeveloper.Web.PersistenceUtils;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/user/register")
public class RegistrationServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationForm form = new RegistrationForm();
        form.setName("");
        form.setPassword("");

        req.setAttribute("form", form);
        req.getRequestDispatcher("/UsersPages/registerUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("name");
        String password = req.getParameter("password");
        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        UserDao users = new UserDao(manager);
        try {
            users.createUser(login, password, UserRole.LoggedUser);
        } finally {
            manager.close();
        }

        resp.sendRedirect(req.getContextPath() + "/login?login=" + login);
    }
}