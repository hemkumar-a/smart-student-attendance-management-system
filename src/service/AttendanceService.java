package service;

import dao.AttendanceDAO;
import model.AttendanceStatus;

public class AttendanceService {

    private final AttendanceDAO attendanceDAO =
            new AttendanceDAO();

public AttendanceStatus processAttendance(int studentId) {

    AttendanceStatus currentStatus =
            attendanceDAO.getTodayAttendanceStatus(studentId);

    switch (currentStatus) {

        case NOT_MARKED:

            boolean checkedIn =
                    attendanceDAO.markAttendance(studentId);

            if (checkedIn) {
                return AttendanceStatus.CHECKED_IN;
            }

            return AttendanceStatus.NOT_MARKED;


        case CHECKED_IN:

            boolean checkedOut =
                    attendanceDAO.markCheckOut(studentId);

            if (checkedOut) {
                return AttendanceStatus.CHECKED_OUT;
            }

            return AttendanceStatus.CHECKED_IN;


        case CHECKED_OUT:

            return AttendanceStatus.ALREADY_COMPLETED;


        case ALREADY_COMPLETED:

            return AttendanceStatus.ALREADY_COMPLETED;


        default:

            return AttendanceStatus.NOT_MARKED;
    }
} 
}