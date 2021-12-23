package ua.edu.sumdu.j2se.stryzhakov.tasks.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ChangeTaskView implements Viewable {
    private final Scanner scanner = new Scanner(System.in);

    public String getTitle() {
        System.out.println("Enter a title");
        return scanner.nextLine();
    }

    public boolean isTaskRepeat() {
        boolean repeat = false;
        try {
            System.out.println("Is the task repeat?\n1. Yes\n2. No");
            String temp = scanner.nextLine();

            if (temp.isEmpty()) throw new InputMismatchException();
            if (temp.equals("1")) {
                repeat = true;
            }

        } catch (InputMismatchException e) {
            System.out.println("Cannot be empty! Please, select again ");
            isTaskRepeat();
        }
        return repeat;
    }

    public String getTimeTask(String data) {
        System.out.println("Task " + data + " Date and Time: (yyyy-MM-dd HH:mm)");
        String time = scanner.nextLine();
        if (time.isEmpty()) getTimeTask(data);
        return time;
    }

    public int getInterval() {
        int intervalTask = 0;
        System.out.println("Task interval in minutes");
        String interval = scanner.nextLine();
        try {
            intervalTask = Integer.parseInt(interval);
        } catch (NumberFormatException e) {
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

            if (temp.isEmpty()) throw new InputMismatchException();
            if (temp.equals("1")) {
                active = true;
            }

        } catch (InputMismatchException e) {
            System.out.println("Cannot be empty! Please, select again ");
            isTaskActive();
        }
        return active;
    }

    @Override
    public int show(String text) {
        int userChoice = 0;
        System.out.println(text);
        System.out.println("Save this task?\n1. Yes\n2. No ");
        try {
            userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice < 1 || userChoice > 2) throw new InputMismatchException();
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("Please, enter a correct number!");
            this.show(text);
        }
        return userChoice;
    }
}
