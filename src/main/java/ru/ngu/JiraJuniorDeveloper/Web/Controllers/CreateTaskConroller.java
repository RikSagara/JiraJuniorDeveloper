package ru.ngu.JiraJuniorDeveloper.Web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ngu.JiraJuniorDeveloper.DataBase.TaskDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.TaskRepository;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserRepository;
import ru.ngu.JiraJuniorDeveloper.Model.Task;
import ru.ngu.JiraJuniorDeveloper.Model.TaskStatus;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Web.Forms.ItemForm;
import ru.ngu.JiraJuniorDeveloper.Web.Forms.UserListForm;


import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
public class CreateTaskConroller {
    @Autowired
    private UserRepository users;

    @Autowired
    private TaskRepository taskDao;

    @ModelAttribute("form")
    public ItemForm getTaskForm() {
        return new ItemForm();
    }

    @GetMapping(path="/addTask")
    public String getTaskForm(ModelMap model, @ModelAttribute("form") ItemForm form){
        UserListForm userListForm=new UserListForm();
        userListForm.setUsers(users.findUsersAll());

        model.addAttribute("data",userListForm);
        return "TaskPages/createTask";
    }

    @PostMapping(path="/addTask")
    public String doPost(@Validated @ModelAttribute("form") ItemForm form,ModelMap model, HttpSession session, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            return "TaskPages/createTask";
        }
        Task createdTask=new Task();
        createdTask.setTitle(form.getTitle());
        createdTask.setStatus(TaskStatus.ToDo);
        createdTask.setReporter(users.findUserByUserName(session.getAttribute("verifiedUserName").toString()));
        if(form.getAssignee()!=0){
            createdTask.setAssignee(users.findUserById(form.getAssignee()));
        }
        createdTask.setTaskCode(form.getItemCode());
        try{
            createdTask.setTaskNumber(form.getItemNumber());
        }catch (NumberFormatException e){
            createdTask.setTaskNumber(1);
        }
        createdTask.setDescription(form.getDescription());
        taskDao.save(createdTask);
        return "redirect:/tasks";

    }
}
