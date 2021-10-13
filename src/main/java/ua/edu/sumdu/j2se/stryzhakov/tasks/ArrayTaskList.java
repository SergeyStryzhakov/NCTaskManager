package ua.edu.sumdu.j2se.stryzhakov.tasks;

import java.util.Arrays;

public class ArrayTaskList {
    private Task[] tasks = new Task[0];

    /**
     * Add new task in the array
     *
     * @param task for add
     */
    public void add(Task task) {
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = task;
    }

    /**
     * Remove specific task and change array size
     *
     * @param task for remove
     * @return if task is not find return false, else - true
     */

    public boolean remove(Task task) {
        if (tasks.length == 0) return false;
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i].equals(task)) {
                tasks[i] = null;
                //Shift the array after removing element
                Task[] temp = new Task[tasks.length - 1];
                System.arraycopy(tasks, 0, temp, 0, i);
                System.arraycopy(tasks, i + 1, temp, i, tasks.length - i - 1);
                tasks = temp;
                return true;
            }
        }
        return false;
    }

    /**
     * Return size of the array
     */
    public int size() {
        return tasks.length;
    }

    /**
     * Get the task with specific index
     *
     * @param index of the task
     * @return The task with specific index,
     * if task not found - return null
     */
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.length) {
            return tasks[index];
        }
        return null;
    }

    /**
     * Create new array of tasks with specific condition
     *
     * @param from Start of time interval
     * @param to   End of time interval
     * @return List of the active tasks with specific condition
     */
    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList taskList = new ArrayTaskList();

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
