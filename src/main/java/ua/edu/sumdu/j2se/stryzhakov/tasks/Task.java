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
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return repeated ? start : time;
    }

    public void setTime(int time) {
        this.time = time;
        if(repeated) repeated = false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getStartTime () {
        return repeated ? start : time;
    }

    public int getEndTime() {
        return repeated ? end : time;
    }

    public int getRepeatInterval() {
        return repeated ? interval : 0;
    }

    private void setTime(int start, int end, int interval) {
        if(!repeated) repeated = true;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public boolean isRepeated() {
        return repeated;
    }


}
