package ru.ngu.JiraJuniorDeveloper.DataBase;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ngu.JiraJuniorDeveloper.Model.Story;

import java.util.List;


@Repository
public interface StoryRepository extends CrudRepository<Story,Integer> {
    Story findStoryById(int id);

    @Query("SELECT s from Story s where s.assignee.userName=:userName or s.reporter.userName=:userName")
    List<Story> findStoriesByUser(@Param("userName") String userName);

    Story findStoryByStoryCodeAndStoryNumber(String storyCode,int storyNumber);
}
