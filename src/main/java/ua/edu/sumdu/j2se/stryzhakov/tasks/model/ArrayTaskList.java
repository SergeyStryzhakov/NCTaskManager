package ua.edu.sumdu.j2se.stryzhakov.tasks.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class ArrayTaskList extends AbstractTaskList implements Cloneable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArrayTaskList.class);
    private final int START_CAPACITY = 10;
    private Task[] tasks = new Task[START_CAPACITY];
    private int index = 0;

    /**
     * Add new task in the array.
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
        LOGGER.info("Add new task");
        tasks[index] = task;
        index++;
    }

    /**
     * Remove specific task and change array size.
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
        if (tasks.length == 0) {
            return false;
        }
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i].equals(task)) {
                LOGGER.info("Remove task");
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
     * Return size of the array without null.
     */
    @Override
    public int size() {
        int count = 0;
        for (Task task : tasks) {
            if (task != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Get the task with specific index.
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
        LOGGER.info("Get task from list with index " + index);
        return tasks[index];
    }

    /**
     * Return type of list which use in the class
     * for method "incoming" in abstract class.
     */
    @Override
    public ListTypes.types getType() {
        return ListTypes.types.ARRAY;
    }

    /**
     * Transform ArrayTaskList to stream.
     *
     * @return Stream of array
     */
    @Override
    public Stream<Task> getStream() {
        return Arrays.stream(this.tasks);
    }


    @Override
    public Iterator<Task> iterator() {
        LOGGER.info("Create new iterator");
        return new ArrayTaskListIterator(this);
    }


    /**
     * Iterator for ArrayTaskList class.
     */
    static class ArrayTaskListIterator implements Iterator<Task> {
        private final ArrayTaskList arrayTaskList;
        Task temp;
        private int count = 0;

        ArrayTaskListIterator(ArrayTaskList arrayTaskList) {
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
            if (temp == null) {
                throw new IllegalStateException("Call remove without next!");
            }
            arrayTaskList.remove(temp);
            count--;

        }
    }

    @Override
    public ArrayTaskList clone() throws CloneNotSupportedException {
        LOGGER.info("Clone list");
        ArrayTaskList list = (ArrayTaskList) super.clone();
        list.tasks = Arrays.copyOf(this.tasks, this.tasks.length);
        return list;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(tasks);

    }

    @Override
    public boolean equals(final Object o) {
        return super.equals(o);
    }
}
