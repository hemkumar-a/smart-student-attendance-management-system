package dao;

import model.AcademicCalendar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarDAO {

    public boolean addCalendarEntry(
            AcademicCalendar calendar) {

        String sql =
                "INSERT INTO academic_calendar "
                + "(calendar_date, is_working_day, description) "
                + "VALUES (?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setDate(
                    1,
                    java.sql.Date.valueOf(
                            calendar.getCalendarDate()
                    )
            );

            statement.setBoolean(
                    2,
                    calendar.isWorkingDay()
            );

            statement.setString(
                    3,
                    calendar.getDescription()
            );

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCalendarEntry(
            AcademicCalendar calendar) {

        String sql =
                "UPDATE academic_calendar "
                + "SET is_working_day = ?, "
                + "description = ? "
                + "WHERE calendar_date = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setBoolean(
                    1,
                    calendar.isWorkingDay()
            );

            statement.setString(
                    2,
                    calendar.getDescription()
            );

            statement.setDate(
                    3,
                    java.sql.Date.valueOf(
                            calendar.getCalendarDate()
                    )
            );

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCalendarEntry(
            LocalDate date) {

        String sql =
                "DELETE FROM academic_calendar "
                + "WHERE calendar_date = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setDate(
                    1,
                    java.sql.Date.valueOf(date)
            );

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public AcademicCalendar getCalendarEntry(
            LocalDate date) {

        String sql =
                "SELECT * FROM academic_calendar "
                + "WHERE calendar_date = ?";

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

                if (resultSet.next()) {

                    AcademicCalendar calendar =
                            new AcademicCalendar();

                    calendar.setId(
                            resultSet.getInt("id")
                    );

                    calendar.setCalendarDate(
                            resultSet
                                    .getDate("calendar_date")
                                    .toLocalDate()
                    );

                    calendar.setWorkingDay(
                            resultSet.getBoolean(
                                    "is_working_day"
                            )
                    );

                    calendar.setDescription(
                            resultSet.getString(
                                    "description"
                            )
                    );

                    return calendar;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<AcademicCalendar> getAllCalendarEntries() {

        List<AcademicCalendar> calendarList =
                new ArrayList<>();

        String sql =
                "SELECT * FROM academic_calendar "
                + "ORDER BY calendar_date ASC";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                AcademicCalendar calendar =
                        new AcademicCalendar();

                calendar.setId(
                        resultSet.getInt("id")
                );

                calendar.setCalendarDate(
                        resultSet
                                .getDate("calendar_date")
                                .toLocalDate()
                );

                calendar.setWorkingDay(
                        resultSet.getBoolean(
                                "is_working_day"
                        )
                );

                calendar.setDescription(
                        resultSet.getString(
                                "description"
                        )
                );

                calendarList.add(calendar);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return calendarList;
    }
}