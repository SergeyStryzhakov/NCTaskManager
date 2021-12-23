package ua.edu.sumdu.j2se.stryzhakov.tasks.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CalendarView implements Viewable {
    private final Scanner scanner = new Scanner(System.in);
    private int maxUserChoice;

    public void setMaxUserChoice(int maxUserChoice) {
        this.maxUserChoice = maxUserChoice;
    }

    public String getDate(String period) {
        System.out.println("Enter date of " + period + " calendar in format (yyyy-MM-dd HH:mm)");
        return scanner.nextLine();
    }

    @Override
    public int show(String text) {
        int userChoice = 0;
        if (text.isEmpty()) {
            maxUserChoice = 4;
            System.out.println("---------------Select date for show calendar---------------");
            System.out.println("1. For the next week");
            System.out.println("2. User date");
            System.out.println("3. Main menu");
            System.out.println("4. Exit");
        } else {
            System.out.println(text);
            System.out.println("1. Main menu");
            System.out.println("2. Exit");
        }
        try {
            userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice < 1 || userChoice > maxUserChoice) throw new InputMismatchException();
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("Please, enter a correct number!");
            this.show(text);
        }
        return userChoice;
    }
}
