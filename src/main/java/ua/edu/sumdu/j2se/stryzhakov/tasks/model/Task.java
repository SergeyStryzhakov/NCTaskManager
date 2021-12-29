package ua.edu.sumdu.j2se.stryzhakov.tasks.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task implements Cloneable, Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);
    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean active;
    private boolean repeated;

    public void setRepeated(boolean repeated) {
        this.repeated = repeated;
    }

    /**
     * Create a non-repeated task.
     *
     * @param title Title of the task
     * @param time  Time to run the task
     * @throws IllegalArgumentException if title equals null or time < 0
     */
    public Task(String title, LocalDateTime time)
                throws IllegalArgumentException {
        if (title.isEmpty() || time == null) {
            throw new IllegalArgumentException("The title and the "
                    + "time cannot be empty");
        }
        LOGGER.info("Single task was created");
        this.title = title;
        this.time = time;
    }

    /**
     * Create a repeated task with specific parameters.
     *
     * @param title    Title of the task
     * @param start    Start time of new task
     * @param end      The end of task
     * @param interval How often the task is been repeated.
     * @throws IllegalArgumentException if title equals null or interval < 0
     */
    public Task(String title,
                LocalDateTime start,
                LocalDateTime end,
                int interval) throws IllegalArgumentException {
        if (title.isEmpty() || interval < 0) {
            throw new IllegalArgumentException(
                    "The title cannot be empty and " +
                            "the interval cannot be a negative");
        }
        LOGGER.info("Repeat task was created");
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IllegalArgumentException {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("The title cannot be empty");
        }
        this.title = title;
    }

    /**
     * @return If the task is repeated then return time of the first run
     * else - time of start
     */
    public LocalDateTime getTime() {
        return repeated ? start : time;
    }

    public void setTime(LocalDateTime time) throws IllegalArgumentException {
        if (time == null) {
            throw new IllegalArgumentException("The time cannot be a negative");
        }
        this.time = time;
        this.repeated = false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return If the task is repeated then return time of the first run
     * else - time of start
     */
    public LocalDateTime getStartTime() {
        return repeated ? start : time;
    }

    /**
     * @return If the task is repeated then return time of the last run
     * else - time of start
     */
    public LocalDateTime getEndTime() {
        return repeated ? end : time;
    }

    /**
     * @return If the task is repeated then return interval
     * else - return 0
     */
    public int getRepeatInterval() {
        return repeated ? interval : 0;
    }

    /**
     * Create repeated task from non-repeated.
     */
    public void setTime(LocalDateTime start, LocalDateTime end, int interval)
                                            throws IllegalArgumentException {
        if (interval < 0 || start == null || end == null) {
            throw new IllegalArgumentException(
                    "Time cannot be a negative");
        }
        this.repeated = true;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public boolean isRepeated() {
        return repeated;
    }

    /**
     * Check the task and return time when next task will be started
     * or -1 if task is not active.
     *
     * @param current Current time
     * @return Time when next task will be started
     * or -1 if task is not active.
     * @throws IllegalArgumentException if current < 0
     */
    public LocalDateTime nextTimeAfter(LocalDateTime current)
                            throws IllegalArgumentException {
        if (current == null) {
            throw new IllegalArgumentException(
                    "Current time cannot be a negative");
        }
        //if task is not active
        if (!active) {
            return null;
        }
        //if task is active and not repeated
        if (!repeated) {
            return current.isBefore(time) ? time : null;
        }
        //if task is active and repeated
        if (current.isBefore(start)) {
            return start;
        }
        if (current.isAfter(end)) {
            return null;
        }
        for (LocalDateTime i = start;
             i.isBefore(end) || i.equals(end);
             i = i.plusSeconds(interval)) {
            if (i.isAfter(current)) {
                return i;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return time == task.time &&
                start == task.start &&
                end == task.end &&
                interval == task.interval &&
                active == task.active &&
                repeated == task.repeated &&
                title.equals(task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, time, start, end,
                interval, active, repeated);
    }

    @Override
    public String toString() {
        String msg;
        if (!repeated) {
            msg = "Задача: " + title +
                    " Початок " + stringFromDate(time) +
                    " Активна: " + active;
        } else {
            msg = "Задача: " + title +
                    " Початок " + stringFromDate(start) +
                    " з інтервалом " + interval / 60 +
                    " хв. закінчення " + stringFromDate(end) +
                    ". Активна: " + active;
        }
        return msg;
    }

    private String stringFromDate(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
       return date.format(formatter);

    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        Task task = (Task) super.clone();
        task.title = this.title;
        return task;
    }
}

