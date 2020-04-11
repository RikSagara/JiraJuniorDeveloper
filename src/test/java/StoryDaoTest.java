import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.ngu.JiraJuniorDeveloper.DataBase.StoryDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

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
    public void CreateStory(){
        User reporter =userDao.createUser("scott","tiger", UserRole.LoggedUser);
        Story s=storyDao.createStory("тестовая история","LVLP",100,reporter);
        Assert.assertNotEquals(s.getId(),0);
    }
    @Test
    public void CreateStoryByInstance(){
        User reporter =userDao.createUser("scott","tiger", UserRole.LoggedUser);
        User assignee =userDao.createUser("RikSagara","1234", UserRole.LoggedUser);
        Story s=new Story();
        s.setTitle("Стори через экземпляр класса");
        s.setStoryCode("LVLP");
        s.setStoryNumber(2);
        s.setAssignee(assignee);
        s.setReporter(reporter);
        s.setStatus(TaskStatus.ToDo);
        s.setDescription("Описание стори через экземпляр класса");
        storyDao.createStory(s);

    }

    @Test
    public void FindStoryByUser(){
        User reporter =userDao.createUser("scott","tiger", UserRole.LoggedUser);
        User assignee =userDao.createUser("RikSagara","1234", UserRole.LoggedUser);
        User assigneeSensei =userDao.createUser("Sensei","12345", UserRole.LoggedUser);
        for(int i=0;i<11;i++) {
            Story s = new Story();
            s.setTitle("Стори через экземпляр класса");
            s.setStoryCode("LVLP");
            s.setStoryNumber(i);
            if(i==3){
                s.setAssignee(assigneeSensei);
            }else{
                s.setAssignee(assignee);
            }
            s.setReporter(reporter);
            s.setStatus(TaskStatus.ToDo);
            s.setDescription("Описание стори через экземпляр класса");
            storyDao.createStory(s);
        }
        List<Story> foundStories=storyDao.findStoriesByUser(assigneeSensei.getName());
        Assert.assertEquals(foundStories.size(),1);

    }
    @Test
    public void FindStoryByCodeAndNumber(){
        User reporter =userDao.createUser("scott","tiger", UserRole.LoggedUser);
        User assignee =userDao.createUser("RikSagara","1234", UserRole.LoggedUser);

        for(int i=0;i<12;i++) {
            Story s = new Story();
            s.setTitle("Стори через экземпляр класса");
            s.setStoryCode("LVLP");
            s.setStoryNumber(i);
            s.setAssignee(assignee);
            s.setReporter(reporter);
            s.setStatus(TaskStatus.ToDo);
            s.setDescription("Описание стори через экземпляр класса");
            storyDao.createStory(s);
        }

        Story foundedStory=storyDao.findStoryByCodeAndNumber("LVLP",5);
        Assert.assertNotNull(foundedStory);

    }

    @Test
    public void FindStoryById(){
        User reporter =userDao.createUser("scott","tiger", UserRole.LoggedUser);
        User assignee =userDao.createUser("RikSagara","1234", UserRole.LoggedUser);

        for(int i=0;i<10;i++) {
            Story s = new Story();
            s.setTitle("Стори через экземпляр класса");
            s.setStoryCode("LVLP");
            s.setStoryNumber(i);
            s.setAssignee(assignee);
            s.setReporter(reporter);
            s.setStatus(TaskStatus.ToDo);
            s.setDescription("Описание стори через экземпляр класса");
            storyDao.createStory(s);
        }

        Story foundedStory=storyDao.findStoryById(5);
        Assert.assertNotNull(foundedStory);

    }

}
