package ua.edu.sumdu.j2se.stryzhakov.tasks.controller;

import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Model;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.Viewable;

public class MainController implements Controller {
    private final Viewable view;
    private Model model;

    public MainController(Viewable view, Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void start() {
        int userChoice = view.show("");
        Action action;
        switch (userChoice) {
            case 1:
                action = Action.CHANGE;
                break;
            case 2:
                action = Action.SHOW;
                break;
            case 3:
                action = Action.CALENDAR;
                break;
            case 4:
                System.exit(0);
            default:
                action = Action.MAIN;
        }
        ControllerFactory.selectController(action).start();
    }

}




