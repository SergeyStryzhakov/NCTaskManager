package ua.edu.sumdu.j2se.stryzhakov.tasks.view;


import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView implements Viewable {

    @Override
    public int show(String text) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;
        System.out.println("-----------Choose item and press Enter-------------------------");
        System.out.println("1: Add new task");
        System.out.println("2: Show all tasks");
        System.out.println("3: Show calendar (You must enter start and finish date)");
        System.out.println("4: Exit");

        try {
            userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice < 1 || userChoice > 4) throw new InputMismatchException();
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("Please, enter a correct number!");
            this.show(text);
        }
        return userChoice;
    }

}
