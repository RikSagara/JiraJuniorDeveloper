import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.ngu.JiraJuniorDeveloper.Model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;


public class TaskTest {
    private EntityManagerFactory factory;
    private EntityManager manager;

    @Before
    public void connect() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
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
        User reporter = new User();
        reporter.setUserName("scott");
        reporter.setPassword("tiger");
        reporter.setRole(UserRole.LoggedUser);

        User assignee = new User();
        assignee.setUserName("student");
        assignee.setPassword("123456");
        assignee.setRole(UserRole.LoggedUser);

        User subscriber = new User();
        subscriber.setUserName("teacher");
        subscriber.setPassword("qwerty");
        subscriber.setRole(UserRole.LoggedUser);

        Task createdTask=new Task();
        createdTask.setTaskCode("LVLP");
        createdTask.setTaskNumber(2);
        createdTask.setTitle("Создать репозиторий");
        createdTask.setReporter(reporter);
        createdTask.setAssignee(assignee);
        createdTask.setStatus(TaskStatus.Development);
        ArrayList<User> subscribers=new ArrayList<>();
        subscribers.add(subscriber);
        createdTask.setSubscribers(subscribers);
        manager.getTransaction().begin();

        manager.persist(reporter);
        manager.persist(assignee);
        manager.persist(subscriber);
        manager.persist(createdTask);

        Task foundTask = manager.find(Task.class, createdTask.getId());
        assertNotNull(foundTask);
        assertSame(foundTask, createdTask);

        manager.getTransaction().commit();

        manager.refresh(foundTask);

    }

}
