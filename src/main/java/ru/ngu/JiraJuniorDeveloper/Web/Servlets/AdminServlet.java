package ru.ngu.JiraJuniorDeveloper.Web.Servlets;

import ru.ngu.JiraJuniorDeveloper.Model.UserRole;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("verifiedUserName") != null) {
            String username = req.getSession().getAttribute("verifiedUserName").toString();
            if (username != null || (!username.isEmpty())) {
                UserRole role = (UserRole) req.getSession().getAttribute("verifiedUserRole");
                if (role.equals(UserRole.Admin)) {
                    req.getRequestDispatcher("/UsersPages/admin.jsp").forward(req, resp);
                    return;
                }

            }
        }
        resp.sendRedirect("login");
    }


}
