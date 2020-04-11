package ru.ngu.JiraJuniorDeveloper.Web.Servlets;

import ru.ngu.JiraJuniorDeveloper.DataBase.StoryDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.Story;
import ru.ngu.JiraJuniorDeveloper.Model.TaskStatus;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Web.PersistenceUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/addStory"})
public class CreateStoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        UserDao users = new UserDao(manager);
        List<User> userList=users.findAllUsers();
        req.setAttribute("users", userList);
        req.getRequestDispatcher("/StoryPages/createStory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        UserDao users = new UserDao(manager);
        StoryDao story=new StoryDao(manager);
        Story createdStory=new Story();
        createdStory.setTitle(req.getParameter("title"));
        createdStory.setStatus(TaskStatus.ToDo);
        createdStory.setReporter(users.findUserByName(req.getSession().getAttribute("verifiedUserName").toString()));
        if(req.getParameter("assignee")!=null){
            createdStory.setAssignee(users.findUserById(Integer.parseInt(req.getParameter("assignee"))));
        }
        createdStory.setStoryCode(req.getParameter("storyCode"));
        try{
        createdStory.setStoryNumber(Integer.parseInt(req.getParameter("storyNumber")));
        }catch (NumberFormatException e){
            createdStory.setStoryNumber(1);
        }
        createdStory.setDescription(req.getParameter("desc"));
        story.createStory(createdStory);
        resp.sendRedirect(req.getContextPath() + "/stories");
    }
}
