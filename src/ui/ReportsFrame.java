package ui;

public class ReportsFrame extends javax.swing.JFrame {

    public ReportsFrame() {

    initComponents();

    setTitle("Smart Student Attendance - Reports");

    setSize(600, 520);

    setLocationRelativeTo(null);
}

    private void openAttendanceHistory() {

        new AttendanceHistoryFrame()
                .setVisible(true);
    }

    private void openStudentReport() {

        new AttendanceReportFrame()
                .setVisible(true);
    }

    private void openAcademicCalendar() {

        new CalendarFrame()
                .setVisible(true);
    }

    private void attendanceHistoryButtonActionPerformed(
            java.awt.event.ActionEvent evt) {

        openAttendanceHistory();
    }

    private void studentReportButtonActionPerformed(
            java.awt.event.ActionEvent evt) {

        openStudentReport();
    }

    private void calendarButtonActionPerformed(
            java.awt.event.ActionEvent evt) {

        openAcademicCalendar();
    }

    private void closeButtonActionPerformed(
            java.awt.event.ActionEvent evt) {

        dispose();
    }



    @SuppressWarnings("unchecked")
private void initComponents() {

    titleLabel =
            new javax.swing.JLabel();

    attendanceHistoryButton =
            new javax.swing.JButton();

    studentReportButton =
            new javax.swing.JButton();

    calendarButton =
            new javax.swing.JButton();

    closeButton =
            new javax.swing.JButton();


    // =========================================================
    // FRAME
    // =========================================================

    setDefaultCloseOperation(
            javax.swing.WindowConstants.DISPOSE_ON_CLOSE
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
            "REPORTS"
    );


    // =========================================================
    // BUTTON STYLE
    // =========================================================

    attendanceHistoryButton.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    attendanceHistoryButton.setText(
            "ATTENDANCE HISTORY"
    );

    attendanceHistoryButton.setPreferredSize(
            new java.awt.Dimension(
                    330,
                    55
            )
    );

    attendanceHistoryButton.addActionListener(
            evt ->
                    attendanceHistoryButtonActionPerformed(evt)
    );


    studentReportButton.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    studentReportButton.setText(
            "STUDENT ATTENDANCE REPORT"
    );

    studentReportButton.setPreferredSize(
            new java.awt.Dimension(
                    330,
                    55
            )
    );

    studentReportButton.addActionListener(
            evt ->
                    studentReportButtonActionPerformed(evt)
    );


    calendarButton.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    calendarButton.setText(
            "ACADEMIC CALENDAR"
    );

    calendarButton.setPreferredSize(
            new java.awt.Dimension(
                    330,
                    55
            )
    );

    calendarButton.addActionListener(
            evt ->
                    calendarButtonActionPerformed(evt)
    );


    closeButton.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    12
            )
    );

    closeButton.setText(
            "CLOSE"
    );

    closeButton.setPreferredSize(
            new java.awt.Dimension(
                    120,
                    40
            )
    );

    closeButton.addActionListener(
            evt ->
                    closeButtonActionPerformed(evt)
    );


    // =========================================================
    // SUBTITLE
    // =========================================================

    javax.swing.JLabel subtitleLabel =
            new javax.swing.JLabel(
                    "Access attendance records, reports and calendar"
            );

    subtitleLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    14
            )
    );

    subtitleLabel.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );


    // =========================================================
    // REPORT OPTIONS PANEL
    // =========================================================

    javax.swing.JPanel optionsPanel =
            new javax.swing.JPanel();

    optionsPanel.setLayout(
            new javax.swing.BoxLayout(
                    optionsPanel,
                    javax.swing.BoxLayout.Y_AXIS
            )
    );

    optionsPanel.setBorder(
            javax.swing.BorderFactory.createTitledBorder(
                    "Report & Management Options"
            )
    );


    attendanceHistoryButton.setAlignmentX(
            javax.swing.JComponent.CENTER_ALIGNMENT
    );

    studentReportButton.setAlignmentX(
            javax.swing.JComponent.CENTER_ALIGNMENT
    );

    calendarButton.setAlignmentX(
            javax.swing.JComponent.CENTER_ALIGNMENT
    );


    optionsPanel.add(
            javax.swing.Box.createVerticalStrut(15)
    );

    optionsPanel.add(
            attendanceHistoryButton
    );

    optionsPanel.add(
            javax.swing.Box.createVerticalStrut(15)
    );

    optionsPanel.add(
            studentReportButton
    );

    optionsPanel.add(
            javax.swing.Box.createVerticalStrut(15)
    );

    optionsPanel.add(
            calendarButton
    );

    optionsPanel.add(
            javax.swing.Box.createVerticalStrut(15)
    );


    // =========================================================
    // CLOSE PANEL
    // =========================================================

    javax.swing.JPanel closePanel =
            new javax.swing.JPanel(
                    new java.awt.FlowLayout(
                            java.awt.FlowLayout.CENTER
                    )
            );

    closePanel.add(
            closeButton
    );


    // =========================================================
    // MAIN PANEL
    // =========================================================

    javax.swing.JPanel mainPanel =
            new javax.swing.JPanel();

    mainPanel.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(
                    25,
                    45,
                    25,
                    45
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

    subtitleLabel.setAlignmentX(
            javax.swing.JComponent.CENTER_ALIGNMENT
    );


    mainPanel.add(
            titleLabel
    );

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(5)
    );

    mainPanel.add(
            subtitleLabel
    );

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(25)
    );

    mainPanel.add(
            optionsPanel
    );

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(20)
    );

    mainPanel.add(
            closePanel
    );


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
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new ReportsFrame()
                    .setVisible(true);
        });
    }

    // Variables declaration
    private javax.swing.JButton attendanceHistoryButton;
    private javax.swing.JButton calendarButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton studentReportButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration
}