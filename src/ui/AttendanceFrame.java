package ui;


import dao.StudentDAO;
import model.Student;
import util.CameraQRScanner;
import javax.swing.JOptionPane;

import model.AttendanceStatus;
import service.AttendanceService;

public class AttendanceFrame extends javax.swing.JFrame {

    private Student currentStudent;
private CameraQRScanner cameraQRScanner;

private final StudentDAO studentDAO =
        new StudentDAO();


private final AttendanceService attendanceService =
        new AttendanceService();    
    private void startCameraScanner() {

    cameraQRScanner =
            new CameraQRScanner();

    cameraQRScanner.startScanner(
            qrCode -> {

                qrCodeField.setText(qrCode);

                processQRCode();
            }
    );
}
    private void scanQRButtonActionPerformed(
        java.awt.event.ActionEvent evt) {

    startCameraScanner();
}
    
public AttendanceFrame() {

    initComponents();

    setTitle("Smart Student Attendance - Attendance");

    setSize(900, 600);

    setLocationRelativeTo(null);

    markAttendanceButton.setEnabled(false);

    qrCodeField.requestFocus();
}

    private void processQRCode() {

        String qrCode = qrCodeField.getText().trim();

        if (qrCode.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter or scan a QR code.",
                    "QR Code",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Student student =
                studentDAO.getStudentByQRCode(qrCode);

        if (student == null) {

            currentStudent = null;

            studentNameValueLabel.setText("-");
            registrationValueLabel.setText("-");
            departmentValueLabel.setText("-");
            statusValueLabel.setText("Student not found");

            markAttendanceButton.setEnabled(false);

            JOptionPane.showMessageDialog(
                    this,
                    "No student found for this QR code.",
                    "QR Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        currentStudent = student;

        studentNameValueLabel.setText(
                student.getName()
        );

        registrationValueLabel.setText(
                student.getRegistrationNo()
        );

        departmentValueLabel.setText(
                student.getDepartment()
        );

        statusValueLabel.setText(
                "Student found - Ready to mark attendance"
        );

        markAttendanceButton.setEnabled(true);
    }

private void markStudentAttendance() {

    if (currentStudent == null) {

        JOptionPane.showMessageDialog(
                this,
                "Please process a QR code first.",
                "Attendance",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    AttendanceStatus status =
            attendanceService.processAttendance(
                    currentStudent.getId()
            );

    switch (status) {

        case CHECKED_IN:

            statusValueLabel.setText(
                    "Checked in successfully"
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Check-in recorded successfully for "
                    + currentStudent.getName(),
                    "Attendance",
                    JOptionPane.INFORMATION_MESSAGE
            );

            break;


        case CHECKED_OUT:

            statusValueLabel.setText(
                    "Checked out successfully"
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Check-out recorded successfully for "
                    + currentStudent.getName(),
                    "Attendance",
                    JOptionPane.INFORMATION_MESSAGE
            );

            break;


        case ALREADY_COMPLETED:

            statusValueLabel.setText(
                    "Attendance already completed today"
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Attendance has already been completed for "
                    + currentStudent.getName()
                    + " today.",
                    "Attendance",
                    JOptionPane.INFORMATION_MESSAGE
            );

            break;


        case NOT_MARKED:

            statusValueLabel.setText(
                    "Unable to process attendance"
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to process attendance.",
                    "Attendance",
                    JOptionPane.ERROR_MESSAGE
            );

            break;
    }
}
   private void clearAttendanceForm() {

        qrCodeField.setText("");

        studentNameValueLabel.setText("-");
        registrationValueLabel.setText("-");
        departmentValueLabel.setText("-");
        statusValueLabel.setText("Waiting for QR...");

        currentStudent = null;

        markAttendanceButton.setEnabled(false);

        qrCodeField.requestFocus();
    }


@SuppressWarnings("unchecked")
private void initComponents() {

    titleLabel = new javax.swing.JLabel();

    qrLabel = new javax.swing.JLabel();
    qrCodeField = new javax.swing.JTextField();
    processQRButton = new javax.swing.JButton();
    scanQRButton = new javax.swing.JButton();

    studentLabel = new javax.swing.JLabel();
    studentNameValueLabel = new javax.swing.JLabel();

    registrationLabel = new javax.swing.JLabel();
    registrationValueLabel = new javax.swing.JLabel();

    departmentLabel = new javax.swing.JLabel();
    departmentValueLabel = new javax.swing.JLabel();

    statusLabel = new javax.swing.JLabel();
    statusValueLabel = new javax.swing.JLabel();

    markAttendanceButton = new javax.swing.JButton();
    clearButton = new javax.swing.JButton();


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
            "ATTENDANCE MANAGEMENT"
    );


    // =========================================================
    // QR SECTION
    // =========================================================

    qrLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    14
            )
    );

    qrLabel.setText("QR CODE");


    qrCodeField.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    14
            )
    );

    qrCodeField.setPreferredSize(
            new java.awt.Dimension(
                    350,
                    35
            )
    );


    processQRButton.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    12
            )
    );

    processQRButton.setText(
            "PROCESS QR"
    );

    processQRButton.setPreferredSize(
            new java.awt.Dimension(
                    130,
                    35
            )
    );

    processQRButton.addActionListener(
            evt -> processQRCode()
    );


    scanQRButton.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    12
            )
    );

    scanQRButton.setText(
            "SCAN QR"
    );

    scanQRButton.setPreferredSize(
            new java.awt.Dimension(
                    110,
                    35
            )
    );

    scanQRButton.addActionListener(
            evt -> startCameraScanner()
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

    studentNameValueLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    14
            )
    );

    studentNameValueLabel.setText("-");


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

    registrationValueLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    14
            )
    );

    registrationValueLabel.setText("-");


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


    statusLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    statusLabel.setText(
            "STATUS"
    );

    statusValueLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    14
            )
    );

    statusValueLabel.setText(
            "Waiting for QR..."
    );


    // =========================================================
    // ATTENDANCE BUTTON
    // =========================================================

    markAttendanceButton.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    markAttendanceButton.setText(
            "MARK ATTENDANCE"
    );

    markAttendanceButton.setPreferredSize(
            new java.awt.Dimension(
                    190,
                    45
            )
    );

    markAttendanceButton.addActionListener(
            evt -> markStudentAttendance()
    );


    // =========================================================
    // CLEAR BUTTON
    // =========================================================

    clearButton.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    clearButton.setText(
            "CLEAR"
    );

    clearButton.setPreferredSize(
            new java.awt.Dimension(
                    110,
                    45
            )
    );

    clearButton.addActionListener(
            evt -> clearAttendanceForm()
    );


    // =========================================================
    // QR PANEL
    // =========================================================

    javax.swing.JPanel qrPanel =
            new javax.swing.JPanel(
                    new java.awt.GridBagLayout()
            );

    qrPanel.setBorder(
            javax.swing.BorderFactory.createTitledBorder(
                    "QR Attendance"
            )
    );

    java.awt.GridBagConstraints qrGbc =
            new java.awt.GridBagConstraints();

    qrGbc.insets =
            new java.awt.Insets(
                    10,
                    10,
                    10,
                    10
            );

    qrGbc.fill =
            java.awt.GridBagConstraints.HORIZONTAL;


    // QR label
    qrGbc.gridx = 0;
    qrGbc.gridy = 0;
    qrGbc.weightx = 0;

    qrPanel.add(
            qrLabel,
            qrGbc
    );


    // QR field
    qrGbc.gridx = 1;
    qrGbc.weightx = 1;

    qrPanel.add(
            qrCodeField,
            qrGbc
    );


    // Process button
    qrGbc.gridx = 2;
    qrGbc.weightx = 0;

    qrPanel.add(
            processQRButton,
            qrGbc
    );


    // Scan button
    qrGbc.gridx = 3;

    qrPanel.add(
            scanQRButton,
            qrGbc
    );


    // =========================================================
    // STUDENT INFORMATION PANEL
    // =========================================================

    javax.swing.JPanel studentPanel =
            new javax.swing.JPanel(
                    new java.awt.GridBagLayout()
            );

    studentPanel.setBorder(
            javax.swing.BorderFactory.createTitledBorder(
                    "Student Information"
            )
    );

    java.awt.GridBagConstraints studentGbc =
            new java.awt.GridBagConstraints();

    studentGbc.insets =
            new java.awt.Insets(
                    10,
                    15,
                    10,
                    15
            );

    studentGbc.fill =
            java.awt.GridBagConstraints.HORIZONTAL;


    // Student
    studentGbc.gridx = 0;
    studentGbc.gridy = 0;
    studentGbc.weightx = 0.2;

    studentPanel.add(
            studentLabel,
            studentGbc
    );

    studentGbc.gridx = 1;
    studentGbc.weightx = 0.8;

    studentPanel.add(
            studentNameValueLabel,
            studentGbc
    );


    // Registration
    studentGbc.gridx = 0;
    studentGbc.gridy = 1;
    studentGbc.weightx = 0.2;

    studentPanel.add(
            registrationLabel,
            studentGbc
    );

    studentGbc.gridx = 1;
    studentGbc.weightx = 0.8;

    studentPanel.add(
            registrationValueLabel,
            studentGbc
    );


    // Department
    studentGbc.gridx = 0;
    studentGbc.gridy = 2;
    studentGbc.weightx = 0.2;

    studentPanel.add(
            departmentLabel,
            studentGbc
    );

    studentGbc.gridx = 1;
    studentGbc.weightx = 0.8;

    studentPanel.add(
            departmentValueLabel,
            studentGbc
    );


    // Status
    studentGbc.gridx = 0;
    studentGbc.gridy = 3;
    studentGbc.weightx = 0.2;

    studentPanel.add(
            statusLabel,
            studentGbc
    );

    studentGbc.gridx = 1;
    studentGbc.weightx = 0.8;

    studentPanel.add(
            statusValueLabel,
            studentGbc
    );


    // =========================================================
    // ACTION PANEL
    // =========================================================

    javax.swing.JPanel actionPanel =
            new javax.swing.JPanel(
                    new java.awt.FlowLayout(
                            java.awt.FlowLayout.CENTER,
                            15,
                            5
                    )
            );

    actionPanel.add(
            markAttendanceButton
    );

    actionPanel.add(
            clearButton
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
                    25,
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
            javax.swing.Box.createVerticalStrut(8)
    );


    javax.swing.JLabel subtitleLabel =
            new javax.swing.JLabel(
                    "Scan a student QR code to record attendance"
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
            javax.swing.Box.createVerticalStrut(25)
    );


    mainPanel.add(
            qrPanel
    );

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(20)
    );


    mainPanel.add(
            studentPanel
    );

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(25)
    );


    mainPanel.add(
            actionPanel
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
            new AttendanceFrame().setVisible(true);
        });
    }

    // Variables declaration
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel departmentLabel;
    private javax.swing.JLabel departmentValueLabel;
    private javax.swing.JButton markAttendanceButton;
    private javax.swing.JButton processQRButton;
    private javax.swing.JLabel qrLabel;
    private javax.swing.JTextField qrCodeField;
    private javax.swing.JLabel registrationLabel;
    private javax.swing.JLabel registrationValueLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel statusValueLabel;
    private javax.swing.JLabel studentLabel;
    private javax.swing.JLabel studentNameValueLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton scanQRButton;
}