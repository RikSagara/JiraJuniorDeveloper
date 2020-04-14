package ru.ngu.JiraJuniorDeveloper.DataBase;

import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ngu.JiraJuniorDeveloper.Model.Story;
import ru.ngu.JiraJuniorDeveloper.Model.Task;
import ru.ngu.JiraJuniorDeveloper.Model.TaskStatus;
import ru.ngu.JiraJuniorDeveloper.Model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;
@Repository
public class TaskDao {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public Task createTask(String title, String taskCode, Integer taskNumber, User reporter){
        Task createdTask=new Task();
        createdTask.setTitle(title);
        createdTask.setTaskCode(taskCode);
        createdTask.setTaskNumber(taskNumber);
        createdTask.setReporter(reporter);
        createdTask.setStatus(TaskStatus.ToDo);

        manager.persist(createdTask);
        return createdTask;
    }

    @Transactional
    public Task createTask(Task createdTask) {
        manager.persist(createdTask);

        return createdTask;
    }

    public List<Task> findTasksByUser(String userName) {
        return manager.createQuery("SELECT t from Task t where t.assignee.userName=:userName or t.reporter.userName=:userName", Task.class)
                .setParameter("userName",userName)
                .getResultList();
    }

    @Nullable
    public Task findTaskByCodeAndNumber(String taskCode,int taskNumber){
        try {
            return manager.createQuery("from Task t WHERE t.taskCode = :taskCode and t.taskNumber=:taskNumber", Task.class)
                    .setParameter("taskCode",taskCode)
                    .setParameter("taskNumber",taskNumber)
                    .getSingleResult();
        } catch (NoResultException cause) {
            return null;
        }
    }

    @Nullable
    public Task findTaskById(int id){
        try {
            return manager.createQuery("from Task t WHERE t.id = :id", Task.class)
                    .setParameter("id",id)
                    .getSingleResult();
        } catch (NoResultException cause) {
            return null;
        }
    }
}
