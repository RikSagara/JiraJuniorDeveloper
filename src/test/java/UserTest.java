import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class UserTest {
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
    public void CreateAdmin()
    {
        User user = new User();
        user.setName("admin");
        user.setPassword("admin");
        user.setRole(UserRole.Admin);


        manager.getTransaction().begin();

        manager.persist(user);

        User foundUser = manager.find(User.class, user.getId());
        assertNotNull(foundUser);
        assertSame(foundUser, user);

        manager.getTransaction().commit();

        manager.refresh(foundUser);

    }

    @Test
    public void CreateLoggedUser()
    {
        User user = new User();
        user.setName("scott");
        user.setPassword("tiger");
        user.setRole(UserRole.LoggedUser);

        manager.getTransaction().begin();

        manager.persist(user);
        User foundUser = manager.find(User.class, user.getId());
        assertNotNull(foundUser);
        assertSame(foundUser, user);

        manager.getTransaction().commit();

        manager.refresh(foundUser);

    }
}
