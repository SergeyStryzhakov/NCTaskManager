package ua.edu.sumdu.j2se.stryzhakov.tasks.controller;

import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Model;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.*;

/**
 * Factory return controller depending on user action.
 */
public class ControllerFactory {

    public static Controller selectController(Action action) {
        switch (action) {
            case CHANGE:
                return new ChangeTaskController(new ChangeTaskView(),
                        Model.getInstance());
            case CALENDAR:
                return new CalendarController(new CalendarView(),
                        Model.getInstance());
            case SHOW:
                return new ShowController(new ShowView(),
                        Model.getInstance());
            case SAVE:
                return new SaveController(new SaveView(),
                        Model.getInstance());
            default:
                return new MainController(new MainView(),
                        Model.getInstance());
        }
    }
}
