package ua.edu.sumdu.j2se.stryzhakov.tasks;

public class Task {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repeated;

    public Task(String title, int time) {
        this.title = title;
        this.time = time;
    }

    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return
     */
    public int getTime() {
        return repeated ? start : time;
    }

    public void setTime(int time) {
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
     * @return
     */
    public int getStartTime() {
        return repeated ? start : time;
    }

    /**
     * @return
     */
    public int getEndTime() {
        return repeated ? end : time;
    }

    /**
     * @return
     */
    public int getRepeatInterval() {
        return repeated ? interval : 0;
    }

    /**
     * @param start
     * @param end
     * @param interval
     */
    public void setTime(int start, int end, int interval) {
        this.repeated = true;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public boolean isRepeated() {
        return repeated;
    }

    public int nextTimeAfter(int current) {

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

