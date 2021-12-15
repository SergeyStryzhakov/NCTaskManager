package ua.edu.sumdu.j2se.stryzhakov.tasks.view;

import ua.edu.sumdu.j2se.stryzhakov.tasks.controller.Action;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Task;

public class TasksView extends View {
    AbstractTaskList list;

    public void setList(AbstractTaskList list) {
        this.list = list;
    }

    public void createTableTasks(AbstractTaskList list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + " " + list.getTask(i).toString());
        }
    }

    @Override
    public Action show() {

        System.out.println("--------------------------------------------");
        createTableTasks(list);
        System.out.println("--------------------------------------------");
        System.out.println("Enter a number of task for edit or delete");
        int choose = checkUserChoose();
        if (choose < 0 || choose > list.size()) {
            System.out.println("Please, enter a correct number!");
            this.show();
        }

        return Action.SHOW;
    }
}
