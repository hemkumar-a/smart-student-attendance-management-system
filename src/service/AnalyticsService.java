package service;

import dao.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class AnalyticsService {

    public int getTotalStudents() {

        String sql = "SELECT COUNT(*) FROM students";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getPresentToday() {

        String sql =
                "SELECT COUNT(*) FROM attendance "
                + "WHERE attendance_date = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setDate(
                    1,
                    java.sql.Date.valueOf(LocalDate.now())
            );

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getAbsentToday() {

        int totalStudents = getTotalStudents();
        int presentToday = getPresentToday();

        return totalStudents - presentToday;
    }

    public double getAttendanceRate() {

        int totalStudents = getTotalStudents();

        if (totalStudents == 0) {
            return 0.0;
        }

        int presentToday = getPresentToday();

        return (presentToday * 100.0) / totalStudents;
    }

    // ==========================================
    // WORKING DAY LOGIC
    // ==========================================

    public boolean isWorkingDay(LocalDate date) {

        String sql =
                "SELECT is_working_day "
                + "FROM academic_calendar "
                + "WHERE calendar_date = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setDate(
                    1,
                    java.sql.Date.valueOf(date)
            );

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    return resultSet.getBoolean(
                            "is_working_day"
                    );
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // No explicit calendar entry.
        // Monday-Friday = working day.
        // Saturday-Sunday = non-working day.

        DayOfWeek day =
                date.getDayOfWeek();

        return day != DayOfWeek.SATURDAY
                && day != DayOfWeek.SUNDAY;
    }

    public int getWorkingDaysUntilToday() {

        LocalDate startDate =
                LocalDate.of(
                        LocalDate.now().getYear(),
                        1,
                        1
                );

        LocalDate endDate =
                LocalDate.now();

        int workingDays = 0;

        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {

            if (isWorkingDay(currentDate)) {
                workingDays++;
            }

            currentDate =
                    currentDate.plusDays(1);
        }

        return workingDays;
    }

    // ==========================================
// WORKING DAYS FOR A SPECIFIC STUDENT
// ==========================================

public int getWorkingDaysForStudent(int studentId) {

    LocalDate startDate = getStudentCreatedDate(studentId);

    if (startDate == null) {
        return 0;
    }

    LocalDate firstDayOfYear =
            LocalDate.of(
                    LocalDate.now().getYear(),
                    1,
                    1
            );

    // Do not count working days before the
    // student was added to the system.
    if (startDate.isBefore(firstDayOfYear)) {
        startDate = firstDayOfYear;
    }

    LocalDate endDate = LocalDate.now();

    if (startDate.isAfter(endDate)) {
        return 0;
    }

    int workingDays = 0;

    LocalDate currentDate = startDate;

    while (!currentDate.isAfter(endDate)) {

        if (isWorkingDay(currentDate)) {
            workingDays++;
        }

        currentDate =
                currentDate.plusDays(1);
    }

    return workingDays;
}

// ==========================================
// GET STUDENT CREATION DATE
// ==========================================

private LocalDate getStudentCreatedDate(int studentId) {

    String sql =
            "SELECT created_at "
            + "FROM students "
            + "WHERE id = ?";

    try (Connection connection =
                 DatabaseConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql)) {

        statement.setInt(1, studentId);

        try (ResultSet resultSet =
                     statement.executeQuery()) {

            if (resultSet.next()) {

                java.sql.Timestamp timestamp =
                        resultSet.getTimestamp("created_at");

                if (timestamp != null) {

                    return timestamp
                            .toLocalDateTime()
                            .toLocalDate();
                }
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}

public int getPresentDaysForStudent(int studentId) {

        String sql =
                "SELECT attendance_date "
                + "FROM attendance "
                + "WHERE student_id = ? "
                + "AND attendance_date <= ?";

        int presentDays = 0;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);

            statement.setDate(
                    2,
                    java.sql.Date.valueOf(
                            LocalDate.now()
                    )
            );

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                while (resultSet.next()) {

                    LocalDate attendanceDate =
                            resultSet.getDate(
                                    "attendance_date"
                            ).toLocalDate();

                    if (isWorkingDay(attendanceDate)) {
                        presentDays++;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return presentDays;
    }

public int getAbsentDaysForStudent(int studentId) {

    int workingDays =
            getWorkingDaysForStudent(studentId);

    int presentDays =
            getPresentDaysForStudent(studentId);

    return Math.max(
            0,
            workingDays - presentDays
    );
}
public double getStudentAttendancePercentage(
        int studentId) {

    int workingDays =
            getWorkingDaysForStudent(studentId);

    if (workingDays == 0) {
        return 0.0;
    }

    int presentDays =
            getPresentDaysForStudent(studentId);

    return (presentDays * 100.0)
            / workingDays;
}
}