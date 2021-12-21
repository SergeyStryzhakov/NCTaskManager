package ua.edu.sumdu.j2se.stryzhakov.tasks.view;

import java.util.Scanner;

public class ShowView implements Viewable {
    private String text;


    @Override
    public int show(String text) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;
        this.text = text;
        System.out.println(text);
        try {
            userChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please, enter a correct number!");
            this.show(this.text);
        }
        return userChoice;
    }
}
