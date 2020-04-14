package ru.ngu.JiraJuniorDeveloper.Web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ngu.JiraJuniorDeveloper.DataBase.TaskDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.TaskRepository;
import ru.ngu.JiraJuniorDeveloper.Model.Task;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TaskListController {
    @Autowired
    private TaskRepository taskDao;

    @GetMapping(path = "/tasks")
    public String GetTaskList(HttpSession session){
        List<Task> tasks=taskDao.findTasksByUser(session.getAttribute("verifiedUserName").toString());
        session.setAttribute("tasks", tasks);
        return "/TaskPages/tasksList";
    }
}
