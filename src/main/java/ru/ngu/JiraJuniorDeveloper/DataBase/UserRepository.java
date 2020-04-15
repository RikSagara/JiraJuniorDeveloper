package ru.ngu.JiraJuniorDeveloper.DataBase;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;

import java.util.List;
@Repository
@RepositoryRestResource(collectionResourceRel = "user-api",
        itemResourceRel = "user-api",
        path = "user-api")
public interface UserRepository extends CrudRepository<User,Integer> {
    User findUserById(@Param("id")int id);
    User findUserByUserName(@Param("UserName")String UserName);
    List<User> findUsersByRole(@Param("role")UserRole role);

    @Query("select y from User y")
    List<User> findUsersAll();
}
