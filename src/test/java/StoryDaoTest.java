import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.ngu.JiraJuniorDeveloper.Configurations.TestConfiguration;
import ru.ngu.JiraJuniorDeveloper.DataBase.StoryDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.*;

import javax.persistence.EntityManager;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StoryDaoTest {
@Autowired
    private EntityManager manager;
@Autowired
    private UserDao userDao;
@Autowired
    private StoryDao storyDao;


    @Test
    public void CreateStory(){
        User reporter =userDao.findUserByName("scott");
        Story s=storyDao.createStory("тестовая история","LVLP",100,reporter);
        Assert.assertNotEquals(s.getId(),0);
    }
    @Test
    public void CreateStoryByInstance(){
        User reporter =userDao.findUserByName("scott");
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
        User reporter =userDao.findUserByName("scott");
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
        List<Story> foundStories=storyDao.findStoriesByUser(assigneeSensei.getUserName());
        Assert.assertEquals(foundStories.size(),1);

    }
    @Test
    public void FindStoryByCodeAndNumber(){
        User reporter =userDao.findUserByName("scott");
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
        User reporter=userDao.findUserByName("scott");
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
