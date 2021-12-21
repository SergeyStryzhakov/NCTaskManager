package ua.edu.sumdu.j2se.stryzhakov.tasks.controller;

import ua.edu.sumdu.j2se.stryzhakov.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Model;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Task;
import ua.edu.sumdu.j2se.stryzhakov.tasks.model.Tasks;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.CalendarView;
import ua.edu.sumdu.j2se.stryzhakov.tasks.view.Viewable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.SortedMap;

public class CalendarController implements Controller {

    private final CalendarView view;
    private final Model model;


    public CalendarController(Viewable view, Model model) {
        this.view = (CalendarView) view;
        this.model = model;
    }

    private String getCalendar(LocalDateTime dateFrom, LocalDateTime dateTo) {
        AbstractTaskList list;
        SortedMap<LocalDateTime, Set<Task>> calendar;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        StringBuilder builder = new StringBuilder();

        list = model.getList();
        calendar = Tasks.calendar(list, dateFrom, dateTo);

        System.out.println("---------------Calendar of tasks from "
                + dateFrom.format(formatter)
                + " to "
                + dateTo.format(formatter) + "---------------");

        for (LocalDateTime dateTime : calendar.keySet()) {
            for (Task task : calendar.get(dateTime)) {
                builder.append(dateTime.format(formatter))
                        .append(" ------ ")
                        .append(task.toString())
                        .append("\n");
            }
        }
        return builder.toString();
    }

    @Override
    public void start() {
        LocalDateTime dateFrom;
        LocalDateTime dateTo;
        int userChoice;

        userChoice = view.show("");
        switch (userChoice) {
            case 1:
                dateFrom = LocalDateTime.now();
                dateTo = LocalDateTime.now().plusDays(7);
                view.setMaxUserChoice(2);
                userChoice = view.show(getCalendar(dateFrom, dateTo));
                if (userChoice == 1) {
                    ControllerFactory.selectController(Action.MAIN).start();
                } else {
                    System.exit(0);
                }
                break;
            case 2:
                dateFrom = dateFromString(view.getDate("start"));
                dateTo = dateFromString(view.getDate("end"));
                if (dateTo.isBefore(dateFrom)) {
                    System.out.println("The end date is earlier then the start date. Try again!");
                    start();
                    view.setMaxUserChoice(2);
                    userChoice = view.show(getCalendar(dateFrom, dateTo));
                    if (userChoice == 1) {
                        ControllerFactory.selectController(Action.MAIN).start();
                    } else {
                        System.exit(0);
                    }
                }
            case 3:
                ControllerFactory.selectController(Action.MAIN).start();
            case 4:
                System.exit(0);
        }

    }
}
