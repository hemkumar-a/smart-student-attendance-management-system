package test;

import dao.StudentDAO;
import model.Student;

public class StudentQRTest {

    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();

        String qrCode = "STUDENT-23AD001";

        Student student =
                studentDAO.getStudentByQRCode(qrCode);

        if (student != null) {

            System.out.println("Student found!");
            System.out.println("ID: " + student.getId());
            System.out.println(
                    "Registration No: "
                    + student.getRegistrationNo()
            );
            System.out.println(
                    "Name: " + student.getName()
            );

        } else {

            System.out.println("Student not found.");
        }
    }
}