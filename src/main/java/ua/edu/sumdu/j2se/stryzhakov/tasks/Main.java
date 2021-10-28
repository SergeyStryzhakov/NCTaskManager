package ua.edu.sumdu.j2se.stryzhakov.tasks;

public class Main {

    public static void main(String[] args) {

       AbstractTaskList list = TaskListFactory.createTaskList(ListTypes.types.LINKED);
        list.add(new Task("Test1", 15));
        list.add(new Task("Test2", 40));
        list.add(new Task("Test3", 18));
        list.add(new Task("Test4", 10, 24, 5));
        list.add(new Task("Test5", 19));
        Task task = new Task("Test new", 55);
        task.setActive(true);
        list.add(task);
        list.add(new Task("Test6", 99));
        list.add(new Task("Test7", 10, 24, 5));
        Task task1 = new Task("Test new 1", 10, 24, 5);
        task1.setActive(true);
        list.add(task1);
        System.out.println("Size:" + list.size());
        list.remove(task);
        System.out.println(list.size());
        System.out.println(list.getTask(0).getTitle());
        System.out.println(list.getTask(6).getTitle());
        System.out.println(list.incoming(16, 60).size());
        System.out.println("After del: " + list.size());
        System.out.println("stop");


    }
}
