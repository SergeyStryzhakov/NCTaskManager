package ua.edu.sumdu.j2se.stryzhakov.tasks;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {

        AbstractTaskList arrayList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        AbstractTaskList list1 = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        Task walk = new Task("Walk", LocalDateTime.now().minusDays(1));
        Task running = new Task("Running", LocalDateTime.now().minusDays(1));
        Task homework = new Task("Homework", LocalDateTime.of(2021, 11, 23, 10, 0, 0),
                LocalDateTime.of(2021, 11, 30, 12, 0, 0), 3600);
        Task sportExercise = new Task("Sport", LocalDateTime.now(), LocalDateTime.now().plusDays(45), 450000);
        Task newSportExercise;
        newSportExercise = sportExercise.clone();
        newSportExercise.setTitle("Super sport");
        walk.setActive(true);
        homework.setActive(true);
        sportExercise.setActive(true);
        newSportExercise.setActive(true);
        running.setActive(true);
        arrayList.add(running);
        arrayList.add(walk);
        arrayList.add(homework);
        arrayList.add(sportExercise);
        arrayList.add(newSportExercise);
        System.out.println("Walk before setTime: " + walk);
        //walk.setTime(LocalDateTime.now());
        System.out.println("Walk after setTime: " + walk);
        Thread.sleep(1000);
        System.out.println("Walk after setTime and sleep: " + walk);
        for (Task task : arrayList) {
            System.out.println("Before incoming: " + task);
        }
        LocalDateTime start = LocalDateTime.now().minusDays(2);
        LocalDateTime end = LocalDateTime.now().plusDays(2);
        System.out.println("========================================================");
        System.out.println(Tasks.calendar(arrayList, start,end));
//        for (LocalDateTime dt : Tasks.calendar(arrayList, start,end).keySet()) {
//            System.out.println(dt);
//        }
        Iterable<Task> newList = Tasks.incoming(arrayList, start, end);
        for (Task task : newList) {
            System.out.println("After incoming in tasks: " + task);
        }
        AbstractTaskList newListfromAbstract = arrayList.incoming(start, end);
        for (Task task : newListfromAbstract) {
            System.out.println("After incoming in AbstractList: " + task);
        }

        System.out.println("stop");


    }
}
