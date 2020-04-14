import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.ngu.JiraJuniorDeveloper.Configurations.TestConfiguration;
import ru.ngu.JiraJuniorDeveloper.DataBase.TaskDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TaskDaoTest {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TaskDao taskDao;

    @Test
    public void CreateTask(){
        User reporter =userDao.findUserByName("scott");
        Task s=taskDao.createTask("тестовая история","LVLP",100,reporter);
        Assert.assertNotEquals(s.getId(),0);
    }
    @Test
    public void CreateTaskByInstance(){
        User reporter =userDao.findUserByName("scott");
        User assignee =userDao.createUser("RikSagara","1234", UserRole.LoggedUser);
        Task s=new Task();
        s.setTitle("Стори через экземпляр класса");
        s.setTaskCode("LVLP");
        s.setTaskNumber(2);
        s.setAssignee(assignee);
        s.setReporter(reporter);
        s.setStatus(TaskStatus.ToDo);
        s.setDescription("Описание стори через экземпляр класса");
        taskDao.createTask(s);

    }

    @Test
    public void FindTaskByUser(){
        User reporter =userDao.findUserByName("scott");
        User assignee =userDao.createUser("RikSagara","1234", UserRole.LoggedUser);
        User assigneeSensei =userDao.createUser("Sensei","12345", UserRole.LoggedUser);
        for(int i=0;i<11;i++) {
            Task s = new Task();
            s.setTitle("Стори через экземпляр класса");
            s.setTaskCode("LVLP");
            s.setTaskNumber(i);
            if(i==3){
                s.setAssignee(assigneeSensei);
            }else{
                s.setAssignee(assignee);
            }
            s.setReporter(reporter);
            s.setStatus(TaskStatus.ToDo);
            s.setDescription("Описание стори через экземпляр класса");
            taskDao.createTask(s);
        }
        List<Task> foundTasks=taskDao.findTasksByUser(assigneeSensei.getUserName());
        Assert.assertEquals(foundTasks.size(),1);

    }
    @Test
    public void FindTaskByCodeAndNumber(){
        User reporter =userDao.findUserByName("scott");
        User assignee =userDao.createUser("RikSagara","1234", UserRole.LoggedUser);

        for(int i=0;i<12;i++) {
            Task s = new Task();
            s.setTitle("Стори через экземпляр класса");
            s.setTaskCode("LVLP");
            s.setTaskNumber(i);
            s.setAssignee(assignee);
            s.setReporter(reporter);
            s.setStatus(TaskStatus.ToDo);
            s.setDescription("Описание стори через экземпляр класса");
            taskDao.createTask(s);
        }

        Task foundedTask=taskDao.findTaskByCodeAndNumber("LVLP",5);
        Assert.assertNotNull(foundedTask);

    }

    @Test
    public void FindTaskById(){
        User reporter =userDao.findUserByName("scott");
        User assignee =userDao.createUser("RikSagara","1234", UserRole.LoggedUser);

        for(int i=0;i<10;i++) {
            Task s = new Task();
            s.setTitle("Стори через экземпляр класса");
            s.setTaskCode("LVLP");
            s.setTaskNumber(i);
            s.setAssignee(assignee);
            s.setReporter(reporter);
            s.setStatus(TaskStatus.ToDo);
            s.setDescription("Описание стори через экземпляр класса");
            taskDao.createTask(s);
        }

        Task foundedTask=taskDao.findTaskById(5);
        Assert.assertNotNull(foundedTask);

    }

}
