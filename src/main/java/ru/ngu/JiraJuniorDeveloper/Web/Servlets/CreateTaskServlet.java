package ru.ngu.JiraJuniorDeveloper.Web.Servlets;

import ru.ngu.JiraJuniorDeveloper.DataBase.StoryDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.TaskDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.Story;
import ru.ngu.JiraJuniorDeveloper.Model.Task;
import ru.ngu.JiraJuniorDeveloper.Model.TaskStatus;
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

@WebServlet(urlPatterns = {"/addTask"})
public class CreateTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        UserDao users = new UserDao(manager);
        List<User> userList=users.findAllUsers();
        req.setAttribute("users", userList);
        req.getRequestDispatcher("/TaskPages/createTask.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager manager = PersistenceUtils.createManager(req.getServletContext());
        UserDao users = new UserDao(manager);
        TaskDao story=new TaskDao(manager);
        Task createdTask=new Task();
        createdTask.setTitle(req.getParameter("title"));
        createdTask.setStatus(TaskStatus.ToDo);
        createdTask.setReporter(users.findUserByName(req.getSession().getAttribute("verifiedUserName").toString()));
        if(req.getParameter("assignee")!=null){
            createdTask.setAssignee(users.findUserById(Integer.parseInt(req.getParameter("assignee"))));
        }
        createdTask.setTaskCode(req.getParameter("taskCode"));
        try{
            createdTask.setTaskNumber(Integer.parseInt(req.getParameter("taskNumber")));
        }catch (NumberFormatException e){
            createdTask.setTaskNumber(1);
        }
        createdTask.setDescription(req.getParameter("desc"));
        story.createTask(createdTask);
        resp.sendRedirect(req.getContextPath() + "/tasks");
    }
}
