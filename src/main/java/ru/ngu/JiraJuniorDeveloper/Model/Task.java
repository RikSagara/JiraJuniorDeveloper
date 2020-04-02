package ru.ngu.JiraJuniorDeveloper.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"taskCode", "taskNumber"}))
public class Task {
    @Id
    @GeneratedValue
    private int id;

    private String title;

    @Column(name = "taskCode", nullable = false, length = 5)
    private String taskCode;

    @Column(name = "taskNumber", nullable = false)
    private int taskNumber;

    @OneToOne
    private User reporter;

    @OneToOne
    private User assignee;

    @Enumerated(EnumType.ORDINAL)
    private TaskStatus status;

    private String description;

    @OneToMany(targetEntity = User.class)
    private List<User> subscribers;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public List<User> getSubscribers() { return subscribers; }

    public void setSubscribers(List<User> subscribers) { this.subscribers = subscribers; }

    public String getTaskCode() { return taskCode; }

    public void setTaskCode(String taskCode) { this.taskCode = taskCode; }

    public int getTaskNumber() { return taskNumber; }

    public void setTaskNumber(int taskNumber) { this.taskNumber = taskNumber; }
}
