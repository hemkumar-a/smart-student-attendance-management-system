package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceDAO {

    // Mark attendance for a student
    public boolean markAttendance(int studentId) {

        String checkSql =
                "SELECT id FROM attendance "
                + "WHERE student_id = ? AND attendance_date = ?";

        String insertSql =
                "INSERT INTO attendance "
                + "(student_id, attendance_date, check_in) "
                + "VALUES (?, ?, ?)";

        LocalDate today = LocalDate.now();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement checkStatement =
                     connection.prepareStatement(checkSql)) {

            checkStatement.setInt(1, studentId);
            checkStatement.setDate(
                    2,
                    java.sql.Date.valueOf(today)
            );

            ResultSet resultSet = checkStatement.executeQuery();

            // Already marked today
            if (resultSet.next()) {
                return false;
            }

            try (PreparedStatement insertStatement =
                         connection.prepareStatement(insertSql)) {

                insertStatement.setInt(1, studentId);
                insertStatement.setDate(
                        2,
                        java.sql.Date.valueOf(today)
                );
                insertStatement.setTimestamp(
                        3,
                        java.sql.Timestamp.valueOf(
                                LocalDateTime.now()
                        )
                );

                insertStatement.executeUpdate();
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public java.util.List<model.Attendance> getAllAttendance() {

    java.util.List<model.Attendance> attendanceList =
            new java.util.ArrayList<>();

    String sql =
            "SELECT a.id, a.student_id, "
            + "s.registration_no, s.name, s.department, "
            + "a.attendance_date, a.check_in, a.check_out "
            + "FROM attendance a "
            + "INNER JOIN students s "
            + "ON a.student_id = s.id "
            + "ORDER BY a.attendance_date DESC, a.check_in DESC";

    try (Connection connection =
                 DatabaseConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql);
         ResultSet resultSet =
                 statement.executeQuery()) {

        while (resultSet.next()) {

            model.Attendance attendance =
                    new model.Attendance();

            attendance.setId(
                    resultSet.getInt("id")
            );

            attendance.setStudentId(
                    resultSet.getInt("student_id")
            );

            attendance.setRegistrationNo(
                    resultSet.getString("registration_no")
            );

            attendance.setStudentName(
                    resultSet.getString("name")
            );

            attendance.setDepartment(
                    resultSet.getString("department")
            );

            if (resultSet.getDate("attendance_date") != null) {
                attendance.setAttendanceDate(
                        resultSet.getDate("attendance_date")
                                .toLocalDate()
                );
            }

            if (resultSet.getTimestamp("check_in") != null) {
                attendance.setCheckIn(
                        resultSet.getTimestamp("check_in")
                                .toLocalDateTime()
                );
            }

            if (resultSet.getTimestamp("check_out") != null) {
                attendance.setCheckOut(
                        resultSet.getTimestamp("check_out")
                                .toLocalDateTime()
                );
            }

            attendanceList.add(attendance);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return attendanceList;
}
public java.util.List<model.Attendance> getAttendanceByDate(
        java.time.LocalDate date) {

    java.util.List<model.Attendance> attendanceList =
            new java.util.ArrayList<>();

    String sql =
            "SELECT a.id, a.student_id, "
            + "s.registration_no, s.name, s.department, "
            + "a.attendance_date, a.check_in, a.check_out "
            + "FROM attendance a "
            + "INNER JOIN students s "
            + "ON a.student_id = s.id "
            + "WHERE a.attendance_date = ? "
            + "ORDER BY a.check_in DESC";

    try (Connection connection =
                 DatabaseConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql)) {

        statement.setDate(
                1,
                java.sql.Date.valueOf(date)
        );

        try (ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                model.Attendance attendance =
                        new model.Attendance();

                attendance.setId(
                        resultSet.getInt("id")
                );

                attendance.setStudentId(
                        resultSet.getInt("student_id")
                );

                attendance.setRegistrationNo(
                        resultSet.getString("registration_no")
                );

                attendance.setStudentName(
                        resultSet.getString("name")
                );

                attendance.setDepartment(
                        resultSet.getString("department")
                );

                attendance.setAttendanceDate(
                        resultSet.getDate("attendance_date")
                                .toLocalDate()
                );

                if (resultSet.getTimestamp("check_in") != null) {
                    attendance.setCheckIn(
                            resultSet.getTimestamp("check_in")
                                    .toLocalDateTime()
                    );
                }

                if (resultSet.getTimestamp("check_out") != null) {
                    attendance.setCheckOut(
                            resultSet.getTimestamp("check_out")
                                    .toLocalDateTime()
                    );
                }

                attendanceList.add(attendance);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return attendanceList;
}
public model.AttendanceStatus getTodayAttendanceStatus(
        int studentId) {

    String sql =
            "SELECT check_in, check_out "
            + "FROM attendance "
            + "WHERE student_id = ? "
            + "AND attendance_date = ?";

    try (Connection connection =
                 DatabaseConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql)) {

        statement.setInt(1, studentId);

        statement.setDate(
                2,
                java.sql.Date.valueOf(
                        java.time.LocalDate.now()
                )
        );

        try (ResultSet resultSet =
                     statement.executeQuery()) {

            if (!resultSet.next()) {

                return model.AttendanceStatus.NOT_MARKED;
            }

            if (resultSet.getTimestamp("check_out") != null) {

                return model.AttendanceStatus.CHECKED_OUT;
            }

            if (resultSet.getTimestamp("check_in") != null) {

                return model.AttendanceStatus.CHECKED_IN;
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return model.AttendanceStatus.NOT_MARKED;
}
public boolean markCheckOut(int studentId) {

    String sql =
            "UPDATE attendance "
            + "SET check_out = ? "
            + "WHERE student_id = ? "
            + "AND attendance_date = ? "
            + "AND check_out IS NULL";

    try (Connection connection =
                 DatabaseConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql)) {

        statement.setTimestamp(
                1,
                java.sql.Timestamp.valueOf(
                        java.time.LocalDateTime.now()
                )
        );

        statement.setInt(2, studentId);

        statement.setDate(
                3,
                java.sql.Date.valueOf(
                        java.time.LocalDate.now()
                )
        );

        return statement.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}

}