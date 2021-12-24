package ua.edu.sumdu.j2se.stryzhakov.tasks.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.stryzhakov.tasks.Main;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Model;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.Viewable;

public class MainController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final Viewable view;
    private Model model;

    public MainController(Viewable view, Model model) {
        this.view = view;
        this.model = model;
        logger.info("Start Main Controller");
    }

    @Override
    public void start() {
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
                   logger.info("Exit");
                    System.exit(0);
                }
                break;
        }
        ControllerFactory.selectController(action).start();
    }

}




