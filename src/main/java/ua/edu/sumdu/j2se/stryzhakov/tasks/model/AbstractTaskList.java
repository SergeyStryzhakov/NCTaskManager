package ua.edu.sumdu.j2se.stryzhakov.tasks.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Serializable {

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public abstract ListTypes.types getType();

    public abstract Stream<Task> getStream();


    /**
     * Create new list of tasks with specific condition.
     *
     * @param start Start of time interval
     * @param end   End of time interval
     * @return List of the active tasks in specific time interval
     * @throws IllegalArgumentException if time less 0
     */
    public final AbstractTaskList incoming(LocalDateTime start,
                                           LocalDateTime end)
            throws IllegalArgumentException {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Time cannot be negative");
        }

        AbstractTaskList taskList =
                TaskListFactory.createTaskList(this.getType());

        this.getStream().filter(Objects::nonNull)
                .filter((t) -> (t.nextTimeAfter(start) != null &&
                        (t.nextTimeAfter(start).isBefore(end) ||
                                t.nextTimeAfter(start).equals(end))))
                .forEach(taskList::add);

        return taskList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Task temp : this) {
            builder.append(temp.toString());
            builder.append("\n");
        }
        return builder.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractTaskList list = (AbstractTaskList) o;
        if (this.size() != list.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (!this.getTask(i).equals(list.getTask(i))) {
                return false;
            }
        }
        return true;
    }


}


