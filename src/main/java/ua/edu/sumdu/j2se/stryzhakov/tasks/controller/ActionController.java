package ua.edu.sumdu.j2se.stryzhakov.tasks.controller;

import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Model;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.MainView;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.TasksView;

public class ActionController {

    public static Controller startController(Action actions){
        switch (actions){
            case MAIN:
               return new MainController(new MainView(), Model.getInstanse());
            case SHOW:
                return new TasksController(new TasksView(), Model.getInstanse());
            default:
                return new MainController(new MainView(), Model.getInstanse());
        }
    }
}
