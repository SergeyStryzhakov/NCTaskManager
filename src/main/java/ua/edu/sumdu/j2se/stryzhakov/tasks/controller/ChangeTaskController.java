package ua.edu.sumdu.j2se.stryzhakov.tasks.controller;

import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Model;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Task;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.ChangeTaskView;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.Viewable;

import java.time.LocalDateTime;

public class ChangeTaskController implements Controller {
    private final Model model;
    private final ChangeTaskView view;
    private Task task;
    private String title;
    private String startTime;
    private String endTime;
    private int interval;
    private boolean repeat;
    private boolean active;


    public ChangeTaskController(Viewable view, Model model) {
        this.view = (ChangeTaskView) view;
        this.model = model;
        this.task = model.getCurrentTask();
    }

    /**
     * Get data from view for create/edit the task
     */
    private void getUserData() {
        try {
            title = view.getTitle();
            repeat = view.isTaskRepeat();
            active = view.isTaskActive();
            startTime = view.getTimeTask("start");
            if (repeat) {
                endTime = view.getTimeTask("end");
                if (dateFromString(endTime).isBefore(dateFromString(startTime))) {
                    System.out.println("The end date cannot be earlier the start date!");
                    getUserData();
                }
                interval = view.getInterval() * 60;
            }
        } catch (Exception e) {
            System.out.println("Invalid value, try again.");
            getUserData();
        }
    }

    /**
     * Create a new task
     */
    private void createTask() {
        if (title.isEmpty()) createTask();
        if (repeat) {
            task = new Task(title,
                    dateFromString(startTime),
                    dateFromString(endTime),
                    interval);
        } else {
            task = new Task(title, dateFromString(startTime));
        }
        task.setRepeated(repeat);
        task.setActive(active);
        model.setChanged(true);
    }

    /**
     * Edit exist task
     *
     * @param task for edit
     */
    private void editTask(Task task) {
        title = title.isEmpty() ? task.getTitle() : title;
        task.setTitle(title);
        LocalDateTime start = startTime.isEmpty()
                ? task.getStartTime()
                : dateFromString(startTime);
        if (repeat) {

            LocalDateTime end = endTime.isEmpty()
                    ? task.getStartTime()
                    : dateFromString(endTime);
            task.setTime(start, end, interval);

        } else {
            task.setTime(start);
        }
        task.setRepeated(repeat);
        task.setActive(active);
        model.setChanged(true);
    }

    @Override
    public void start() {
        getUserData();
        if (task == null) {
            createTask();
        } else {
            editTask(task);
        }
        int userChoice = view.show(task.toString());
        switch (userChoice) {
            case 1:
                if (model.getCurrentTask() == null) {
                    model.getList().add(task);
                }
                model.setCurrentTask(null);
            case 2:
                ControllerFactory.selectController(Action.SHOW).start();
        }
    }


}
