package smartstudentattendance;

import ui.LoginFrame;

public class SmartStudentAttendance {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}