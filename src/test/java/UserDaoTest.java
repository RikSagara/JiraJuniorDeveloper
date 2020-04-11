import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ru.ngu.JiraJuniorDeveloper.DataBase.StoryDao;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserDaoTest {

    private EntityManagerFactory factory;
    private EntityManager manager;
    private UserDao userDao;

    @Before
    public void connect() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        userDao=new UserDao(manager);

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
    public void CreateUser(){
        User createdUser=userDao.createUser("scott","tiger", UserRole.LoggedUser);
        Assert.assertNotEquals(0,createdUser.getId());
    }

    @Test
    public void FindUserByName(){
        userDao.createUser("RisKagara","123456", UserRole.Admin);
        userDao.createUser("RikSagara","1234", UserRole.LoggedUser);
        userDao.createUser("RikSogara","12345", UserRole.LoggedUser);
        User foundUser=userDao.findUserByName("RikSagara");
        Assert.assertNotEquals(0,foundUser.getId());
        Assert.assertEquals("1234",foundUser.getPassword());
        Assert.assertEquals(UserRole.LoggedUser,foundUser.getRole());

    }

    @Test
    public void FindUserByRole(){
        userDao.createUser("RisKagara","123456", UserRole.Admin);
        userDao.createUser("RikSagara","1234", UserRole.LoggedUser);
        userDao.createUser("RikSogara","12345", UserRole.LoggedUser);
        List<User> foundUser=userDao.findUsersByUserRole(UserRole.LoggedUser);
        Assert.assertEquals(2,foundUser.size());

    }

    @Test
    public void FindAllUsers(){
        userDao.createUser("RisKagara","123456", UserRole.Admin);
        userDao.createUser("RikSagara","1234", UserRole.LoggedUser);
        userDao.createUser("RikSogara","12345", UserRole.LoggedUser);
        userDao.createUser("RikSgara","12345", UserRole.LoggedUser);
        List<User> foundUser=userDao.findAllUsers();
        Assert.assertEquals(4,foundUser.size());

    }

    @Test
    public void FindUserById(){
        userDao.createUser("RisKagara","123456", UserRole.Admin);
        userDao.createUser("RikSagara","1234", UserRole.LoggedUser);
        userDao.createUser("RikSogara","12345", UserRole.LoggedUser);
        userDao.createUser("RikSgara","12345", UserRole.LoggedUser);
        User foundUser=userDao.findUserById(2);
        Assert.assertEquals("RikSagara",foundUser.getName());
        Assert.assertEquals("1234",foundUser.getPassword());
        Assert.assertEquals(UserRole.LoggedUser,foundUser.getRole());


    }

    @Test
    public void EditUser(){
        userDao.createUser("RisKagara","123456", UserRole.Admin);
        userDao.createUser("RikSagara","1234", UserRole.LoggedUser);
        userDao.editUser(2,"RikSagara","1234567890",UserRole.Admin);
        User foundUser=userDao.findUserById(2);
        manager.refresh(foundUser);
        Assert.assertEquals("1234567890",foundUser.getPassword());
        Assert.assertEquals(UserRole.Admin,foundUser.getRole());


    }


}
