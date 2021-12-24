package ua.edu.sumdu.j2se.stryzhakov.tasks.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ShowView implements Viewable {
    private static final Logger logger = LoggerFactory.getLogger(ShowView.class);

    @Override
    public int show(String text) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;
        System.out.println("\n*************** Select item for edit or remove and press Enter ***************\n");
        System.out.println(text);
        try {
            userChoice = Integer.parseInt(scanner.nextLine());
            logger.info("User select item {}", userChoice);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            System.out.println("Please, enter a correct number!");
            this.show(text);
        }
        return userChoice;
    }
}
