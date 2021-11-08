package ua.edu.sumdu.j2se.stryzhakov.tasks;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {

        AbstractTaskList list = TaskListFactory.createTaskList(ListTypes.types.LINKED);
        AbstractTaskList list1 = TaskListFactory.createTaskList(ListTypes.types.LINKED);

        list.add(new Task("Кіно", 15));
        list.add(new Task("Кіно", 15));
        list.add(new Task("На роботу", 40));
        list.add(new Task("Кава", 18));
        list.add(new Task("Тренування", 10, 24, 5));
        list.add(new Task("Прийом ліків", 19));
        AbstractTaskList list2 = TaskListFactory.createTaskList(ListTypes.types.LINKED);
        list2 = (AbstractTaskList) list.clone();
        System.out.println("Equals after clone is " + list.equals(list2));
        System.out.println("Hash of list is " + list2.hashCode());
        list1.add(new Task("Кіно", 15));
        list1.add(new Task("Кіно", 15));
        list1.add(new Task("На роботу", 40));
        list1.add(new Task("Кава", 18));
        //list1.add(new Task("Прийом ліків", 19));
        list1.add(new Task("Тренування", 10, 24, 5));
        list1.add(new Task("Прийом ліків", 19));
        System.out.println("Equals lists is " + list1.equals(list));
        System.out.println("Hash list: " + list.hashCode());
        System.out.println("Hash list1: " + list1.hashCode());

        Task task3 = new Task("Зустріч з друзями", 55);
        task3.setActive(true);
        list.add(task3);
        list.add(new Task("Кіно", 15));
        list.add(new Task("На роботу", 40));
        list.add(new Task("Кава", 18));
        list.add(new Task("Тренування", 10, 24, 5));
        list.add(new Task("Прийом ліків", 19));
        Task task = new Task("Відпочинок", 55);
        task.setActive(true);
        list.add(task);
        list.add(new Task("Зателефонувати мамі", 99));
        list.add(new Task("Зателефонувати дружині", 10, 24, 5));
        Task task1 = new Task("Подивитися новий трейлер", 10, 24, 5);
        task1.setActive(true);
        list.add(task1);
        System.out.println("Size before removing is :" + list.size());
        list.remove(list.getTask(0));
        System.out.println(task1.getTitle() + " hashcode is " + task1.hashCode());
        System.out.println(task3.getTitle() + " hashcode is " + task3.hashCode());
        Task task4 = new Task("Відпочинок", 55);
        task4.setActive(true);
        System.out.println("Equals: " + task4.equals(task));
        System.out.println("List to string: ");
        System.out.println(list);
        Iterator<Task> iterator = list.iterator();
//        iterator.remove();
        while (iterator.hasNext()) {
            Task current = iterator.next();
//            if(current.getTitle().equals("Test6")){
//                iterator.remove();
//                System.out.println("Remove: " + current.getTitle());
//                continue;
//            }
            //iterator.remove();
            System.out.println("Task to string: ");
            System.out.println(current.toString());

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
