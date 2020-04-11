package ru.ngu.JiraJuniorDeveloper.DataBase;

import com.sun.istack.Nullable;
import ru.ngu.JiraJuniorDeveloper.Model.Story;
import ru.ngu.JiraJuniorDeveloper.Model.Task;
import ru.ngu.JiraJuniorDeveloper.Model.TaskStatus;
import ru.ngu.JiraJuniorDeveloper.Model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Objects;

public class TaskDao {
    private EntityManager manager;

    public TaskDao(EntityManager manager) {
        Objects.requireNonNull(manager, "EntityManager shouldn't be null");
        this.manager = manager;
    }

    public Task createTask(String title, String taskCode, Integer taskNumber, User reporter){
        Task createdTask=new Task();
        createdTask.setTitle(title);
        createdTask.setTaskCode(taskCode);
        createdTask.setTaskNumber(taskNumber);
        createdTask.setReporter(reporter);

        createdTask.setStatus(TaskStatus.ToDo);
        manager.getTransaction().begin();
        try {
            manager.persist(createdTask);
        } catch (Throwable cause) {
            manager.getTransaction().rollback();
            throw cause;
        }

        manager.getTransaction().commit();

        return createdTask;
    }

    public Task createTask(Task createdTask){
        manager.getTransaction().begin();
        try {
            manager.persist(createdTask);
        } catch (Throwable cause) {
            manager.getTransaction().rollback();
            throw cause;
        }

        manager.getTransaction().commit();
        manager.refresh(createdTask);
        return createdTask;
    }

    public List<Task> findTasksByUser(String userName) {
        return manager.createQuery("SELECT t from Task t where t.assignee.name=:userName or t.reporter.name=:userName", Task.class)
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
