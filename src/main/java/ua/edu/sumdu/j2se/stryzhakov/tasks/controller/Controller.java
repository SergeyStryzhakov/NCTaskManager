package ua.edu.sumdu.j2se.stryzhakov.tasks.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface Controller {

    void start();

    default LocalDateTime dateFromString(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime tempDate;
        tempDate = LocalDateTime.parse(data, formatter);
        return tempDate;
    }

}
