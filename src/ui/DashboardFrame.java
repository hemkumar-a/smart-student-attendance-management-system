package ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import ui.StudentFrame;
import ui.AttendanceFrame;
import ui.AttendanceHistoryFrame;
import service.AnalyticsService;
public class DashboardFrame extends javax.swing.JFrame {

public DashboardFrame() {

    initComponents();

    setTitle("Smart Student Attendance - Dashboard");

    setSize(1050, 650);

    setLocationRelativeTo(null);

    loadDashboardAnalytics();

    addWindowListener(new WindowAdapter() {

        @Override
        public void windowActivated(WindowEvent e) {
            loadDashboardAnalytics();
        }
    });
}

@SuppressWarnings("unchecked")
private void initComponents() {

    titleLabel = new javax.swing.JLabel();
    welcomeLabel = new javax.swing.JLabel();

    totalStudentsTitle = new javax.swing.JLabel();
    totalStudentsValue = new javax.swing.JLabel();

    presentTodayTitle = new javax.swing.JLabel();
    presentTodayValue = new javax.swing.JLabel();

    absentTodayTitle = new javax.swing.JLabel();
    absentTodayValue = new javax.swing.JLabel();

    attendanceRateTitle = new javax.swing.JLabel();
    attendanceRateValue = new javax.swing.JLabel();

    studentsButton = new javax.swing.JButton();
    scanQRButton = new javax.swing.JButton();
    attendanceButton = new javax.swing.JButton();
    reportsButton = new javax.swing.JButton();
    logoutButton = new javax.swing.JButton();


    // =========================================================
    // FRAME
    // =========================================================

    setDefaultCloseOperation(
            javax.swing.WindowConstants.EXIT_ON_CLOSE
    );


    // =========================================================
    // TITLE
    // =========================================================

    titleLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    28
            )
    );

    titleLabel.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    titleLabel.setText(
            "SMART STUDENT ATTENDANCE"
    );


    // =========================================================
    // SUBTITLE
    // =========================================================

    welcomeLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    16
            )
    );

    welcomeLabel.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    welcomeLabel.setText("Dashboard");


    // =========================================================
    // STAT TITLES
    // =========================================================

    totalStudentsTitle.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    14
            )
    );

    totalStudentsTitle.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    totalStudentsTitle.setText(
            "TOTAL STUDENTS"
    );


    presentTodayTitle.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    14
            )
    );

    presentTodayTitle.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    presentTodayTitle.setText(
            "PRESENT TODAY"
    );


    absentTodayTitle.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    14
            )
    );

    absentTodayTitle.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    absentTodayTitle.setText(
            "ABSENT TODAY"
    );


    attendanceRateTitle.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    14
            )
    );

    attendanceRateTitle.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    attendanceRateTitle.setText(
            "ATTENDANCE RATE"
    );


    // =========================================================
    // STAT VALUES
    // =========================================================

    totalStudentsValue.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    30
            )
    );

    totalStudentsValue.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    totalStudentsValue.setText("0");


    presentTodayValue.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    30
            )
    );

    presentTodayValue.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    presentTodayValue.setText("0");


    absentTodayValue.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    30
            )
    );

    absentTodayValue.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    absentTodayValue.setText("0");


    attendanceRateValue.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    30
            )
    );

    attendanceRateValue.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    attendanceRateValue.setText("0%");


    // =========================================================
    // STAT CARDS
    // =========================================================

    javax.swing.JPanel totalCard =
            new javax.swing.JPanel(
                    new java.awt.GridLayout(2, 1)
            );

    totalCard.setBorder(
            javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(
                            java.awt.Color.LIGHT_GRAY
                    ),
                    javax.swing.BorderFactory.createEmptyBorder(
                            12, 10, 12, 10
                    )
            )
    );

    totalCard.add(totalStudentsTitle);
    totalCard.add(totalStudentsValue);


    javax.swing.JPanel presentCard =
            new javax.swing.JPanel(
                    new java.awt.GridLayout(2, 1)
            );

    presentCard.setBorder(
            javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(
                            java.awt.Color.LIGHT_GRAY
                    ),
                    javax.swing.BorderFactory.createEmptyBorder(
                            12, 10, 12, 10
                    )
            )
    );

    presentCard.add(presentTodayTitle);
    presentCard.add(presentTodayValue);


    javax.swing.JPanel absentCard =
            new javax.swing.JPanel(
                    new java.awt.GridLayout(2, 1)
            );

    absentCard.setBorder(
            javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(
                            java.awt.Color.LIGHT_GRAY
                    ),
                    javax.swing.BorderFactory.createEmptyBorder(
                            12, 10, 12, 10
                    )
            )
    );

    absentCard.add(absentTodayTitle);
    absentCard.add(absentTodayValue);


    javax.swing.JPanel rateCard =
            new javax.swing.JPanel(
                    new java.awt.GridLayout(2, 1)
            );

    rateCard.setBorder(
            javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(
                            java.awt.Color.LIGHT_GRAY
                    ),
                    javax.swing.BorderFactory.createEmptyBorder(
                            12, 10, 12, 10
                    )
            )
    );

    rateCard.add(attendanceRateTitle);
    rateCard.add(attendanceRateValue);


    javax.swing.JPanel statisticsPanel =
            new javax.swing.JPanel(
                    new java.awt.GridLayout(
                            1,
                            4,
                            15,
                            0
                    )
            );

    statisticsPanel.add(totalCard);
    statisticsPanel.add(presentCard);
    statisticsPanel.add(absentCard);
    statisticsPanel.add(rateCard);


    // =========================================================
    // NAVIGATION BUTTONS
    // =========================================================

    studentsButton.setText("STUDENTS");

    scanQRButton.setText("SCAN QR");

    attendanceButton.setText("ATTENDANCE");

    reportsButton.setText("REPORTS");

    logoutButton.setText("LOGOUT");


    studentsButton.setPreferredSize(
            new java.awt.Dimension(180, 50)
    );

    scanQRButton.setPreferredSize(
            new java.awt.Dimension(180, 50)
    );

    attendanceButton.setPreferredSize(
            new java.awt.Dimension(180, 50)
    );

    reportsButton.setPreferredSize(
            new java.awt.Dimension(180, 50)
    );

    logoutButton.setPreferredSize(
            new java.awt.Dimension(140, 40)
    );


    // =========================================================
    // BUTTON LISTENERS
    // =========================================================

    studentsButton.addActionListener(
            evt -> studentsButtonActionPerformed(evt)
    );

    scanQRButton.addActionListener(
            evt -> scanQRButtonActionPerformed(evt)
    );

    attendanceButton.addActionListener(
            evt -> attendanceButtonActionPerformed(evt)
    );

    reportsButton.addActionListener(
            evt -> reportsButtonActionPerformed(evt)
    );

    logoutButton.addActionListener(
            evt -> logoutButtonActionPerformed(evt)
    );


    // =========================================================
    // NAVIGATION PANEL
    // =========================================================

    javax.swing.JPanel navigationPanel =
            new javax.swing.JPanel(
                    new java.awt.GridLayout(
                            2,
                            2,
                            15,
                            15
                    )
            );

    navigationPanel.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(
                    5,
                    100,
                    5,
                    100
            )
    );

    navigationPanel.add(studentsButton);
    navigationPanel.add(scanQRButton);
    navigationPanel.add(attendanceButton);
    navigationPanel.add(reportsButton);


    // =========================================================
    // LOGOUT PANEL
    // =========================================================

    javax.swing.JPanel logoutPanel =
            new javax.swing.JPanel(
                    new java.awt.FlowLayout(
                            java.awt.FlowLayout.CENTER
                    )
            );

    logoutPanel.add(logoutButton);


    // =========================================================
    // MAIN PANEL
    // =========================================================

    javax.swing.JPanel mainPanel =
            new javax.swing.JPanel();

    mainPanel.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(
                    25,
                    40,
                    25,
                    40
            )
    );

    mainPanel.setLayout(
            new javax.swing.BoxLayout(
                    mainPanel,
                    javax.swing.BoxLayout.Y_AXIS
            )
    );


    titleLabel.setAlignmentX(
            javax.swing.JComponent.CENTER_ALIGNMENT
    );

    welcomeLabel.setAlignmentX(
            javax.swing.JComponent.CENTER_ALIGNMENT
    );


    mainPanel.add(titleLabel);

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(5)
    );

    mainPanel.add(welcomeLabel);

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(35)
    );

    mainPanel.add(statisticsPanel);

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(45)
    );

    mainPanel.add(navigationPanel);

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(30)
    );

    mainPanel.add(logoutPanel);


    // =========================================================
    // FRAME CONTENT
    // =========================================================

    getContentPane().setLayout(
            new java.awt.BorderLayout()
    );

    getContentPane().add(
            mainPanel,
            java.awt.BorderLayout.CENTER
    );

    pack();
}


    private void studentsButtonActionPerformed(java.awt.event.ActionEvent evt) {

       StudentFrame studentFrame = new StudentFrame();
       studentFrame.setVisible(true);
    }

private void scanQRButtonActionPerformed(
        java.awt.event.ActionEvent evt) {

    new AttendanceFrame().setVisible(true);
}

private void attendanceButtonActionPerformed(
        java.awt.event.ActionEvent evt) {

    new AttendanceHistoryFrame().setVisible(true);
}

private void reportsButtonActionPerformed(
        java.awt.event.ActionEvent evt) {

    new ReportsFrame().setVisible(true);
}
    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Do you want to logout?",
                "Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {

            new LoginFrame().setVisible(true);
            dispose();
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new DashboardFrame().setVisible(true);
        });
    }
    private final AnalyticsService analyticsService =
        new AnalyticsService();
private void loadDashboardAnalytics() {

    int totalStudents =
            analyticsService.getTotalStudents();

    totalStudentsValue.setText(
            String.valueOf(totalStudents)
    );

    // Check whether today is a working day
    boolean workingDay =
            analyticsService.isWorkingDay(
                    java.time.LocalDate.now()
            );

    if (!workingDay) {

        presentTodayValue.setText("-");
        absentTodayValue.setText("-");
        attendanceRateValue.setText("N/A");

        return;
    }

    int presentToday =
            analyticsService.getPresentToday();

    int absentToday =
            analyticsService.getAbsentToday();

    double attendanceRate =
            analyticsService.getAttendanceRate();

    presentTodayValue.setText(
            String.valueOf(presentToday)
    );

    absentTodayValue.setText(
            String.valueOf(absentToday)
    );

    attendanceRateValue.setText(
            String.format("%.1f%%", attendanceRate)
    );
}
    // Variables declaration - do not modify
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel welcomeLabel;

    private javax.swing.JLabel totalStudentsTitle;
    private javax.swing.JLabel totalStudentsValue;

    private javax.swing.JLabel presentTodayTitle;
    private javax.swing.JLabel presentTodayValue;

    private javax.swing.JLabel absentTodayTitle;
    private javax.swing.JLabel absentTodayValue;

    private javax.swing.JLabel attendanceRateTitle;
    private javax.swing.JLabel attendanceRateValue;

    private javax.swing.JButton studentsButton;
    private javax.swing.JButton scanQRButton;
    private javax.swing.JButton attendanceButton;
    private javax.swing.JButton reportsButton;
    private javax.swing.JButton logoutButton;
    // End of variables declaration
}