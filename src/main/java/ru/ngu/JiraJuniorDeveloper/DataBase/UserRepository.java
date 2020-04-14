package ru.ngu.JiraJuniorDeveloper.DataBase;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;

import java.util.List;
@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    User findUserById(int id);
    User findUserByUserName(String UserName);
    List<User> findUsersByRole(UserRole role);

    @Query("select y from User y")
    List<User> findUsersAll();
}
