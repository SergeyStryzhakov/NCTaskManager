package ua.edu.sumdu.j2se.stryzhakov.tasks.controller;

import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Model;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.View;

public class MainController extends Controller {
    private final View view;
    private final Model model;

    public MainController(View view, Model model) {
        this.view = view;
        this.model = model;

    }

    public void start() {
        action = view.show();
        ActionController.startController(action).start();

    }


}

