package ua.edu.sumdu.j2se.stryzhakov.tasks.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SaveView implements Viewable {
    private static final Logger logger = LoggerFactory.getLogger(SaveView.class);

    @Override
    public int show(String text) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;
        System.out.println("Your tasks are changed. Do you want to save changes?\n");
        System.out.println(
                "1. Save and return to main menu\n" +
                        "2. Save and exit\n" +
                        "3. Exit without saving");
        try {
            userChoice = Integer.parseInt(scanner.nextLine());
            logger.info("User choice is {}", userChoice);
            if (userChoice < 1 || userChoice > 3) throw new InputMismatchException();
        } catch (NumberFormatException | InputMismatchException e) {
            logger.error(e.getMessage());
            System.out.println("Please, enter a correct number!");
            this.show(text);
        }
        return userChoice;
    }
}
