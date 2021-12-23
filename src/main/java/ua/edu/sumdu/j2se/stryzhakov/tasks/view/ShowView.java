package ua.edu.sumdu.j2se.stryzhakov.tasks.view;

import java.util.Scanner;

public class ShowView implements Viewable {


    @Override
    public int show(String text) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;
        System.out.println("\n*************** Select item for edit or remove and press Enter ***************\n");
        System.out.println(text);
        try {
            userChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please, enter a correct number!");
            this.show(text);
        }
        return userChoice;
    }
}
