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
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;
import ru.ngu.JiraJuniorDeveloper.Web.Controllers.LoginController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
public class AdminControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockHttpSession session;

    @Autowired
    private UserDao users;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testAdminForm() throws Exception {
        session.setAttribute(LoginController.VERIFIED_USER_NAME,"scott");
        session.setAttribute(LoginController.VERIFIED_USER_ROLE, UserRole.Admin);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/admin").session(session)
        ).andExpect(status().isOk())
                .andExpect(view().name("/UsersPages/admin"))
                .andReturn();
    }

    @Test
    public void testNotLoggedAdminForm() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/admin").session(session)
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))

                .andReturn();
    }

    @Test
    public void testLoggedUserAdminForm() throws Exception {
        User createdUser=users.createUser("RikSogara","123",UserRole.LoggedUser);
        session.setAttribute(LoginController.VERIFIED_USER_NAME,createdUser.getUserName());
        session.setAttribute(LoginController.VERIFIED_USER_ROLE,createdUser.getRole());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/admin").session(session)
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))

                .andReturn();
    }
}
