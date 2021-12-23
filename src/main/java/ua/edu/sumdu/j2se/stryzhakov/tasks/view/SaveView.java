package ua.edu.sumdu.j2se.stryzhakov.tasks.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SaveView implements Viewable {
    @Override
    public int show(String text) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;
        System.out.println("Your tasks was changed. Do you want to save changes?\n");
        System.out.println(
                        "1. Save and return to main menu\n" +
                        "2. Save and exit\n" +
                        "3. Exit without saving");
        try {
            userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice < 1 || userChoice > 3) throw new InputMismatchException();
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("Please, enter a correct number!");
            this.show(text);
        }
        return userChoice;
    }
}
