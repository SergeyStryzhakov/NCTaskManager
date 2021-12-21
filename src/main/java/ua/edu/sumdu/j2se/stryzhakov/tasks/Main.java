package ua.edu.sumdu.j2se.stryzhakov.tasks;

import ua.edu.sumdu.j2se.stryzhakov.tasks.controller.Action;
import ua.edu.sumdu.j2se.stryzhakov.tasks.controller.Controller;
import ua.edu.sumdu.j2se.stryzhakov.tasks.controller.ControllerFactory;


public class Main {

    public static void main(String[] args) {
        Controller startController = ControllerFactory.selectController(Action.MAIN);
        startController.start();
   }
}
