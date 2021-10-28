package ua.edu.sumdu.j2se.stryzhakov.tasks;

public class Task {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repeated;

    /**
     * Create a non-repeated task
     *
     * @param title Title of the task
     * @param time  Time to run the task
     * @throws IllegalArgumentException if title equals null or time < 0
     */
    public Task(String title, int time) throws IllegalArgumentException {
        if (title == null || time < 0) {
            throw new IllegalArgumentException("The title cannot be null and the time cannot be a negative");
        }
        this.title = title;
        this.time = time;
    }

    /**
     * Create a repeated task with specific parameters
     *
     * @param title    Title of the task
     * @param start    Start time of new task
     * @param end      The end of task
     * @param interval How often the task is been repeated.
     * @throws IllegalArgumentException if title equals null or interval < 0
     */
    public Task(String title, int start, int end, int interval) throws IllegalArgumentException {
        if (title == null || interval < 0) {
            throw new IllegalArgumentException("The title cannot be null and the interval cannot be a negative");
        }
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
        if (title == null) {
            throw new IllegalArgumentException("The title cannot be null");
        }
        this.title = title;
    }

    /**
     * @return If the task is repeated then return time of the first run
     * else - time of start
     */
    public int getTime() {
        return repeated ? start : time;
    }

    public void setTime(int time) throws IllegalArgumentException {
        if (time < 0) {
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
    public int getStartTime() {
        return repeated ? start : time;
    }

    /**
     * @return If the task is repeated then return time of the last run
     * else - time of start
     */
    public int getEndTime() {
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
     * Create repeated task from non-repeated
     */
    public void setTime(int start, int end, int interval) throws IllegalArgumentException {
        if (interval < 0 || start < 0 || end < 0) {
            throw new IllegalArgumentException("Time cannot be a negative");
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
    public int nextTimeAfter(int current) throws IllegalArgumentException {
        if (current < 0) {
            throw new IllegalArgumentException("Current time cannot be a negative");
        }
        //if task is not active
        if (!active) return -1;
        //if task is active and not repeated
        if (!repeated) {
            if (current < time) return time;
            return -1;
        }
        //if task is active and repeated
        if (current < start) return start;
        if (current > end) return -1;
        for (int i = start; i <= end; i += interval) {
            if (i > current) {
                return i;
            }
        }
        return -1;
    }
}

