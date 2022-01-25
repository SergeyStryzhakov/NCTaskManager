package ua.edu.sumdu.j2se.stryzhakov.tasks.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Model;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.Viewable;

/**
 * This class starts when app is started.
 * Depending on user choice set action for factory.
 */
public class MainController implements Controller {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(MainController.class);
    private final Viewable view;
    private final Model model;

    public MainController(final Viewable view, final Model model) {
        this.view = view;
        this.model = model;
        LOGGER.info("Start Main Controller");
    }

    @Override
    public void start() {
        if(!model.isNotificationStarted()){
            ControllerFactory.selectController(Action.NOTIFICATOR).start();
        }
        LOGGER.info("Notification is already started");
        int userChoice = view.show("");
        Action action = Action.MAIN;
        switch (userChoice) {
            case 1:
                action = Action.SHOW;
                break;
            case 2:
                action = Action.CHANGE;
                break;
            case 3:
                action = Action.CALENDAR;
                break;
            case 4:
                if (model.isChanged()) {
                    action = Action.SAVE;
                } else {
                    LOGGER.info("Exit");
                    System.exit(0);
                }
                break;
        }
        ControllerFactory.selectController(action).start();
    }

}




