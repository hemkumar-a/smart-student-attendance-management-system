package ui;

import dao.StudentDAO;
import model.Student;
import service.AnalyticsService;

import javax.swing.JOptionPane;

public class AttendanceReportFrame
        extends javax.swing.JFrame {

    private final StudentDAO studentDAO =
            new StudentDAO();

    private final AnalyticsService analyticsService =
            new AnalyticsService();

    
    public AttendanceReportFrame() {

    initComponents();

    setTitle(
            "Smart Student Attendance - Student Report"
    );

    setSize(900, 620);

    setLocationRelativeTo(null);
}

    private void loadStudentReport() {

        String registrationNo =
                registrationField
                        .getText()
                        .trim();

        if (registrationNo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a registration number.",
                    "Input Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Student student =
                studentDAO.getStudentByRegistrationNo(
                        registrationNo
                );

        if (student == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student not found.",
                    "Not Found",
                    JOptionPane.WARNING_MESSAGE
            );

            clearReport();

            return;
        }

        studentValueLabel.setText(
                student.getName()
        );

        departmentValueLabel.setText(
                student.getDepartment()
        );

        int workingDays =
        analyticsService
                .getWorkingDaysForStudent(
                        student.getId()
                );
        int presentDays =
                analyticsService
                        .getPresentDaysForStudent(
                                student.getId()
                        );

        int absentDays =
                analyticsService
                        .getAbsentDaysForStudent(
                                student.getId()
                        );

        double attendancePercentage =
                analyticsService
                        .getStudentAttendancePercentage(
                                student.getId()
                        );

        workingDaysValueLabel.setText(
                String.valueOf(workingDays)
        );

        presentDaysValueLabel.setText(
                String.valueOf(presentDays)
        );

        absentDaysValueLabel.setText(
                String.valueOf(absentDays)
        );

        attendanceValueLabel.setText(
                String.format(
                        "%.2f%%",
                        attendancePercentage
                )
        );
    }

    private void clearReport() {

        studentValueLabel.setText("-");
        departmentValueLabel.setText("-");

        workingDaysValueLabel.setText("0");
        presentDaysValueLabel.setText("0");
        absentDaysValueLabel.setText("0");
        attendanceValueLabel.setText("0.00%");
    }

    private void loadButtonActionPerformed(
            java.awt.event.ActionEvent evt) {

        loadStudentReport();
    }

    private void closeButtonActionPerformed(
            java.awt.event.ActionEvent evt) {

        dispose();
    }


    @SuppressWarnings("unchecked")
private void initComponents() {

    titleLabel = new javax.swing.JLabel();

    registrationLabel =
            new javax.swing.JLabel();

    registrationField =
            new javax.swing.JTextField();

    loadButton =
            new javax.swing.JButton();

    studentLabel =
            new javax.swing.JLabel();

    studentValueLabel =
            new javax.swing.JLabel();

    departmentLabel =
            new javax.swing.JLabel();

    departmentValueLabel =
            new javax.swing.JLabel();

    workingDaysLabel =
            new javax.swing.JLabel();

    workingDaysValueLabel =
            new javax.swing.JLabel();

    presentDaysLabel =
            new javax.swing.JLabel();

    presentDaysValueLabel =
            new javax.swing.JLabel();

    absentDaysLabel =
            new javax.swing.JLabel();

    absentDaysValueLabel =
            new javax.swing.JLabel();

    attendanceLabel =
            new javax.swing.JLabel();

    attendanceValueLabel =
            new javax.swing.JLabel();

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
                    26
            )
    );

    titleLabel.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    titleLabel.setText(
            "STUDENT ATTENDANCE REPORT"
    );


    // =========================================================
    // SEARCH
    // =========================================================

    registrationLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    registrationLabel.setText(
            "REGISTRATION NO"
    );


    registrationField.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    14
            )
    );

    registrationField.setPreferredSize(
            new java.awt.Dimension(
                    300,
                    38
            )
    );


    loadButton.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    12
            )
    );

    loadButton.setText(
            "LOAD"
    );

    loadButton.setPreferredSize(
            new java.awt.Dimension(
                    110,
                    38
            )
    );

    loadButton.addActionListener(
            evt -> loadButtonActionPerformed(evt)
    );


    // =========================================================
    // STUDENT INFORMATION
    // =========================================================

    studentLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    studentLabel.setText(
            "STUDENT"
    );

    studentValueLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    14
            )
    );

    studentValueLabel.setText("-");


    departmentLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    departmentLabel.setText(
            "DEPARTMENT"
    );

    departmentValueLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    14
            )
    );

    departmentValueLabel.setText("-");


    // =========================================================
    // STATISTICS
    // =========================================================

    workingDaysLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    workingDaysLabel.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    workingDaysLabel.setText(
            "WORKING DAYS"
    );


    workingDaysValueLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    26
            )
    );

    workingDaysValueLabel.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    workingDaysValueLabel.setText("0");


    presentDaysLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    presentDaysLabel.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    presentDaysLabel.setText(
            "PRESENT DAYS"
    );


    presentDaysValueLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    26
            )
    );

    presentDaysValueLabel.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    presentDaysValueLabel.setText("0");


    absentDaysLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    absentDaysLabel.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    absentDaysLabel.setText(
            "ABSENT DAYS"
    );


    absentDaysValueLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    26
            )
    );

    absentDaysValueLabel.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    absentDaysValueLabel.setText("0");


    attendanceLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    attendanceLabel.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    attendanceLabel.setText(
            "ATTENDANCE"
    );


    attendanceValueLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    26
            )
    );

    attendanceValueLabel.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    attendanceValueLabel.setText(
            "0.00%"
    );


    // =========================================================
    // CLOSE
    // =========================================================

    closeButton.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    12
            )
    );

    closeButton.setText("CLOSE");

    closeButton.setPreferredSize(
            new java.awt.Dimension(
                    120,
                    40
            )
    );

    closeButton.addActionListener(
            evt -> closeButtonActionPerformed(evt)
    );


    // =========================================================
    // SEARCH PANEL
    // =========================================================

    javax.swing.JPanel searchPanel =
            new javax.swing.JPanel(
                    new java.awt.FlowLayout(
                            java.awt.FlowLayout.CENTER,
                            12,
                            10
                    )
            );

    searchPanel.setBorder(
            javax.swing.BorderFactory.createTitledBorder(
                    "Student Search"
            )
    );

    searchPanel.add(
            registrationLabel
    );

    searchPanel.add(
            registrationField
    );

    searchPanel.add(
            loadButton
    );


    // =========================================================
    // STUDENT INFORMATION PANEL
    // =========================================================

    javax.swing.JPanel informationPanel =
            new javax.swing.JPanel(
                    new java.awt.GridBagLayout()
            );

    informationPanel.setBorder(
            javax.swing.BorderFactory.createTitledBorder(
                    "Student Information"
            )
    );

    java.awt.GridBagConstraints infoGbc =
            new java.awt.GridBagConstraints();

    infoGbc.insets =
            new java.awt.Insets(
                    12,
                    15,
                    12,
                    15
            );

    infoGbc.fill =
            java.awt.GridBagConstraints.HORIZONTAL;


    infoGbc.gridx = 0;
    infoGbc.gridy = 0;
    infoGbc.weightx = 0;

    informationPanel.add(
            studentLabel,
            infoGbc
    );


    infoGbc.gridx = 1;
    infoGbc.weightx = 1;

    informationPanel.add(
            studentValueLabel,
            infoGbc
    );


    infoGbc.gridx = 0;
    infoGbc.gridy = 1;
    infoGbc.weightx = 0;

    informationPanel.add(
            departmentLabel,
            infoGbc
    );


    infoGbc.gridx = 1;
    infoGbc.weightx = 1;

    informationPanel.add(
            departmentValueLabel,
            infoGbc
    );


    // =========================================================
    // STAT CARDS
    // =========================================================

    javax.swing.JPanel workingPanel =
            createStatPanel(
                    workingDaysLabel,
                    workingDaysValueLabel
            );

    javax.swing.JPanel presentPanel =
            createStatPanel(
                    presentDaysLabel,
                    presentDaysValueLabel
            );

    javax.swing.JPanel absentPanel =
            createStatPanel(
                    absentDaysLabel,
                    absentDaysValueLabel
            );

    javax.swing.JPanel attendancePanel =
            createStatPanel(
                    attendanceLabel,
                    attendanceValueLabel
            );


    javax.swing.JPanel statisticsPanel =
            new javax.swing.JPanel(
                    new java.awt.GridLayout(
                            1,
                            4,
                            15,
                            0
                    )
            );

    statisticsPanel.setBorder(
            javax.swing.BorderFactory.createTitledBorder(
                    "Attendance Summary"
            )
    );

    statisticsPanel.add(
            workingPanel
    );

    statisticsPanel.add(
            presentPanel
    );

    statisticsPanel.add(
            absentPanel
    );

    statisticsPanel.add(
            attendancePanel
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
                    20,
                    30,
                    20,
                    30
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

    mainPanel.add(
            titleLabel
    );

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(
                    8
            )
    );


    javax.swing.JLabel subtitleLabel =
            new javax.swing.JLabel(
                    "View individual student attendance performance"
            );

    subtitleLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    14
            )
    );

    subtitleLabel.setAlignmentX(
            javax.swing.JComponent.CENTER_ALIGNMENT
    );

    mainPanel.add(
            subtitleLabel
    );


    mainPanel.add(
            javax.swing.Box.createVerticalStrut(
                    20
            )
    );


    mainPanel.add(
            searchPanel
    );


    mainPanel.add(
            javax.swing.Box.createVerticalStrut(
                    15
            )
    );


    mainPanel.add(
            informationPanel
    );


    mainPanel.add(
            javax.swing.Box.createVerticalStrut(
                    15
            )
    );


    mainPanel.add(
            statisticsPanel
    );


    mainPanel.add(
            javax.swing.Box.createVerticalStrut(
                    20
            )
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


// =========================================================
// CREATE STATISTIC PANEL
// =========================================================

private javax.swing.JPanel createStatPanel(
        javax.swing.JLabel title,
        javax.swing.JLabel value) {

    javax.swing.JPanel panel =
            new javax.swing.JPanel(
                    new java.awt.GridLayout(
                            2,
                            1
                    )
            );

    panel.setBorder(
            javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(
                            java.awt.Color.LIGHT_GRAY
                    ),
                    javax.swing.BorderFactory.createEmptyBorder(
                            12,
                            8,
                            12,
                            8
                    )
            )
    );

    panel.add(title);
    panel.add(value);

    return panel;
}



    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new AttendanceReportFrame()
                    .setVisible(true);
        });
    }

    // Variables declaration
    private javax.swing.JLabel absentDaysLabel;
    private javax.swing.JLabel absentDaysValueLabel;
    private javax.swing.JLabel attendanceLabel;
    private javax.swing.JLabel attendanceValueLabel;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel departmentLabel;
    private javax.swing.JLabel departmentValueLabel;
    private javax.swing.JButton loadButton;
    private javax.swing.JLabel presentDaysLabel;
    private javax.swing.JLabel presentDaysValueLabel;
    private javax.swing.JTextField registrationField;
    private javax.swing.JLabel registrationLabel;
    private javax.swing.JLabel studentLabel;
    private javax.swing.JLabel studentValueLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel workingDaysLabel;
    private javax.swing.JLabel workingDaysValueLabel;
    // End of variables declaration
}