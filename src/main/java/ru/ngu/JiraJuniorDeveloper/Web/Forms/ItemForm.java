package ru.ngu.JiraJuniorDeveloper.Web.Forms;

import ru.ngu.JiraJuniorDeveloper.Model.TaskStatus;
import ru.ngu.JiraJuniorDeveloper.Model.User;

import javax.validation.constraints.Pattern;


public class ItemForm {

    private String title;

    @Pattern(regexp = "[A-Z]+",
            message = "Only uppercase letters are allowed in Code.")
    private String itemCode;

    private int itemNumber;

    private int assignee;

    private TaskStatus status;

    private String description;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAssignee() {
        return assignee;
    }

    public void setAssignee(int assignee) {
        this.assignee = assignee;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getItemCode() { return itemCode; }

    public void setItemCode(String itemCode) { this.itemCode = itemCode; }

    public int getItemNumber() { return itemNumber; }

    public void setItemNumber(int itemNumber) { this.itemNumber = itemNumber; }
}
