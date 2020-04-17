
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.ngu.JiraJuniorDeveloper.Configurations.TestConfiguration;
import ru.ngu.JiraJuniorDeveloper.DataBase.StoryDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.*;
import ru.ngu.JiraJuniorDeveloper.Web.Controllers.LoginController;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
public class StoryListControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockHttpSession session;

    @Autowired
    private UserDao users;

    @Autowired
    private StoryDao storyDao;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void storyListForm() throws Exception {
        User loggedUser = users.createUser("Riks","12345", UserRole.LoggedUser);
        User admin=users.findUserByName("scott");
        for(int i=0;i<5;i++){
            Story createdStory=new Story();
            createdStory.setTitle("title "+Integer.toString(i));
            createdStory.setStoryCode("HW");
            createdStory.setStoryNumber(i);
            createdStory.setDescription("test story");
            createdStory.setStatus(TaskStatus.ToDo);
            if(i==3){
                createdStory.setAssignee(admin);
                createdStory.setReporter(loggedUser);
            }else {
                createdStory.setReporter(admin);
                createdStory.setAssignee(loggedUser);
            }
            storyDao.createStory(createdStory);
        }
        session.setAttribute(LoginController.VERIFIED_USER_NAME,"scott");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/stories").session(session)
        ).andExpect(status().isOk())
                .andExpect(view().name("/StoryPages/storiesList"))
                .andReturn();
        List<Story> stories=(List<Story>)session.getAttribute("stories");
        Assert.assertEquals(5,stories.size());
    }

}
