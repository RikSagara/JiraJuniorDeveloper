package ru.ngu.JiraJuniorDeveloper.DataBase;

import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;
import javax.persistence.NoResultException;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import com.sun.istack.Nullable;

public class UserDao {
    private EntityManager manager;

    public UserDao(EntityManager manager) {
        Objects.requireNonNull(manager, "EntityManager shouldn't be null");
        this.manager = manager;
    }

    public User createUser(String name, String password, UserRole role){
        User createdUser=new User();
        createdUser.setName(name);
        createdUser.setPassword(password);
        createdUser.setRole(role);
        manager.getTransaction().begin();
        try {
            manager.persist(createdUser);
        } catch (Throwable cause) {
            manager.getTransaction().rollback();
            throw cause;
        }

        manager.getTransaction().commit();

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
        return manager.createQuery("SELECT u from User u where u.role=:role", User.class).getResultList();
    }

}
