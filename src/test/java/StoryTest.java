import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.ngu.JiraJuniorDeveloper.Model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.invoke.MethodHandles.catchException;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;


public class StoryTest {

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
    public void CreateStory() {
        User reporter = new User();
        reporter.setUserName("scott");
        reporter.setPassword("tiger");
        reporter.setRole(UserRole.LoggedUser);

        User assignee = new User();
        assignee.setUserName("student");
        assignee.setPassword("123456");
        assignee.setRole(UserRole.LoggedUser);
        Story createdStory=new Story();
        createdStory.setAssignee(assignee);
        createdStory.setReporter(reporter);
        createdStory.setStatus(TaskStatus.ToDo);
        createdStory.setStoryCode("LVLP");
        createdStory.setStoryNumber(1);
        createdStory.setTitle("Первая домашняя работа");

        manager.getTransaction().begin();

        manager.persist(reporter);
        manager.persist(assignee);
        manager.persist(createdStory);

        Story foundStory = manager.find(Story.class, createdStory.getId());
        assertNotNull(foundStory);
        assertSame(foundStory, createdStory);

        manager.getTransaction().commit();

        manager.refresh(foundStory);



    }

    @Test(expected = RollbackException.class)
    public void CreateNonUniqueStory() {

        User reporter = new User();
        reporter.setUserName("scott");
        reporter.setPassword("tiger");
        reporter.setRole(UserRole.LoggedUser);

        User assignee = new User();
        assignee.setUserName("student");
        assignee.setPassword("123456");
        assignee.setRole(UserRole.LoggedUser);
        Story createdStory=new Story();
        createdStory.setAssignee(assignee);
        createdStory.setReporter(reporter);
        createdStory.setStatus(TaskStatus.ToDo);
        createdStory.setStoryCode("LVLP");
        createdStory.setStoryNumber(1);
        createdStory.setTitle("Первая домашняя работа");
        Story nonUniqueStory=new Story();
        nonUniqueStory.setAssignee(assignee);
        nonUniqueStory.setReporter(reporter);
        nonUniqueStory.setStatus(TaskStatus.ToDo);
        nonUniqueStory.setStoryCode("LVLP");
        nonUniqueStory.setStoryNumber(1);
        nonUniqueStory.setTitle("Первая работа");
        manager.getTransaction().begin();

        manager.persist(reporter);
        manager.persist(assignee);
        manager.persist(createdStory);
        manager.persist(nonUniqueStory);

        Story foundStory = manager.find(Story.class, createdStory.getId());
        assertNotNull(foundStory);
        assertSame(foundStory, createdStory);

        manager.getTransaction().commit();

        manager.refresh(foundStory);



    }
}
