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
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;
import ru.ngu.JiraJuniorDeveloper.Web.Controllers.LoginController;
import ru.ngu.JiraJuniorDeveloper.Web.Forms.ItemForm;
import ru.ngu.JiraJuniorDeveloper.Web.Forms.UserListForm;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
public class TaskControllerTest {

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
    public void testTaskForm() throws Exception {
        UserListForm userListForm=new UserListForm();
        userListForm.setUsers(users.findAllUsers());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/addTask").session(session)
        ).andExpect(status().isOk())
                .andExpect(view().name("TaskPages/createTask"))
                .andExpect(model().attribute("data",userListForm))
                .andReturn();
    }

    @Test
    public void PostStoryFormTest() throws Exception {
        ItemForm form=new ItemForm();
        form.setTitle("HomeWork Test");
        form.setItemCode("HWT");
        form.setItemNumber(1);
        form.setAssignee(1);
        form.setDescription("Home work valid test");
        session.setAttribute(LoginController.VERIFIED_USER_NAME,"scott");
        session.setAttribute(LoginController.VERIFIED_USER_ROLE, UserRole.Admin);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/addTask")
                        .session(session)
                        .param("title",form.getTitle())
                        .param("itemCode",form.getItemCode())
                        .param("itemNumber",Integer.toString(form.getItemNumber()))
                        .param("assignee",Integer.toString(form.getAssignee()))
                        .param("description",form.getDescription())
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/tasks"))
                .andReturn();
    }
}
