import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.ngu.JiraJuniorDeveloper.DataBase.StoryDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.Story;
import ru.ngu.JiraJuniorDeveloper.Model.Task;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StoryDaoTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private UserDao userDao;
    private StoryDao storyDao;
    @Before
    public void connect() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        userDao=new UserDao(manager);
        storyDao=new StoryDao(manager);
    }

    @After
    public void close() {
        if (manager != null) {
            manager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void CreateTask(){
        User reporter =userDao.createUser("scott","tiger", UserRole.LoggedUser);
        Story s=storyDao.createStory("тестовая история","LVLP",100,reporter);
        Assert.assertNotEquals(s.getId(),0);
    }
}
