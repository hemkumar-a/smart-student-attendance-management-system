package test;

import dao.AttendanceDAO;
import dao.StudentDAO;
import model.Student;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import util.QRScanner;

public class AttendanceQRTest {

    public static void main(String[] args) {

        try {

            // 1. Read QR image
            File file = new File("student-qr.png");

            BufferedImage image =
                    ImageIO.read(file);

            // 2. Decode QR
            String qrCode =
                    QRScanner.decodeQRCode(image);

            System.out.println(
                    "QR Result: " + qrCode
            );

            // 3. Find student
            StudentDAO studentDAO =
                    new StudentDAO();

            Student student =
                    studentDAO.getStudentByQRCode(qrCode);

            if (student == null) {
                System.out.println(
                        "Student not found."
                );
                return;
            }

            System.out.println(
                    "Student: " + student.getName()
            );

            System.out.println(
                    "Registration No: "
                    + student.getRegistrationNo()
            );

            // 4. Mark attendance
            AttendanceDAO attendanceDAO =
                    new AttendanceDAO();

            boolean marked =
                    attendanceDAO.markAttendance(
                            student.getId()
                    );

            if (marked) {

                System.out.println(
                        "Attendance marked successfully."
                );

            } else {

                System.out.println(
                        "Attendance already marked today."
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}