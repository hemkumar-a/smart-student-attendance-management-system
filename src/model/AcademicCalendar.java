package model;

import java.time.LocalDate;

public class AcademicCalendar {

    private int id;
    private LocalDate calendarDate;
    private boolean workingDay;
    private String description;

    public AcademicCalendar() {
    }

    public AcademicCalendar(
            int id,
            LocalDate calendarDate,
            boolean workingDay,
            String description) {

        this.id = id;
        this.calendarDate = calendarDate;
        this.workingDay = workingDay;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(LocalDate calendarDate) {
        this.calendarDate = calendarDate;
    }

    public boolean isWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(boolean workingDay) {
        this.workingDay = workingDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}