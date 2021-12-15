package ua.edu.sumdu.j2se.stryzhakov.tasks.controller;

import ua.edu.sumdu.j2se.stryzhakov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Model;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.TasksView;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.View;

import java.io.File;

public class TasksController extends Controller {
    View view;
    Model model;

    public TasksController(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    private AbstractTaskList getTasks() {
        return model.getList();

    }

    @Override
    public void start() {
        if (view instanceof TasksView) {
            ((TasksView) view).setList(getTasks());
        } else throw new IllegalArgumentException("Wrong view!");

        view.show();
        System.out.println("Test");
    }

}
