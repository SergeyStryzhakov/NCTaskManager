package ua.edu.sumdu.j2se.stryzhakov.tasks;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ArrayTaskList extends AbstractTaskList {
    private Task[] tasks = new Task[10];
    private int index = 0;

    /**
     * Add new task in the array
     *
     * @param task for add
     * @throws NullPointerException if task equals null
     */
    @Override
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
     * @return if task is not find return false, else - true
     * @throws NullPointerException if task equals null
     */
    @Override
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
    @Override
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
     * @return The task with specific index
     * @throws IndexOutOfBoundsException if index out of bounds
     */
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.length) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return tasks[index];
    }

    /**
     * Return type of list which use in the class
     * for method "incoming" in abstract class
     */
    @Override
    ListTypes.types getType() {
        return ListTypes.types.ARRAY;
    }


    @NotNull
    @Override
    public Iterator<Task> iterator() {
        return new ArrayTaskListIterator(this);
    }

    @Override
    public void forEach(Consumer<? super Task> action) {
        super.forEach(action);
    }

    @Override
    public Spliterator<Task> spliterator() {
        return super.spliterator();
    }

    /**
     * Iterator for ArrayTaskList class
     */
    static class ArrayTaskListIterator implements Iterator<Task> {
        ArrayTaskList arrayTaskList;
        Task temp;
        private int count = 0;

        public ArrayTaskListIterator(ArrayTaskList arrayTaskList) {
            this.arrayTaskList = arrayTaskList;
        }

        @Override
        public boolean hasNext() {
            return count < arrayTaskList.size();
        }

        @Override
        public Task next() {
            temp = arrayTaskList.getTask(count++);
            return temp;
        }

        @Override
        public void remove() throws IllegalStateException {
            if (temp == null) throw new IllegalStateException("Call remove without next!");
            arrayTaskList.remove(temp);
            count--;

        }
    }

}
