package test;

import dao.CalendarDAO;
import model.AcademicCalendar;

import java.time.LocalDate;
import java.util.List;

public class CalendarDAOTest {

    public static void main(String[] args) {

        CalendarDAO calendarDAO =
                new CalendarDAO();

        AcademicCalendar calendar =
                new AcademicCalendar();

        calendar.setCalendarDate(
                LocalDate.of(2026, 9, 23)
        );

        calendar.setWorkingDay(false);

        calendar.setDescription(
                "College Holiday"
        );

        boolean added =
                calendarDAO.addCalendarEntry(calendar);

        System.out.println(
                "Added: " + added
        );

        List<AcademicCalendar> entries =
                calendarDAO.getAllCalendarEntries();

        System.out.println(
                "Calendar Entries: "
                + entries.size()
        );

        for (AcademicCalendar entry : entries) {

            System.out.println(
                    entry.getCalendarDate()
                    + " | Working Day: "
                    + entry.isWorkingDay()
                    + " | "
                    + entry.getDescription()
            );
        }
    }
}