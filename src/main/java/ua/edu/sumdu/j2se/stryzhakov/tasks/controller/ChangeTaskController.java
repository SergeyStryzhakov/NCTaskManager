package ua.edu.sumdu.j2se.stryzhakov.tasks.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Model;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Task;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.ChangeTaskView;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.Viewable;

import java.time.LocalDateTime;

/**
 * This class can edit task and create user with parameter.
 * from ChangeTaskView
 */
public class ChangeTaskController implements Controller {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ChangeTaskController.class);
    private final Model model;
    private final ChangeTaskView view;
    private Task task;
    private String title;
    private String startTime;
    private String endTime;
    private int interval;
    private boolean repeat;
    private boolean active;


    public ChangeTaskController(final Viewable view, final Model model) {
        this.view = (ChangeTaskView) view;
        this.model = model;
        this.task = model.getCurrentTask();
        LOGGER.info("Start ChangeTaskController");
    }

    /**
     * Get data from view for create/edit the task.
     */
    private void getUserData() {
        try {
            title = view.getTitle();
            repeat = view.isTaskRepeat();
            active = view.isTaskActive();
            startTime = view.getTimeTask("start");
            if (repeat) {
                endTime = view.getTimeTask("end");
                if (dateFromString(endTime)
                        .isBefore(dateFromString(startTime))) {
                    LOGGER.error("Date {} > date {}", endTime, startTime);
                    LOGGER.error("The end date cannot be"
                            + " earlier the start date!");
                    getUserData();
                }
                interval = view.getInterval() * 60;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Invalid date, try again by pattern");
            getUserData();
        }
    }

    /**
     * Create a new task.
     */
    private void createTask() {
        LOGGER.info("Create task is started");
        if (title.isEmpty()) {
            createTask();
        }
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
        LOGGER.info("Task created: {}", task.toString());
        LOGGER.info("Creating task is finished");
    }

    /**
     * Edit exist task.
     *
     * @param task for edit
     */
    private void editTask(final Task task) {
        LOGGER.info("Start editing task");
        LOGGER.info("Task before edit: {}", task.toString());
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
        LOGGER.info("Task after edit: {}", task);
        LOGGER.info("Finish editing task.");
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
                    LOGGER.info("Add new task to list");
                }
                model.setCurrentTask(null);
            case 2:
                ControllerFactory.selectController(Action.SHOW).start();
        }
    }


}
