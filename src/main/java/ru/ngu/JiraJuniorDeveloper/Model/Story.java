package ru.ngu.JiraJuniorDeveloper.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"storyCode", "storyNumber"}))
public class Story {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 5)
    private String storyCode;

    @Column(nullable = false)
    private int storyNumber;

    @OneToOne
    private User reporter;

    @OneToOne
    private User assignee;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private TaskStatus status;
    @Column
    private String description;

    @OneToMany
    private List<Task> tasks;

    public int getId() { return id;  }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public User getReporter() { return reporter; }

    public void setReporter(User reporter) { this.reporter = reporter; }

    public TaskStatus getStatus() { return status; }

    public void setStatus(TaskStatus status) { this.status = status; }

    public List<Task> getTasks() { return tasks; }

    public void setTasks(List<Task> tasks) { this.tasks = tasks; }

    public String getStoryCode() { return storyCode; }

    public void setStoryCode(String storyCode) { this.storyCode = storyCode; }

    public int getStoryNumber() { return storyNumber; }

    public void setStoryNumber(int storyNumber) { this.storyNumber = storyNumber; }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }
}
