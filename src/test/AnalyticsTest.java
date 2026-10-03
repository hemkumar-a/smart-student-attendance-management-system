package test;

import service.AnalyticsService;

public class AnalyticsTest {

    public static void main(String[] args) {

        AnalyticsService analyticsService =
                new AnalyticsService();

        System.out.println(
                "Working Days Until Today: "
                + analyticsService.getWorkingDaysUntilToday()
        );

        System.out.println(
                "Student 1 Present Days: "
                + analyticsService.getPresentDaysForStudent(2)
        );

        System.out.println(
                "Student 1 Absent Days: "
                + analyticsService.getAbsentDaysForStudent(1)
        );

        System.out.println(
                "Student 1 Attendance: "
                + analyticsService
                        .getStudentAttendancePercentage(1)
                + "%"
        );
        int workingDays =
        analyticsService.getWorkingDaysUntilToday();

int presentDays =
        analyticsService.getPresentDaysForStudent(1);

int absentDays =
        analyticsService.getAbsentDaysForStudent(1);

double percentage =
        analyticsService
                .getStudentAttendancePercentage(1);

System.out.println("Working Days: " + workingDays);
System.out.println("Present Days: " + presentDays);
System.out.println("Absent Days: " + absentDays);
System.out.println("Attendance: " + percentage + "%");

System.out.println(
        "Manual Absent Calculation: "
        + (workingDays - presentDays)
);

System.out.println(
        "Manual Percentage: "
        + ((presentDays * 100.0) / workingDays)
        + "%"
);
    }
}