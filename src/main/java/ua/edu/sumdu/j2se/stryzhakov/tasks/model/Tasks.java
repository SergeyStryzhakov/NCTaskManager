package ua.edu.sumdu.j2se.stryzhakov.tasks.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {
    private static final Logger LOGGER = LoggerFactory.getLogger(Tasks.class);
    private final static SortedMap<LocalDateTime, Set<Task>> calendar = new TreeMap<>();

    /**
     * Incoming method accept any collection for tasks
     *
     * @param tasks Collection tasks
     * @param start Start time
     * @param end   End time
     * @return Tasks
     */
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task currentTask = iterator.next();
            if (currentTask.nextTimeAfter(start) != null &&
                    (currentTask.nextTimeAfter(start).isBefore(end) ||
                            currentTask.nextTimeAfter(start).equals(end))) {
                continue;
            }
            iterator.remove();
        }
        return tasks;
    }

    /**
     * This method builds a sorted map of tasks which will be started in some interval
     *
     * @param tasks Collection
     * @param start Start time interval
     * @param end   End time interval
     * @return Sorted map of tasks
     */
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        LOGGER.info("Create calendar from " + start
                + " to " + end);
        for (Task task : tasks) {
            LocalDateTime startTime = start;
            if (task == null || !task.isActive()) continue;
            if (task.isRepeated()) {
                while (startTime.isBefore(end)) {
                    if (task.nextTimeAfter(startTime) == null) break;
                    addTaskToCalendar(task, task.nextTimeAfter(startTime));
                    startTime = startTime.plusSeconds(task.getRepeatInterval());
                }
            } else {
                if (task.nextTimeAfter(start) != null &&
                        (task.nextTimeAfter(start).isBefore(end) ||
                                task.nextTimeAfter(start).equals(end))) {
                    addTaskToCalendar(task, task.getTime());
                }
            }

        }
        return calendar;
    }

    /**
     * Add task in calendar. If key not exist - create new, else - add task into existing key
     *
     * @param task Task for add
     * @param date Key in calendar
     */
    private static void addTaskToCalendar(Task task, LocalDateTime date) {
        Set<Task> setTasks = new HashSet<>();
        if (calendar.containsKey(date)) {
            calendar.get(date).add(task);

        } else {
            setTasks.add(task);
            calendar.put(date, setTasks);
        }
    }
}
