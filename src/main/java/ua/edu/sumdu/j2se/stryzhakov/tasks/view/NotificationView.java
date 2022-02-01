package ua.edu.sumdu.j2se.stryzhakov.tasks.view;

public class NotificationView implements Viewable {
    @Override
    public int show(String text) {
        System.out.println("***************"
                + " NOTIFICATION START "
                + "***************");
        System.out.println(text);
        System.out.println("***************"
                + " NOTIFICATION FINISH "
                + "***************");
        return 0;
    }
}
