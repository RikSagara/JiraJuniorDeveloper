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

import ru.ngu.JiraJuniorDeveloper.DataBase.TaskDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.*;
import ru.ngu.JiraJuniorDeveloper.Web.Controllers.LoginController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
public class TaskListCotrollerTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockHttpSession session;

    @Autowired
    private UserDao users;

    @Autowired
    private TaskDao taskDao;

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
            Task createdTask=new Task();
            createdTask.setTitle("title "+Integer.toString(i));
            createdTask.setTaskCode("HW");
            createdTask.setTaskNumber(i);
            createdTask.setDescription("test story");
            createdTask.setStatus(TaskStatus.ToDo);
            if(i==3){
                createdTask.setAssignee(admin);
                createdTask.setReporter(loggedUser);
            }else {
                createdTask.setReporter(admin);
                createdTask.setAssignee(loggedUser);
            }
            taskDao.createTask(createdTask);
        }
        session.setAttribute(LoginController.VERIFIED_USER_NAME,"scott");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/tasks").session(session)
        ).andExpect(status().isOk())
                .andExpect(view().name("/TaskPages/tasksList"))
                .andReturn();
        List<Task> tasks=(List<Task>)session.getAttribute("tasks");
        Assert.assertEquals(5,tasks.size());
    }

}
