package ua.edu.sumdu.j2se.stryzhakov.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.stryzhakov.tasks.controller.Action;
import ua.edu.sumdu.j2se.stryzhakov.tasks.controller.Controller;
import ua.edu.sumdu.j2se.stryzhakov.tasks.controller.ControllerFactory;


public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Start app");
        Controller startController = ControllerFactory.selectController(Action.MAIN);
        startController.start();
    }
}
