package ua.edu.sumdu.j2se.stryzhakov.tasks.model;

import java.io.File;
import java.time.LocalDateTime;

public class Model {
    private final String JSON_DATA = "tasks.json";
    private AbstractTaskList list = new ArrayTaskList();
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private static Model instance;

    private Model() {
        File file = new File(JSON_DATA);
        TaskIO.readText(list, file);
    }

    public static Model getInstanse() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public AbstractTaskList getList() {
        return list;
    }

    public void setList(AbstractTaskList list) {
        this.list = list;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }
}
