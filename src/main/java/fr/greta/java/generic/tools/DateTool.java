package fr.greta.java.generic.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTool {

    public String formatDate(LocalDateTime localDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formatDateTime = localDate.format(formatter);

        return formatDateTime;
    }
}
