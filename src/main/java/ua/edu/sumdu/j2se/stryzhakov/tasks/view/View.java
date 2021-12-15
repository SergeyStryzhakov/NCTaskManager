package ua.edu.sumdu.j2se.stryzhakov.tasks.view;

import ua.edu.sumdu.j2se.stryzhakov.tasks.controller.Action;
import ua.edu.sumdu.j2se.stryzhakov.tasks.utils.Utils;

import java.util.Scanner;

public abstract class View {

    private final Scanner scanner = new Scanner(System.in);

    public abstract Action show();


    public int checkUserChoose() {
        int option = -1;
        try {
            option = Integer.parseInt(scanner.nextLine());

        } catch (IllegalArgumentException e) {
            Utils.clearConsoleScreen();
            System.out.println("Please, enter a correct number!");
            this.show();
        }
       return option;
    }
}
