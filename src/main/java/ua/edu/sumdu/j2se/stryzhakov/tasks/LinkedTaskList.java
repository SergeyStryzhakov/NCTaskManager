package ua.edu.sumdu.j2se.stryzhakov.tasks;

public class LinkedTaskList {
    private Element head;

    /**
     * Class create the node of the linked list
     */
    static class Element {
        private Task task;
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
     * Create new list of tasks with specific condition
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
        Element currentElement = head;
        while (currentElement != null) {
            if (!currentElement.task.isActive()) {
                currentElement = currentElement.next;
                continue;
            }
            if (!currentElement.task.isRepeated() &&
                    currentElement.task.getTime() > from &&
                    currentElement.task.getTime() < to) {
                taskList.add(currentElement.task);
            }

            if (currentElement.task.isRepeated() &&
                    currentElement.task.nextTimeAfter(from) != -1 &&
                    currentElement.task.nextTimeAfter(from) < to) {
                taskList.add(currentElement.task);
            }
            currentElement = currentElement.next;

        }
        return taskList;
    }
}
