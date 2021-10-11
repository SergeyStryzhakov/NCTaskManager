package ua.edu.sumdu.j2se.stryzhakov.tasks;

import java.util.Arrays;

public class ArrayTaskList {
    private Task[] tasks = new Task[5];
    private int indexArray = 0;

    public void addTask(Task task) {
        if (indexArray == tasks.length) {
            tasks = Arrays.copyOf(tasks, tasks.length + 5);
        }
        tasks[indexArray] = task;
        indexArray++;
    }

    public boolean removeTask(Task task) {
        if (tasks.length == 0) return false;
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i].equals(task)) {
                tasks[i] = null;
                indexArray--;
                Task[] temp = new Task[tasks.length - i];
                System.arraycopy(tasks, i, temp, 0, tasks.length - i);
                System.arraycopy(temp, 1, tasks, i, temp.length - 1);
                System.out.println(Arrays.toString(tasks));
                return true;
            }
        }
        return false;
    }

    public int size() {
        return tasks.length;
    }

    public Task getTask(int index) {
        if (index >= 0 && index < tasks.length) {
            return tasks[index];
        }
        return null;
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList taskList = new ArrayTaskList();

        return taskList;
    }
}
