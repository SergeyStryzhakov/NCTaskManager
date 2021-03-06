package ua.edu.sumdu.j2se.stryzhakov.tasks.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * View for CalendarController.
 */
public class CalendarView implements Viewable {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(CalendarView.class);
    private final Scanner scanner = new Scanner(System.in);
    private int maxUserChoice;

    public void setMaxUserChoice(final int maxUserChoice) {
        this.maxUserChoice = maxUserChoice;
    }

    public String getDate(final String period) {
        System.out.println("Enter date of " + period
                + " calendar in format (yyyy-MM-dd HH:mm)");
        String temp = scanner.nextLine();
        LOGGER.info("User data for {} is {} ", period, temp);
        return temp;
    }

    @Override
    public int show(final String text) {
        int userChoice = 0;
        if (text.isEmpty()) {
            maxUserChoice = 4;
            System.out.println("---------------"
                    + "Select date for show calendar"
                    + "---------------");
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
            LOGGER.info("User choice is {} ", userChoice);
            if (userChoice < 1 || userChoice > maxUserChoice) {
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
