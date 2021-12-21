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

    public ChangeTaskController(Viewable view, Model model) {
        this.view = (ChangeTaskView) view;
        this.model = model;
        this.task = model.getCurrentTask();
    }

    private void createTask() {
        String startTime = "";
        String endTime = "";
        try {
            String title = view.getTitle();
            if (title.isEmpty()) createTask();
            boolean repeat = view.isTaskRepeat();
            boolean active = view.isTaskActive();
            startTime = view.getTimeTask("start");
            if (repeat) {
                endTime = view.getTimeTask("end");
                if (dateFromString(endTime).isBefore(dateFromString(startTime))) {
                    System.out.println("The end date cannot be earlier the start date!");
                    createTask();
                }
                int interval = view.getInterval() * 60;
                task = new Task(title,
                        dateFromString(startTime),
                        dateFromString(endTime),
                        interval);
            } else {
                task = new Task(title, dateFromString(startTime));
            }
            task.setRepeated(repeat);
            task.setActive(active);
        } catch (Exception e) {
            System.out.println("Invalid date");
            createTask();
        }
    }

    private void editTask(Task task) {
        String start = "";
        String end = "";
        try {
            String title = view.getTitle();
            title = title.isEmpty() ? task.getTitle() : title;
            task.setTitle(title);
            boolean repeat = view.isTaskRepeat();
            boolean active = view.isTaskActive();
            start = view.getTimeTask("start");

            if (repeat) {
                end = view.getTimeTask("end");
                if (dateFromString(end).isBefore(dateFromString(start))) {
                    System.out.println("The end date cannot be earlier the start date!");
                    editTask(task);
                }
                LocalDateTime startTime = start.isEmpty()
                        ? task.getStartTime()
                        : dateFromString(start);
                LocalDateTime endTime = end.isEmpty()
                        ? task.getStartTime()
                        : dateFromString(end);

                int interval = view.getInterval() * 60;
                task = new Task(title,
                        startTime,
                        endTime,
                        interval);
            } else {
                LocalDateTime startTime = start.isEmpty()
                        ? task.getTime()
                        : dateFromString(start);
                task = new Task(title, startTime);
            }
            task.setRepeated(repeat);
            task.setActive(active);
        } catch (Exception e) {
            System.out.println("Invalid date");
            editTask(task);
        }
    }

    @Override
    public void start() {
        if (task == null) {
            createTask();
        } else {
            editTask(task);
        }
        int userChoice = view.show(task.toString());
        switch (userChoice) {
            case 1:
                model.getList().add(task);
                model.save(model.getList());
                model.setCurrentTask(null);
            case 2:
                ControllerFactory.selectController(Action.SHOW).start();
        }
    }


}
