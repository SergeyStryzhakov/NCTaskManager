package ua.edu.sumdu.j2se.stryzhakov.tasks.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(Utils.class);

    /**
     * Return LocalDateTime object from string.
     *
     * @param data String for convert
     * @return LocalDateTime object
     */
    public static LocalDateTime dateFromString(String data) {
        LOGGER.info("String for convert is {}", data);
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime tempDate;
        tempDate = LocalDateTime.parse(data, formatter);

        return tempDate;
    }
}

