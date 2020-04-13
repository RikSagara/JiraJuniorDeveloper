package ru.ngu.JiraJuniorDeveloper.Web.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ngu.JiraJuniorDeveloper.DataBase.StoryDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.Story;
import ru.ngu.JiraJuniorDeveloper.Model.TaskStatus;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Web.Forms.ItemForm;


import javax.servlet.http.HttpSession;

import java.util.List;


@Controller
public class CreateStoryController {
    @Autowired
    private UserDao users;

    @Autowired
    private StoryDao storyDao;

    @GetMapping(path="/addStory")
    public String getTaskForm(HttpSession session){
        List<User> userList=users.findAllUsers();
        session.setAttribute("storyForm",new ItemForm());
        session.setAttribute("users", userList);
        return "/StoryPages/createStory";
    }

    @PostMapping("/addStory")
    public String doPost(@ModelAttribute("storyForm") ItemForm form, HttpSession session){
        Story createdStory=new Story();
        createdStory.setTitle(form.getTitle());
        createdStory.setStatus(TaskStatus.ToDo);
        createdStory.setReporter(users.findUserByName(session.getAttribute("verifiedUserName").toString()));
        if(form.getAssignee()!=null){
            createdStory.setAssignee(users.findUserById(Integer.parseInt(form.getAssignee())));
        }
        createdStory.setStoryCode(form.getItemCode());
        createdStory.setStoryNumber(form.getItemNumber());

        createdStory.setDescription(form.getDescription());
        storyDao.createStory(createdStory);
        return "redirect:/stories";
    }

}
