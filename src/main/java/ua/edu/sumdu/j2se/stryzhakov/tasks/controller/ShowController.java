package ua.edu.sumdu.j2se.stryzhakov.tasks.controller;

import ua.edu.sumdu.j2se.stryzhakov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Model;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Task;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.Viewable;

public class ShowController implements Controller {
    private final Viewable view;
    private final Model model;
    private AbstractTaskList taskList;
    private Task task;
    private int mainMenu;
    private int exit;
    private int sizeList;


    public ShowController(Viewable view, Model model) {
        this.view = view;
        this.model = model;

    }

    private void getTasks() {
        taskList = model.getList();
        sizeList = taskList.size();

    }

    private String createListAllTasks() {
        getTasks();
        mainMenu = sizeList;
        exit = sizeList + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sizeList; i++) {
            builder.append(i)
                    .append(". ")
                    .append(taskList.getTask(i).toString())
                    .append("\n");
        }
        builder.append(mainMenu).append(". Return to main menu").append("\n");
        builder.append(exit).append(". Exit");
        return builder.toString();
    }

    private String changeTask(int index) {
        task = taskList.getTask(index);
        return task.toString() +
                "\n1. Edit task\n2. Delete task\n";
    }

    @Override
    public void start() {
        int userChoice = view.show(createListAllTasks());
        if (userChoice < 0 || userChoice > sizeList + 1) {
            System.out.println("Enter a correct number");
            this.start();
        }
        if (userChoice == mainMenu) {
            ControllerFactory.selectController(Action.MAIN).start();
        } else if (userChoice == exit) {
            System.exit(0);
        } else {
            userChoice = view.show(changeTask(userChoice));
            switch (userChoice) {
                case 1:
                    model.setCurrentTask(task);
                    ControllerFactory.selectController(Action.CHANGE).start();
                case 2:
                    taskList.remove(task);
                    ControllerFactory.selectController(Action.SHOW).start();
            }
        }


    }

}
