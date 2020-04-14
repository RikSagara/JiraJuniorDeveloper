package ru.ngu.JiraJuniorDeveloper.Web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ngu.JiraJuniorDeveloper.DataBase.StoryDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.StoryRepository;
import ru.ngu.JiraJuniorDeveloper.Model.Story;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StoryListController {
    @Autowired
    private StoryRepository storyDao;

    @GetMapping(path="/stories")
    public String GetStoriesList(HttpSession session){
        List<Story> stories=storyDao.findStoriesByUser(session.getAttribute("verifiedUserName").toString());
        session.setAttribute("stories", stories);
        return "/StoryPages/storiesList";
    }
}
