package ru.ngu.JiraJuniorDeveloper.Web.Servlets;

import ru.ngu.JiraJuniorDeveloper.DataBase.StoryDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.Story;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Web.PersistenceUtils;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/stories"})
public class StoriesListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        StoryDao storyDao = new StoryDao(manager);
        List<Story> stories=storyDao.findStoriesByUser(req.getSession().getAttribute("verifiedUserName").toString());
        req.setAttribute("stories", stories);
        req.getRequestDispatcher("/StoryPages/storiesList.jsp").forward(req, resp);
    }
}
