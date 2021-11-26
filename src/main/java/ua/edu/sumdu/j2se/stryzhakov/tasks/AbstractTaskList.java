package ua.edu.sumdu.j2se.stryzhakov.tasks;

import java.util.Objects;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public abstract ListTypes.types getType();

    public abstract Stream<Task> getStream();

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Create new list of tasks with specific condition
     *
     * @param from Start of time interval
     * @param to   End of time interval
     * @return List of the active tasks in specific time interval
     * @throws IllegalArgumentException if time less 0
     */
    public final AbstractTaskList incoming(int from, int to) throws IllegalArgumentException {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("Time cannot be negative");
        }

        AbstractTaskList taskList = TaskListFactory.createTaskList(this.getType());
        this.getStream().filter(Objects::nonNull)
                .filter((t) -> (t.nextTimeAfter(from) != -1 &&
                        t.nextTimeAfter(from) < to))
                .forEach(taskList::add);
//
//        for (int i = 0; i < size(); i++) {
//            Task current = getTask(i);
//            if (current.nextTimeAfter(from) != -1 &&
//                    current.nextTimeAfter(from) < to) {
//                taskList.add(current);
//            }
//            i++;
//        }
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTaskList list = (AbstractTaskList) o;
        if (this.size() != list.size()) return false;
        for (int i = 0; i < this.size(); i++) {
            if (!this.getTask(i).equals(list.getTask(i))) return false;
        }
        return true;
    }


}


