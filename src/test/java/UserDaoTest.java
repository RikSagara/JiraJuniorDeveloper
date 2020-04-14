import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.ngu.JiraJuniorDeveloper.Configurations.TestConfiguration;
import ru.ngu.JiraJuniorDeveloper.DataBase.UserDao;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserDaoTest {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private UserDao userDao;


    @Test
    public void CreateUser(){
        User createdUser=userDao.createUser("scotty","tigy", UserRole.LoggedUser);
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
        Assert.assertEquals(5,foundUser.size());

    }

    @Test
    public void FindUserById(){
        userDao.createUser("RisKagara","123456", UserRole.Admin);
        userDao.createUser("RikSagara","1234", UserRole.LoggedUser);
        userDao.createUser("RikSogara","12345", UserRole.LoggedUser);
        userDao.createUser("RikSgara","12345", UserRole.LoggedUser);
        User foundUser=userDao.findUserById(3);
        Assert.assertEquals("RikSagara",foundUser.getUserName());
        Assert.assertEquals("1234",foundUser.getPassword());
        Assert.assertEquals(UserRole.LoggedUser,foundUser.getRole());


    }

    @Test
    public void EditUser(){
        userDao.createUser("RisKagara","123456", UserRole.Admin);
        userDao.createUser("RikSagara","1234", UserRole.LoggedUser);
        userDao.editUser(3,"RikSagara","1234567890",UserRole.Admin);

        User foundUser=userDao.findUserById(3);

        Assert.assertEquals("1234567890",foundUser.getPassword());
        Assert.assertEquals(UserRole.Admin,foundUser.getRole());


    }


}
