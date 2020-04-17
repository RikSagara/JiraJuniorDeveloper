
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import ru.ngu.JiraJuniorDeveloper.Web.Forms.RegistrationForm;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
public class RegistrationControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void testRegistrationForm() throws Exception {
        RegistrationForm form = new RegistrationForm();
        form.setUserName("");
        form.setPassword("");

        mockMvc.perform(
                MockMvcRequestBuilders.get("/user/register")
        ).andExpect(status().isOk())
                .andExpect(model().attribute("form", form))
                .andExpect(view().name("UsersPages/registerUser"))
                .andReturn();
    }

    @Test
    public void PostRegistrationFormTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/user/register")
                        .param("userName", "Bill")
                        .param("password", "123")
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login?login=Bill"))
                .andReturn();
    }

    @Test
    public void PostRegistrationFormInvalidTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/user/register")
                        .param("userName", "invalid-login")
                        .param("password", "123")
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login?login=invalid-login"))
                .andReturn();
    }


}
