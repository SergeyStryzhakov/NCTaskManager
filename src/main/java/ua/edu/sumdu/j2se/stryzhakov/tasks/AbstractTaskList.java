package ua.edu.sumdu.j2se.stryzhakov.tasks;

public abstract class AbstractTaskList implements Iterable<Task> {

    abstract void add(Task task);

    abstract boolean remove(Task task);

    abstract int size();

    abstract Task getTask(int index);

    abstract ListTypes.types getType();

    /**
     * Create new list of tasks with specific condition
     *
     * @param from Start of time interval
     * @param to   End of time interval
     * @return List of the active tasks in specific time interval
     * @throws IllegalArgumentException if time less 0
     */
    public AbstractTaskList incoming(int from, int to) throws IllegalArgumentException {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("Time cannot be negative");
        }

        AbstractTaskList taskList = TaskListFactory.createTaskList(this.getType());
        for (int i = 0; i < size(); i++) {
            Task current = getTask(i);
            if (current.nextTimeAfter(from) != -1 &&
                    current.nextTimeAfter(from) < to) {
                taskList.add(current);
            }
            i++;
        }
        return taskList;
    }
}


