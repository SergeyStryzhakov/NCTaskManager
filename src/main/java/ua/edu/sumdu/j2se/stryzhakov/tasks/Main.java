package ua.edu.sumdu.j2se.stryzhakov.tasks;

public class Main {

    public static void main(String[] args) {
        int temp;
        int start = 10;
        int interval = 20;
        int end = 100;
        int current = 30;
        //int nextTime = current + ((current - start) % interval);
        //temp = start;
        for (int i = start; i <= end; i += interval) {
            if (i >= current && current < end) {
                System.out.println(i);
                break;
            }
            //temp = i;
        }

    }
}
