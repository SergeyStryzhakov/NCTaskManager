package ua.edu.sumdu.j2se.stryzhakov.tasks;

import java.util.Arrays;

public class LinkedTaskList {

    private Task[] tasks = new Task[10];
    private int index = 0;

    /**
     * Add new task in the array
     *
     * @param task for add
     * @throws NullPointerException if task equals null
     */
    public void add(Task task) throws NullPointerException {
        if (task == null) {
            throw new NullPointerException("The task cannot be null");
        }
        if (index == tasks.length) {
            tasks = Arrays.copyOf(tasks, tasks.length * 2);
        }
        tasks[index] = task;
        index++;
    }

    /**
     * Remove specific task and change array size
     *
     * @param task for remove
     * @return if task is not find - return false, else - return true
     * @throws NullPointerException if task equals null
     */

    public boolean remove(Task task) throws NullPointerException {
        if (task == null) {
            throw new NullPointerException("The task cannot be null");
        }
        if (tasks.length == 0) return false;
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i].equals(task)) {
                tasks[i] = null;
                //Shift the array after removing element
                Task[] temp = new Task[tasks.length - 1];
                System.arraycopy(tasks, 0, temp, 0, i);
                System.arraycopy(tasks, i + 1, temp, i, tasks.length - i - 1);
                tasks = temp;
                index--;
                return true;
            }
        }
        return false;
    }

    /**
     * Return size of the array without null
     */
    public int size() {
        int count = 0;
        for (Task task : tasks) {
            if (task != null) count++;
        }
        return count;
    }

    /**
     * Get the task with specific index
     *
     * @param index of the task
     * @return The task with specific index,
     * @throws IndexOutOfBoundsException if index out of bounds
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.length) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return tasks[index];
    }

    /**
     * Create new array of tasks with specific condition
     *
     * @param from Start of time interval
     * @param to   End of time interval
     * @return List of the active tasks with specific condition
     */
    public LinkedTaskList incoming(int from, int to) throws IllegalArgumentException {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("Time cannot be negative");
        }
        LinkedTaskList taskList = new LinkedTaskList();
        for (Task task : tasks) {
            if (task == null || !task.isActive()) continue;
            if (!task.isRepeated() &&
                    task.getTime() > from &&
                    task.getTime() < to) {
                taskList.add(task);
            }
            if (task.isRepeated() &&
                    task.nextTimeAfter(from) != -1 &&
                    task.nextTimeAfter(from) < to) {
                taskList.add(task);
            }
        }
        return taskList;
    }
}
