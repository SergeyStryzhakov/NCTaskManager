package ua.edu.sumdu.j2se.stryzhakov.tasks;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ArrayTaskList list = new ArrayTaskList();
        list.addTask(new Task("Test1", 15));
        list.addTask(new Task("Test2", 10, 24, 2));
        list.addTask(new Task("Test3", 18));
        list.addTask(new Task("Test4", 8, 22, 3));
        Task test5 = new Task("Test5", 84);
        Task test6 = new Task("Test6", 98);
        list.addTask(test5);
        System.out.println(list.size());

        list.addTask(test6);
        System.out.println(list.size());
        list.removeTask(test5);
    }
}
