package ua.edu.sumdu.j2se.stryzhakov.tasks.view;

import ua.edu.sumdu.j2se.stryzhakov.tasks.controller.Action;
import ua.edu.sumdu.j2se.stryzhakov.tasks.utils.Utils;

import java.util.Scanner;

public class MainView extends View{



    public Action show() {
        System.out.println("-----------Choose item and press Enter-------------------------");
        System.out.println("1: Show all tasks");
        System.out.println("2: Show calendar (You need to enter start and finish date)");
        System.out.println("3: Exit");
        int choose = checkUserChoose();
        if (choose < 1 || choose > 3) {
            System.out.println("Please, enter a correct number!");
            this.show();
        }
        switch (choose){
            case 1:
                return Action.SHOW;
            case 2:
                return Action.CALENDAR;
            case 3:
                System.exit(0);

        }
        return Action.SHOW;
    }
}
