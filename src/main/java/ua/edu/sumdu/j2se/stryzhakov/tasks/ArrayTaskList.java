package ua.edu.sumdu.j2se.stryzhakov.tasks;

import java.util.Arrays;

public class ArrayTaskList {
    private Task[] tasks = new Task[0];


    public void add(Task task) {
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = task;
    }

    public boolean remove(Task task) {
        if (tasks.length == 0) return false;
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i].equals(task)) {
                tasks[i] = null;
                Task[] temp = new Task[tasks.length - 1];
                System.arraycopy(tasks, 0, temp, 0, i);
                System.arraycopy(tasks, i + 1, temp, i, tasks.length - i - 1);
                tasks = temp;
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
        System.out.println("Incoming array: ");
        System.out.println(Arrays.toString(taskList.tasks));
        return taskList;
    }
}
