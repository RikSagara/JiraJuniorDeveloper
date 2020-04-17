import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
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
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;
import ru.ngu.JiraJuniorDeveloper.Web.Controllers.LoginController;
import ru.ngu.JiraJuniorDeveloper.Web.Forms.RegistrationForm;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
public class LoginControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockHttpSession session;

    private MockMvc mockMvc;

    @Autowired
    private UserDao user;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testLoginForm() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/login")
        ).andExpect(status().isOk())
                .andExpect(view().name("UsersPages/login"))
                .andReturn();
    }

    @Test
    public void loginFormAlreadyLoggedInTest() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .param("username", "test")
                        .param("password", "123")
                        .sessionAttr(LoginController.VERIFIED_USER_NAME, "scott")
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andReturn();
    }

    @Test
    public void loginFormValidAdminTest() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/login").session(session)
                        .param("username", "scott")
                        .param("password", "tiger")

        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin"))

                .andReturn();
        Assert.assertEquals(session.getAttribute(LoginController.VERIFIED_USER_NAME),"scott");
        Assert.assertEquals(session.getAttribute(LoginController.VERIFIED_USER_ROLE),UserRole.Admin);
    }

    @Test
    public void loginFormValidLoggedUserTest() throws Exception {
        user.createUser("RikSogara","12345",UserRole.LoggedUser);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/login").session(session)
                        .param("username", "RikSogara")
                        .param("password", "12345")

        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))

                .andReturn();
        Assert.assertEquals(session.getAttribute(LoginController.VERIFIED_USER_NAME),"RikSogara");
        Assert.assertEquals(session.getAttribute(LoginController.VERIFIED_USER_ROLE),UserRole.LoggedUser);
    }

    @Test
    public void loginFormInvalidUserTest() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .param("username", "RikSogara")
                        .param("password", "12345")

        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login?login=RikSogara"))

                .andReturn();

    }

    @Test
    public void loginFormNullUserTest() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .param("username", "")
                        .param("password", "")

        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))

                .andReturn();

    }

}
