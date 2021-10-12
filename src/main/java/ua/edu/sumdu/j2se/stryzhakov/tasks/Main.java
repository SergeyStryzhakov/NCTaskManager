package ua.edu.sumdu.j2se.stryzhakov.tasks;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ArrayTaskList list = new ArrayTaskList();
        list.add(new Task("Test1", 15));
        list.add(new Task("Test2", 10, 24, 2));
        list.add(new Task("Test3", 18));
        list.add(new Task("Test4", 8, 22, 3));
        Task test5 = new Task("Test5", 84);
        test5.setActive(true);
        Task test6 = new Task("Test6", 98);
        test6.setActive(true);
        list.add(test5);
        System.out.println(list.size());

        list.add(test6);
        System.out.println(list.size());
        list.remove(test5);
        list.remove(test6);
        list.remove(list.getTask(0));
        list.incoming(80, 100);
    }
}
