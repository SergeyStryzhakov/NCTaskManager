package ua.edu.sumdu.j2se.stryzhakov.tasks.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * View for MainController, start screen.
 */
public class MainView implements Viewable {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(MainView.class);

    @Override
    public int show(final String text) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;
        System.out.println("\n***************"
                + " Select item and press Enter"
                + " ***************\n");
        System.out.println("1: Show all tasks");
        System.out.println("2: Add new task");
        System.out.println("3: Show calendar "
                + "(You must enter start and finish date)");
        System.out.println("4: Exit");

        try {
            userChoice = Integer.parseInt(scanner.nextLine());
            LOGGER.info("User choice is {}", userChoice);
            if (userChoice < 1 || userChoice > 4) {
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
