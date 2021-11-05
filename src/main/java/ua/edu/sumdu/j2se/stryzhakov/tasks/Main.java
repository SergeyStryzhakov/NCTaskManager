package ua.edu.sumdu.j2se.stryzhakov.tasks;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        AbstractTaskList list = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        Task task3 = new Task("Check", 55);
        task3.setActive(true);
        list.add(task3);
        list.add(new Task("Test1", 15));
        list.add(new Task("Test2", 40));
        list.add(new Task("Test3", 18));
        list.add(new Task("Test4", 10, 24, 5));
        list.add(new Task("Test5", 19));
        Task task = new Task("A", 55);
        task.setActive(true);
        list.add(task);
        list.add(new Task("Test6", 99));
        list.add(new Task("Test7", 10, 24, 5));
        Task task1 = new Task("Test new 1", 10, 24, 5);
        task1.setActive(true);
        list.add(task1);
        System.out.println("Size before removing is :" + list.size());
        list.remove(list.getTask(0));

        Iterator<Task> iterator = list.iterator();
        while (iterator.hasNext()) {
            Task current = iterator.next();
//            if(current.getTitle().equals("Test6")){
//                iterator.remove();
//                System.out.println("Remove: " + current.getTitle());
//                continue;
//            }
            iterator.remove();
            System.out.println("While: " + current.getTitle());
        }
        System.out.println("Size after removing is " + list.size());
        for (Task temp : list) {
            System.out.println("ForEach: " + temp.getTitle());
        }
//        for (Iterator<Task> i = list.iterator(); i.hasNext(); ) {
//            //Task temp = i.next();
//            System.out.println("Remove: " + i.next().getTitle());
//            i.remove();
//
//        }
        /*for (Task temp : list) {
            System.out.println(temp.getTitle());
        }*/
//        list.remove(task);
//        list.remove(list.getTask(0));
//        System.out.println(list.size());
//        System.out.println(list.getTask(0).getTitle());
//        System.out.println(list.getTask(6).getTitle());
//        System.out.println(list.incoming(16, 60).size());
//        System.out.println("After del: " + list.size());
        System.out.println("stop");


    }
}
