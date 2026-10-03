package test;

import dao.AttendanceDAO;

public class AttendanceTest {

    public static void main(String[] args) {

        AttendanceDAO attendanceDAO = new AttendanceDAO();

        // Student ID from our database
        int studentId = 2;

        boolean result =
                attendanceDAO.markAttendance(studentId);

        if (result) {
            System.out.println(
                    "Attendance marked successfully."
            );
        } else {
            System.out.println(
                    "Attendance already marked or an error occurred."
            );
        }
    }
}