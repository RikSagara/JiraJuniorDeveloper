package ru.ngu.JiraJuniorDeveloper.Web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ngu.JiraJuniorDeveloper.DataBase.TaskDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.Task;
import ru.ngu.JiraJuniorDeveloper.Model.TaskStatus;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Web.Forms.ItemForm;


import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
public class CreateTaskConroller {
    @Autowired
    private UserDao users;

    @Autowired
    private TaskDao taskDao;

    @GetMapping(path="/addTask")
    public String getTaskForm(HttpSession session){
        List<User> userList=users.findAllUsers();
        session.setAttribute("taskForm",new ItemForm());
        session.setAttribute("users", userList);
        return "/TaskPages/createTask";
    }

    @PostMapping(path="/addTask")
    public String doPost(@ModelAttribute("taskForm") ItemForm form, HttpSession session){

        Task createdTask=new Task();
        createdTask.setTitle(form.getTitle());
        createdTask.setStatus(TaskStatus.ToDo);
        createdTask.setReporter(users.findUserByName(session.getAttribute("verifiedUserName").toString()));
        if(form.getAssignee()!=null){
            createdTask.setAssignee(users.findUserById(Integer.parseInt(form.getAssignee())));
        }
        createdTask.setTaskCode(form.getItemCode());
        try{
            createdTask.setTaskNumber(form.getItemNumber());
        }catch (NumberFormatException e){
            createdTask.setTaskNumber(1);
        }
        createdTask.setDescription(form.getDescription());
        taskDao.createTask(createdTask);
        return "redirect:/tasks";

    }
}
