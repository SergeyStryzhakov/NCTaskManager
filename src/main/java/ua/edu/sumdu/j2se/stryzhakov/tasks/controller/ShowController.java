package ua.edu.sumdu.j2se.stryzhakov.tasks.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Model;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Task;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.Viewable;

public class ShowController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(ShowController.class);
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
logger.info("Start show controller");
    }

    /**
     * Get list of tasks from model
     */
    private void getTasks() {
        taskList = model.getList();
        sizeList = taskList.size();
logger.info("Task list have got.");
    }

    /**
     * Create string from list of tasks
     *
     * @return list as string for show in the view
     */
    private String createListAllTasks() {
        getTasks();
        mainMenu = sizeList;
        exit = sizeList + 1;
        logger.info("Main menu number is {}, exit number is {}", mainMenu, exit);
        StringBuilder builder = new StringBuilder();
        if(sizeList == 0) {
            builder.append("List of tasks is empty. Please, add task from main menu.\n");
        }
        for (int i = 0; i < sizeList; i++) {
            builder.append(i)
                    .append(". ")
                    .append(taskList.getTask(i).toString())
                    .append("\n");
        }
        builder.append(mainMenu).append(". Return to main menu").append("\n");
        builder.append(exit).append(". Exit");
        logger.info("String builder created successful");
        return builder.toString();
    }

    /**
     * Select task for change or remove by index from tasklist
     *
     * @param index of task
     * @return Selected task as string for change or remove
     */
    private String selectTask(int index) {
logger.info("Selected tas index is {}", index);
        task = taskList.getTask(index);
        return task.toString() +
                "\n1. Edit task\n2. Delete task\n";
    }

    /**
     * Select controller depends on user choice
     * Menu item such as "return to main menu" and "Exit"
     * have a dynamic number depends on size of the task list
     */
    @Override
    public void start() {
        Action action = Action.MAIN;
        int userChoice = view.show(createListAllTasks());
        if (userChoice < 0 || userChoice > sizeList + 1) {
            logger.error("Incorrect user choice, selected {}", userChoice);
            System.out.println("Enter a correct number");
            this.start();
        }
        if (userChoice == mainMenu) {
            action = Action.MAIN;
        } else if (userChoice == exit) {
            if (model.isChanged()) {
                action = Action.SAVE;
            } else {
                logger.info("Exit without saving");
                System.exit(0);
            }
        } else {
            userChoice = view.show(selectTask(userChoice));
            switch (userChoice) {
                case 1:
                    model.setCurrentTask(task);
                    action = Action.CHANGE;
                    break;
                case 2:
                    taskList.remove(task);
                    action = Action.SHOW;
                    break;
            }
        }
        ControllerFactory.selectController(action).start();

    }

}
