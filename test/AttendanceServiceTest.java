package test;

import model.AttendanceStatus;
import service.AttendanceService;

public class AttendanceServiceTest {

    public static void main(String[] args) {

        int studentId = 2;

        AttendanceService service =
                new AttendanceService();

        System.out.println(
                "Processing attendance..."
        );

        AttendanceStatus status =
                service.processAttendance(studentId);

        System.out.println(
                "Result: " + status
        );
    }
}