package ua.edu.sumdu.j2se.stryzhakov.tasks;

public class LinkedTaskList extends AbstractTaskList {
    private Element head;

    /**
     * Class create the node of the linked list
     */
    static class Element {
        private final Task task;
        private Element next;

        public Element(Task task) {
            this.task = task;
            next = null;
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
            if (currentElement.task == task) {
                if (currentElement == head) {
                    head = currentElement.next;
                    return true;
                }
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
    ListTypes.types getType() {
        return ListTypes.types.LINKED;
    }

}
