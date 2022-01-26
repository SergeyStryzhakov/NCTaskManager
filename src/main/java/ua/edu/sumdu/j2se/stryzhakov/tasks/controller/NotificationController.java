package ua.edu.sumdu.j2se.stryzhakov.tasks.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Model;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Task;
import ua.edu.sumdu.j2se.stryzhakov.tasks.utils.Utils;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.Viewable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class NotificationController implements Controller {
    private static final int NOTIFICATION_TIME = 60; //in minutes
    private static final int NOTIFICATION_CHECK_INTERVAL = 600000; //in ms
    private static final Logger LOGGER = LoggerFactory
            .getLogger(NotificationController.class);
    private final Viewable view;
    private final Model model;
    private AbstractTaskList list;

    public NotificationController(Viewable notificatorView, Model model) {
        this.view = notificatorView;
        this.model = model;
    }

    @Override
    public void start() {

        Thread thread = new Thread(createNotificator());
        thread.setDaemon(true);
        thread.start();
        model.setNotificationStarted(true);
        LOGGER.info("{} started.", thread.getName());
        ControllerFactory.selectController(Action.MAIN).start();
    }

    /**
     * Create notificator for tasks
     *
     * @return notificator for thread
     */
    private Runnable createNotificator() {

        return () -> {
            while (true) {
                try {
                    Thread.sleep(NOTIFICATION_CHECK_INTERVAL);
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage());

                }
                list = model.getList();
                StringBuilder sb = new StringBuilder();
                for (Task tempTask : list) {
                    LocalDateTime nextTime = tempTask.nextTimeAfter(
                            LocalDateTime.now());
                    if (nextTime != null &&
                            ChronoUnit.MINUTES.between(
                                    LocalDateTime.now(), nextTime) <= NOTIFICATION_TIME) {
                        sb.append(tempTask.getTitle())
                                .append(" starts at ")
                                .append(Utils.stringFromDate(nextTime))
                                .append("\n");

                    }
                }
                view.show(sb.toString());
            }
        };
    }


}
