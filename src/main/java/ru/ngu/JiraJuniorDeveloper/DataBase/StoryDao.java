package ru.ngu.JiraJuniorDeveloper.DataBase;

import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ngu.JiraJuniorDeveloper.Model.Story;
import ru.ngu.JiraJuniorDeveloper.Model.TaskStatus;
import ru.ngu.JiraJuniorDeveloper.Model.User;
import ru.ngu.JiraJuniorDeveloper.Model.UserRole;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Repository
public class StoryDao {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public Story createStory(String title, String storyCode, Integer storyNumber, User reporter) {
        Story createdStory = new Story();
        createdStory.setTitle(title);
        createdStory.setStoryCode(storyCode);
        createdStory.setStoryNumber(storyNumber);
        createdStory.setReporter(reporter);

        createdStory.setStatus(TaskStatus.ToDo);

        manager.persist(createdStory);


        return createdStory;
    }

    @Transactional
    public Story createStory(Story createdStory){
        manager.persist(createdStory);

        return createdStory;
    }


    public List<Story> findStoriesByUser(String userName) {
        return manager.createQuery("SELECT s from Story s where s.assignee.userName=:userName or s.reporter.userName=:userName", Story.class)
                .setParameter("userName",userName)
                .getResultList();
    }

    @Nullable
    public Story findStoryByCodeAndNumber(String storyCode,int storyNumber){
        try {
            return manager.createQuery("from Story u WHERE u.storyCode = :storyCode and u.storyNumber=:storyNumber", Story.class)
                    .setParameter("storyCode",storyCode)
                    .setParameter("storyNumber",storyNumber)
                    .getSingleResult();
        } catch (NoResultException cause) {
            return null;
        }
    }

    @Nullable
    public Story findStoryById(int id){
        try {
            return manager.createQuery("from Story u WHERE u.id = :id", Story.class)
                    .setParameter("id",id)
                    .getSingleResult();
        } catch (NoResultException cause) {
            return null;
        }
    }
}
