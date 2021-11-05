package ua.edu.sumdu.j2se.stryzhakov.tasks;

public class TaskListFactory {

    public static AbstractTaskList createTaskList(ListTypes.types types) {
        switch (types) {
            case LINKED:
                return new LinkedTaskList();
            case ARRAY:
            default:
                return new ArrayTaskList();
        }

    }
}