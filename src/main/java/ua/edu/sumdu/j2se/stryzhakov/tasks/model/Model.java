package ua.edu.sumdu.j2se.stryzhakov.tasks.model;

import java.io.File;

public class Model {
    private final String db = "tasks.json";
    private AbstractTaskList list = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
    private Task currentTask;
    private boolean changed;
    private static Model instance;


    private Model() {
        getData();
    }

    private void getData() {
        File file = new File(db);
        TaskIO.readText(list, file);

    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public void save(AbstractTaskList list) {
        TaskIO.writeText(list, new File(db));
    }

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }

    public AbstractTaskList getList() {
        return list;
    }

    public void setList(AbstractTaskList list) {
        this.list = list;
    }


}
