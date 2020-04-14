package ru.ngu.JiraJuniorDeveloper.DataBase;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ngu.JiraJuniorDeveloper.Model.Story;
import ru.ngu.JiraJuniorDeveloper.Model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task,Integer> {
    Task findTaskByTaskCodeAndTaskNumber(String taskCode,int taskNumber);
    Task findTaskById(int id);
    @Query("SELECT t from Task t where t.assignee.userName=:userName or t.reporter.userName=:userName")
    List<Task> findTasksByUser(@Param("userName")String userName);
}
