package ru.ngu.JiraJuniorDeveloper.Model;

public enum  TaskStatus {
    ToDo(1),
    Development(2),
    Testing(3),
    Done(4);

    private int status;

    TaskStatus(int statusToSet){
        status=statusToSet;
    }

    public int getStatus() {
        return status;
    }
}
