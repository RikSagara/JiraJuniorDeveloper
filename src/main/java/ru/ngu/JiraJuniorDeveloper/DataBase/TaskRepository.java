package ru.ngu.JiraJuniorDeveloper.DataBase;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.ngu.JiraJuniorDeveloper.Model.Story;
import ru.ngu.JiraJuniorDeveloper.Model.Task;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "task-api",
        itemResourceRel = "task-api",
        path = "task-api")
public interface TaskRepository extends CrudRepository<Task,Integer> {
    Task findTaskByTaskCodeAndTaskNumber(@Param("taskCode")String taskCode,@Param("taskNumber")int taskNumber);
    Task findTaskById(@Param("id")int id);
    @Query("SELECT t from Task t where t.assignee.userName=:userName or t.reporter.userName=:userName")
    List<Task> findTasksByUser(@Param("userName")String userName);
}
