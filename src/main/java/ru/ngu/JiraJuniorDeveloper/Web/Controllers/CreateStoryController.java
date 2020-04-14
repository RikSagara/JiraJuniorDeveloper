package ru.ngu.JiraJuniorDeveloper.Web.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ngu.JiraJuniorDeveloper.DataBase.StoryDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.StoryRepository;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserRepository;
import ru.ngu.JiraJuniorDeveloper.Model.Story;
import ru.ngu.JiraJuniorDeveloper.Model.TaskStatus;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Web.Forms.ItemForm;
import ru.ngu.JiraJuniorDeveloper.Web.Forms.UserListForm;


import javax.servlet.http.HttpSession;

import java.util.List;


@Controller
public class CreateStoryController {
    @Autowired
    private UserRepository users;

    @Autowired
    private StoryRepository story;

    @ModelAttribute("form")
    public ItemForm getStoryForm() {
        return new ItemForm();
    }

    @GetMapping(path="/addStory")
    public String getTaskForm(ModelMap model, @ModelAttribute("form") ItemForm form){
        UserListForm userListForm=new UserListForm();
        userListForm.setUsers(users.findUsersAll());

        model.addAttribute("data",userListForm);
        return "/StoryPages/createStory";
    }

    @PostMapping(path="/addStory")
    public String doPost(@Validated @ModelAttribute("form") ItemForm form, HttpSession session, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            return "/StoryPages/createStory";
        }
        Story createdStory=new Story();
        createdStory.setTitle(form.getTitle());
        createdStory.setStatus(TaskStatus.ToDo);
        createdStory.setReporter(users.findUserByUserName(session.getAttribute("verifiedUserName").toString()));
        if(form.getAssignee()!=0){
            createdStory.setAssignee(users.findUserById(form.getAssignee()));
        }
        createdStory.setStoryCode(form.getItemCode());
        createdStory.setStoryNumber(form.getItemNumber());

        createdStory.setDescription(form.getDescription());
        story.save(createdStory);
        return "redirect:/stories";
    }

}
