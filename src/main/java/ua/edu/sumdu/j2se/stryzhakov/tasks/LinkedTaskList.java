package ua.edu.sumdu.j2se.stryzhakov.tasks;

import java.util.Arrays;
import java.util.Iterator;

import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList implements Cloneable {
    private Element head;

    /**
     * Class create the node of the linked list
     */
    static class Element {
        private final Task task;
        private Element next;
        private Element prev;

        public Element(Task task) {
            this.task = task;
            next = null;
            prev = null;
        }
    }

    /**
     * Add new task in the list
     *
     * @param task for add
     * @throws NullPointerException if task equals null
     */
    @Override
    public void add(Task task) throws NullPointerException {
        if (task == null) {
            throw new NullPointerException("The task cannot be null");
        }
        Element newElement = new Element(task);
        if (head != null) {
            newElement.next = head;
            head.prev = newElement;
        }
        head = newElement;

    }

    /**
     * Remove specific task
     *
     * @param task for remove
     * @return if task is not find - return false, else - return true
     * @throws NullPointerException if task equals null
     */
    @Override
    public boolean remove(Task task) throws NullPointerException {
        if (task == null) {
            throw new NullPointerException("The task cannot be null");
        }
        Element currentElement = head;
        Element previousElement = null;
        while (currentElement != null) {
            if (currentElement.task.equals(task)) {
                if (currentElement.equals(head)) {
                    head = currentElement.next;
                    return true;
                }
                previousElement.next.prev = currentElement.prev;
                previousElement.next = currentElement.next;

                return true;
            }
            previousElement = currentElement;
            currentElement = currentElement.next;
        }
        return false;
    }

    /**
     * Return size of the list
     */
    @Override
    public int size() {
        int count = 0;
        Element currentElement = head;
        if (currentElement == null) return count;
        while (currentElement != null) {
            count++;
            currentElement = currentElement.next;

        }
        return count;
    }

    /**
     * Get the task with specific index
     *
     * @param index of the task
     * @return The task with specific index,
     * @throws IndexOutOfBoundsException if index out of bounds
     * @throws IllegalArgumentException  if list is empty
     */
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (head == null) {
            throw new IllegalArgumentException("List is empty.");
        }
        Element currentElement = head;
        int countElement = 0;

        while (currentElement != null) {
            if (countElement == index) {
                return currentElement.task;
            }
            currentElement = currentElement.next;
            countElement++;
        }
        return null;
    }

    /**
     * Return type of list which use in the class
     * for method "incoming" in abstract class
     */
    @Override
    public ListTypes.types getType() {
        return ListTypes.types.LINKED;
    }

    /**
     * Transform list to stream
     *
     * @return Stream from LinkedTaskList
     */
    @Override
    public Stream<Task> getStream() {

        Task[] tasks = new Task[this.size()];
        for (int i = 0; i < this.size(); i++) {
            tasks[i] = this.getTask(i);
        }
        return Arrays.stream(tasks);
    }

    @Override
    public Iterator<Task> iterator() {
        return new LinkedListIterator(this);
    }


    static class LinkedListIterator implements Iterator<Task> {
        private final LinkedTaskList list;
        private Element current;
        private int count;

        public LinkedListIterator(LinkedTaskList list) {
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return count < list.size();
        }

        @Override
        public Task next() {
            count++;
            if (current == null) {
                current = list.head;
            } else {
                current = current.next;
            }
            return current.task;
        }

        @Override
        public void remove() throws IllegalStateException {
            if (current == null) throw new IllegalStateException("Call remove without next!");
            list.remove(current.task);
            count--;
        }
    }

    @Override
    public LinkedTaskList clone() throws CloneNotSupportedException {
        LinkedTaskList list = (LinkedTaskList) super.clone();
        list.head = null;
        for (int i = this.size() - 1; i >= 0; i--) {
            list.add(this.getTask(i).clone());
        }
        return list;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (Task task : this) {
            hash += task.hashCode();
        }
        return hash;
    }


}
