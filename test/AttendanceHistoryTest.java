package test;

import dao.AttendanceDAO;
import model.Attendance;

import java.util.List;

public class AttendanceHistoryTest {

    public static void main(String[] args) {

        AttendanceDAO attendanceDAO =
                new AttendanceDAO();

        List<Attendance> records =
                attendanceDAO.getAllAttendance();

        System.out.println(
                "Attendance Records: "
                + records.size()
        );

        for (Attendance attendance : records) {

            System.out.println(
                    attendance.getRegistrationNo()
                    + " | "
                    + attendance.getStudentName()
                    + " | "
                    + attendance.getAttendanceDate()
                    + " | Check-in: "
                    + attendance.getCheckIn()
                    + " | Check-out: "
                    + attendance.getCheckOut()
            );
        }
    }
}