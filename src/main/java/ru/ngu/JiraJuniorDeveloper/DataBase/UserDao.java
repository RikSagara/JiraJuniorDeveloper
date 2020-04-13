package ru.ngu.JiraJuniorDeveloper.DataBase;

import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;
import javax.persistence.NoResultException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;


@Repository
public class UserDao {
    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public User createUser(String name, String password, UserRole role) {
        User createdUser = new User();
        createdUser.setName(name);
        createdUser.setPassword(password);
        createdUser.setRole(role);

        manager.persist(createdUser);


        return createdUser;
    }

    @Nullable
    public User findUserByName(String name) {
        try {
            return manager.createQuery("from User u WHERE u.name = :name", User.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException cause) {
            return null;
        }
    }

    public List<User> findUsersByUserRole(UserRole role) {
        return manager.createQuery("SELECT u from User u where u.role=:role", User.class)
                .setParameter("role",role)
                .getResultList();
    }

    public List<User> findAllUsers() {
        return manager.createQuery("SELECT u from User u").getResultList();
    }

    @Nullable
    public User findUserById(int id) {
        try {
            return manager.createQuery("from User u WHERE u.Id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException cause) {
            return null;
        }
    }
    @Transactional
    public void editUser(int id, String login, String password, UserRole role) {

        manager.createQuery("update User u set u.name=:login,u.password=:password,u.role=:role where u.Id=:id")
               .setParameter("id",id)
               .setParameter("login",login)
               .setParameter("password",password)
               .setParameter("role",role).executeUpdate();


    }
}
