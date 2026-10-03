package dao;

import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class StudentDAO {

    // ADD STUDENT
    public boolean addStudent(Student student) {

        String sql = """
                INSERT INTO students
                (registration_no, name, email, phone, gender,
                 department, year, section, qr_code)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getRegistrationNo());
            statement.setString(2, student.getName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPhone());
            statement.setString(5, student.getGender());
            statement.setString(6, student.getDepartment());
            statement.setInt(7, student.getYear());
            statement.setString(8, student.getSection());
            statement.setString(9, student.getQrCode());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // UPDATE STUDENT
    public boolean updateStudent(Student student) {

        String sql = """
                UPDATE students
                SET registration_no = ?,
                    name = ?,
                    email = ?,
                    phone = ?,
                    gender = ?,
                    department = ?,
                    year = ?,
                    section = ?,
                    qr_code = ?
                WHERE id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getRegistrationNo());
            statement.setString(2, student.getName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPhone());
            statement.setString(5, student.getGender());
            statement.setString(6, student.getDepartment());
            statement.setInt(7, student.getYear());
            statement.setString(8, student.getSection());
            statement.setString(9, student.getQrCode());
            statement.setInt(10, student.getId());

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE STUDENT
    public boolean deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // GET ALL STUDENTS
    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = """
                SELECT id, registration_no, name, email, phone,
                       gender, department, year, section, qr_code
                FROM students
                ORDER BY id DESC
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("registration_no"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("gender"),
                        resultSet.getString("department"),
                        resultSet.getInt("year"),
                        resultSet.getString("section"),
                        resultSet.getString("qr_code")
                );

                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    // SEARCH STUDENTS
    public List<Student> searchStudents(String keyword) {

        List<Student> students = new ArrayList<>();

        String sql = """
                SELECT id, registration_no, name, email, phone,
                       gender, department, year, section, qr_code
                FROM students
                WHERE registration_no LIKE ?
                   OR name LIKE ?
                   OR department LIKE ?
                ORDER BY id DESC
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            String searchKeyword = "%" + keyword + "%";

            statement.setString(1, searchKeyword);
            statement.setString(2, searchKeyword);
            statement.setString(3, searchKeyword);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("registration_no"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("gender"),
                        resultSet.getString("department"),
                        resultSet.getInt("year"),
                        resultSet.getString("section"),
                        resultSet.getString("qr_code")
                );

                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
    
    public Student getStudentByQRCode(String qrCode) {

    String sql =
            "SELECT id, registration_no, name, email, phone, "
            + "gender, department, year, section, qr_code "
            + "FROM students WHERE qr_code = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql)) {

        statement.setString(1, qrCode);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {

            Student student = new Student();

            student.setId(resultSet.getInt("id"));
            student.setRegistrationNo(
                    resultSet.getString("registration_no")
            );
            student.setName(
                    resultSet.getString("name")
            );
            student.setEmail(
                    resultSet.getString("email")
            );
            student.setPhone(
                    resultSet.getString("phone")
            );
            student.setGender(
                    resultSet.getString("gender")
            );
            student.setDepartment(
                    resultSet.getString("department")
            );
            student.setYear(
                    resultSet.getInt("year")
            );
            student.setSection(
                    resultSet.getString("section")
            );
            student.setQrCode(
                    resultSet.getString("qr_code")
            );

            return student;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}
// GET STUDENT BY REGISTRATION NUMBER
public Student getStudentByRegistrationNo(
        String registrationNo) {

    String sql =
            "SELECT id, registration_no, name, email, phone, "
            + "gender, department, year, section, qr_code "
            + "FROM students "
            + "WHERE registration_no = ?";

    try (Connection connection =
                 DatabaseConnection.getConnection();
         PreparedStatement statement =
                 connection.prepareStatement(sql)) {

        statement.setString(1, registrationNo);

        ResultSet resultSet =
                statement.executeQuery();

        if (resultSet.next()) {

            Student student = new Student();

            student.setId(
                    resultSet.getInt("id")
            );

            student.setRegistrationNo(
                    resultSet.getString("registration_no")
            );

            student.setName(
                    resultSet.getString("name")
            );

            student.setEmail(
                    resultSet.getString("email")
            );

            student.setPhone(
                    resultSet.getString("phone")
            );

            student.setGender(
                    resultSet.getString("gender")
            );

            student.setDepartment(
                    resultSet.getString("department")
            );

            student.setYear(
                    resultSet.getInt("year")
            );

            student.setSection(
                    resultSet.getString("section")
            );

            student.setQrCode(
                    resultSet.getString("qr_code")
            );

            return student;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}
}