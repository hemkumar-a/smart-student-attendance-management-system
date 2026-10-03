package ui;

import dao.AttendanceDAO;
import model.Attendance;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class AttendanceHistoryFrame extends javax.swing.JFrame {

    private final AttendanceDAO attendanceDAO =
            new AttendanceDAO();

public AttendanceHistoryFrame() {

    initComponents();

    setTitle(
            "Smart Student Attendance - Attendance History"
    );

    setSize(1050, 650);

    setLocationRelativeTo(null);

    loadAllAttendance();
}

    private void setupTable() {

        DefaultTableModel model =
                new DefaultTableModel(
                        new Object[][]{},
                        new String[]{
                            "Registration No",
                            "Student",
                            "Department",
                            "Date",
                            "Check-in",
                            "Check-out"
                        }
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        attendanceTable.setModel(model);
    }

    private void loadAllAttendance() {

        List<Attendance> attendanceList =
                attendanceDAO.getAllAttendance();

        displayAttendance(attendanceList);
    }

    private void loadAttendanceByDate() {

        String dateText =
                dateField.getText().trim();

        if (dateText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a date.",
                    "Date Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            LocalDate date =
                    LocalDate.parse(dateText);

            List<Attendance> attendanceList =
                    attendanceDAO.getAttendanceByDate(date);

            displayAttendance(attendanceList);

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid date format.\n"
                    + "Please use YYYY-MM-DD.",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void displayAttendance(
            List<Attendance> attendanceList) {

        DefaultTableModel model =
                (DefaultTableModel)
                        attendanceTable.getModel();

        model.setRowCount(0);

        for (Attendance attendance :
                attendanceList) {

            String checkIn = "-";
            String checkOut = "-";

            if (attendance.getCheckIn() != null) {

                checkIn =
                        attendance.getCheckIn()
                                .toLocalTime()
                                .toString();
            }

            if (attendance.getCheckOut() != null) {

                checkOut =
                        attendance.getCheckOut()
                                .toLocalTime()
                                .toString();
            }

            model.addRow(
                    new Object[]{
                        attendance.getRegistrationNo(),
                        attendance.getStudentName(),
                        attendance.getDepartment(),
                        attendance.getAttendanceDate(),
                        checkIn,
                        checkOut
                    }
            );
        }
    }

    private void loadDateButtonActionPerformed(
            java.awt.event.ActionEvent evt) {

        loadAttendanceByDate();
    }

    private void showAllButtonActionPerformed(
            java.awt.event.ActionEvent evt) {

        dateField.setText("");

        loadAllAttendance();
    }

    private void closeButtonActionPerformed(
            java.awt.event.ActionEvent evt) {

        dispose();
    }


    @SuppressWarnings("unchecked")
private void initComponents() {

    titleLabel = new javax.swing.JLabel();

    dateLabel = new javax.swing.JLabel();
    dateField = new javax.swing.JTextField();

    loadDateButton = new javax.swing.JButton();
    showAllButton = new javax.swing.JButton();

    tableScrollPane = new javax.swing.JScrollPane();
    attendanceTable = new javax.swing.JTable();

    closeButton = new javax.swing.JButton();


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
            "ATTENDANCE HISTORY"
    );


    // =========================================================
    // DATE CONTROLS
    // =========================================================

    dateLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    dateLabel.setText("DATE");


    dateField.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    13
            )
    );

    dateField.setPreferredSize(
            new java.awt.Dimension(
                    180,
                    35
            )
    );


    loadDateButton.setText(
            "LOAD DATE"
    );

    loadDateButton.setPreferredSize(
            new java.awt.Dimension(
                    120,
                    35
            )
    );

    loadDateButton.addActionListener(
            evt -> loadDateButtonActionPerformed(evt)
    );


    showAllButton.setText(
            "SHOW ALL"
    );

    showAllButton.setPreferredSize(
            new java.awt.Dimension(
                    110,
                    35
            )
    );

    showAllButton.addActionListener(
            evt -> showAllButtonActionPerformed(evt)
    );


    // =========================================================
    // TABLE
    // =========================================================

    attendanceTable.setModel(
            new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "Registration No",
                        "Student",
                        "Department",
                        "Date",
                        "Check-in",
                        "Check-out"
                    }
            ) {

                @Override
                public boolean isCellEditable(
                        int row,
                        int column) {

                    return false;
                }
            }
    );

    attendanceTable.setRowHeight(28);

    attendanceTable.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    13
            )
    );

    attendanceTable.getTableHeader().setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    attendanceTable.setSelectionMode(
            javax.swing.ListSelectionModel.SINGLE_SELECTION
    );

    tableScrollPane.setViewportView(
            attendanceTable
    );


    // =========================================================
    // CLOSE
    // =========================================================

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
            evt -> closeButtonActionPerformed(evt)
    );


    // =========================================================
    // FILTER PANEL
    // =========================================================

    javax.swing.JPanel filterPanel =
            new javax.swing.JPanel(
                    new java.awt.FlowLayout(
                            java.awt.FlowLayout.LEFT,
                            12,
                            8
                    )
            );

    filterPanel.setBorder(
            javax.swing.BorderFactory.createTitledBorder(
                    "Filter Attendance"
            )
    );

    filterPanel.add(dateLabel);
    filterPanel.add(dateField);
    filterPanel.add(loadDateButton);
    filterPanel.add(showAllButton);


    // =========================================================
    // TABLE PANEL
    // =========================================================

    javax.swing.JPanel tablePanel =
            new javax.swing.JPanel(
                    new java.awt.BorderLayout()
            );

    tablePanel.setBorder(
            javax.swing.BorderFactory.createTitledBorder(
                    "Attendance Records"
            )
    );

    tablePanel.add(
            tableScrollPane,
            java.awt.BorderLayout.CENTER
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

    closePanel.add(closeButton);


    // =========================================================
    // MAIN PANEL
    // =========================================================

    javax.swing.JPanel mainPanel =
            new javax.swing.JPanel();

    mainPanel.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(
                    20,
                    25,
                    20,
                    25
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

    mainPanel.add(titleLabel);

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(20)
    );

    mainPanel.add(filterPanel);

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(12)
    );

    mainPanel.add(
            tablePanel
    );

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(12)
    );

    mainPanel.add(closePanel);


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

            new AttendanceHistoryFrame()
                    .setVisible(true);
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField dateField;
    private javax.swing.JButton loadDateButton;
    private javax.swing.JButton showAllButton;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JTable attendanceTable;
    private javax.swing.JButton closeButton;
    // End of variables declaration
}