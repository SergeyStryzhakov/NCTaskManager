package ua.edu.sumdu.j2se.stryzhakov.tasks.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * View for ChangeTaskController.
 */
public class ChangeTaskView implements Viewable {
    private final Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ChangeTaskView.class);

    public String getTitle() {
        System.out.println("Enter a title");
        String title = scanner.nextLine();
        LOGGER.info("Title is {}", title);
        return title;
    }

    public boolean isTaskRepeat() {
        boolean repeat = false;
        try {
            System.out.println("Is the task repeat?\n1. Yes\n2. No");
            String temp = scanner.nextLine();
            LOGGER.info("User choice of repeat is {}", temp);
            if (temp.isEmpty()) {
                throw new InputMismatchException();
            }
            if (temp.equals("1")) {
                repeat = true;
            }

        } catch (InputMismatchException e) {
            LOGGER.error(e.getMessage());
            System.out.println("Cannot be empty! Please, select again ");
            isTaskRepeat();
        }
        return repeat;
    }

    public String getTimeTask(final String data) {
        System.out.println("Task " + data
                + " Date and Time: (yyyy-MM-dd HH:mm)");
        String time = scanner.nextLine();
        if (time.isEmpty()) {
            getTimeTask(data);
        }
        LOGGER.info("Time for {} is {}", data, time);
        return time;
    }

    public int getInterval() {
        int intervalTask = 0;
        System.out.println("Task interval in minutes");
        String interval = scanner.nextLine();
        try {
            intervalTask = Integer.parseInt(interval);
            LOGGER.info("Interval is {}", intervalTask);
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
            System.out.println("Not integer! Try again");
            getInterval();
        }
        return intervalTask;
    }

    public boolean isTaskActive() {
        boolean active = false;
        try {
            System.out.println("Is the task active?\n1. Yes\n2. No");
            String temp = scanner.nextLine();
            LOGGER.info("User choice of active is {}", temp);
            if (temp.isEmpty()) {
                throw new InputMismatchException();
            }
            if (temp.equals("1")) {
                active = true;
            }

        } catch (InputMismatchException e) {
            LOGGER.error(e.getMessage());
            System.out.println("Cannot be empty! Please, select again ");
            isTaskActive();
        }
        return active;
    }

    @Override
    public int show(final String text) {
        int userChoice = 0;
        System.out.println(text);
        System.out.println("Save this task?\n1. Yes\n2. No ");
        try {
            userChoice = Integer.parseInt(scanner.nextLine());
            LOGGER.info("User choice is {} ", userChoice);
            if (userChoice < 1 || userChoice > 2) {
                throw new InputMismatchException();
            }
        } catch (NumberFormatException | InputMismatchException e) {
            LOGGER.error(e.getMessage());
            System.out.println("Please, enter a correct number!");
            this.show(text);
        }
        return userChoice;
    }
}
