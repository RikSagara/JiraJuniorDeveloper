import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.ngu.JiraJuniorDeveloper.DataBase.StoryDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.TaskDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
public class TaskDaoTest {

    private EntityManagerFactory factory;
    private EntityManager manager;
    private UserDao userDao;
    private TaskDao taskDao;
    @Before
    public void connect() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        userDao=new UserDao(manager);
        taskDao=new TaskDao(manager);
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
        Task s=taskDao.createTask("тестовая история","LVLP",100,reporter);
        Assert.assertNotEquals(s.getId(),0);
    }
    @Test
    public void CreateTaskByInstance(){
        User reporter =userDao.createUser("scott","tiger", UserRole.LoggedUser);
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
        User reporter =userDao.createUser("scott","tiger", UserRole.LoggedUser);
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
        List<Task> foundTasks=taskDao.findTasksByUser(assigneeSensei.getName());
        Assert.assertEquals(foundTasks.size(),1);

    }
    @Test
    public void FindTaskByCodeAndNumber(){
        User reporter =userDao.createUser("scott","tiger", UserRole.LoggedUser);
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
        User reporter =userDao.createUser("scott","tiger", UserRole.LoggedUser);
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
