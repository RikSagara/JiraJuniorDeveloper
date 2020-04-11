package ru.ngu.JiraJuniorDeveloper.Web.Servlets;

import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;
import ru.ngu.JiraJuniorDeveloper.Web.PersistenceUtils;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/UsersPages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");




        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        UserDao users = new UserDao(manager);

        User foundedUser = null;
        try {
            if (username != null) {
                foundedUser = users.findUserByName(username);
            }
        } finally {
            manager.close();
        }



        if (username != null && password != null && password.equals(foundedUser.getPassword())) {
            req.getSession().setAttribute("verifiedUserName", username);
            req.getSession().setAttribute("verifiedUserRole",foundedUser.getRole());
            if(foundedUser.getRole().equals(UserRole.LoggedUser)) {
                resp.sendRedirect(req.getContextPath());
            }else{
                resp.sendRedirect(req.getContextPath()+"/admin");
            }
        } else {
            resp.sendRedirect("login?login=" + username);
        }
    }


}
