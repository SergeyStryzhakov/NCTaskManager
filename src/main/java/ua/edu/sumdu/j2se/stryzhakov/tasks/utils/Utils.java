package ua.edu.sumdu.j2se.stryzhakov.tasks.utils;

public  class Utils {
    public static void clearConsoleScreen() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

