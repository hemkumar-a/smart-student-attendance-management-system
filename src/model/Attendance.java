package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Attendance {

    private int id;
    private int studentId;

    private String registrationNo;
    private String studentName;
    private String department;

    private LocalDate attendanceDate;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    public Attendance() {
    }

    public Attendance(
            int id,
            int studentId,
            String registrationNo,
            String studentName,
            String department,
            LocalDate attendanceDate,
            LocalDateTime checkIn,
            LocalDateTime checkOut) {

        this.id = id;
        this.studentId = studentId;
        this.registrationNo = registrationNo;
        this.studentName = studentName;
        this.department = department;
        this.attendanceDate = attendanceDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }
}