package ui;

import dao.CalendarDAO;
import model.AcademicCalendar;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CalendarFrame extends javax.swing.JFrame {

    private final CalendarDAO calendarDAO =
            new CalendarDAO();

    private int selectedId = -1;

public CalendarFrame() {

    initComponents();

    setTitle("Academic Calendar");
    setSize(900, 600);
    setLocationRelativeTo(null);

    setupTable();
    loadCalendarEntries();
}

    private void setupTable() {

        DefaultTableModel model =
                new DefaultTableModel(
                        new Object[][]{},
                        new String[]{
                            "Date",
                            "Working Day",
                            "Description"
                        }
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        calendarTable.setModel(model);

        calendarTable.getSelectionModel()
                .addListSelectionListener(
                        event -> {

                            if (!event.getValueIsAdjusting()) {
                                loadSelectedEntry();
                            }
                        }
                );
    }

    private void loadCalendarEntries() {

        List<AcademicCalendar> entries =
                calendarDAO.getAllCalendarEntries();

        DefaultTableModel model =
                (DefaultTableModel)
                        calendarTable.getModel();

        model.setRowCount(0);

        for (AcademicCalendar entry : entries) {

            model.addRow(
                    new Object[]{
                        entry.getCalendarDate(),
                        entry.isWorkingDay()
                                ? "YES"
                                : "NO",
                        entry.getDescription()
                    }
            );
        }
    }

    private void loadSelectedEntry() {

        int selectedRow =
                calendarTable.getSelectedRow();

        if (selectedRow == -1) {
            return;
        }

        String dateText =
                calendarTable
                        .getValueAt(selectedRow, 0)
                        .toString();

        String workingDay =
                calendarTable
                        .getValueAt(selectedRow, 1)
                        .toString();

        String description =
                calendarTable
                        .getValueAt(selectedRow, 2)
                        .toString();

        dateField.setText(dateText);

        workingDayComboBox.setSelectedItem(
                workingDay
        );

        descriptionField.setText(
                description
        );

        AcademicCalendar entry =
                calendarDAO.getCalendarEntry(
                        LocalDate.parse(dateText)
                );

        if (entry != null) {
            selectedId = entry.getId();
        }
    }

    private LocalDate getDateFromField() {

        String dateText =
                dateField.getText().trim();

        if (dateText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a date.",
                    "Date Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return null;
        }

        try {

            return LocalDate.parse(dateText);

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid date format.\n"
                    + "Please use YYYY-MM-DD.",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );

            return null;
        }
    }

    private AcademicCalendar getCalendarFromFields() {

        LocalDate date =
                getDateFromField();

        if (date == null) {
            return null;
        }

        boolean workingDay =
                "YES".equals(
                        workingDayComboBox
                                .getSelectedItem()
                );

        String description =
                descriptionField
                        .getText()
                        .trim();

        AcademicCalendar calendar =
                new AcademicCalendar();

        calendar.setCalendarDate(date);
        calendar.setWorkingDay(workingDay);
        calendar.setDescription(description);

        return calendar;
    }

    private void addCalendarEntry() {

        AcademicCalendar calendar =
                getCalendarFromFields();

        if (calendar == null) {
            return;
        }

        if (calendarDAO.getCalendarEntry(
                calendar.getCalendarDate()) != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "An entry already exists for this date.",
                    "Duplicate Date",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        boolean success =
                calendarDAO.addCalendarEntry(calendar);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Calendar entry added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadCalendarEntries();
            clearFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to add calendar entry.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void updateCalendarEntry() {

        AcademicCalendar calendar =
                getCalendarFromFields();

        if (calendar == null) {
            return;
        }

        if (calendarDAO.getCalendarEntry(
                calendar.getCalendarDate()) == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No calendar entry found for this date.",
                    "Not Found",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        boolean success =
                calendarDAO.updateCalendarEntry(
                        calendar
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Calendar entry updated successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadCalendarEntries();
            clearFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update calendar entry.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void deleteCalendarEntry() {

        LocalDate date =
                getDateFromField();

        if (date == null) {
            return;
        }

        AcademicCalendar existing =
                calendarDAO.getCalendarEntry(date);

        if (existing == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No calendar entry found for this date.",
                    "Not Found",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int confirmation =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete calendar entry for "
                        + date + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (confirmation !=
                JOptionPane.YES_OPTION) {

            return;
        }

        boolean success =
                calendarDAO.deleteCalendarEntry(date);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Calendar entry deleted successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadCalendarEntries();
            clearFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to delete calendar entry.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void clearFields() {

        dateField.setText("");

        workingDayComboBox.setSelectedIndex(0);

        descriptionField.setText("");

        calendarTable.clearSelection();

        selectedId = -1;
    }

    private void addButtonActionPerformed(
            java.awt.event.ActionEvent evt) {

        addCalendarEntry();
    }

    private void updateButtonActionPerformed(
            java.awt.event.ActionEvent evt) {

        updateCalendarEntry();
    }

    private void deleteButtonActionPerformed(
            java.awt.event.ActionEvent evt) {

        deleteCalendarEntry();
    }

    private void clearButtonActionPerformed(
            java.awt.event.ActionEvent evt) {

        clearFields();
    }


    @SuppressWarnings("unchecked")
private void initComponents() {

    titleLabel = new javax.swing.JLabel();

    dateLabel = new javax.swing.JLabel();
    dateField = new javax.swing.JTextField();

    workingDayLabel = new javax.swing.JLabel();
    workingDayComboBox = new javax.swing.JComboBox<>();

    descriptionLabel = new javax.swing.JLabel();
    descriptionField = new javax.swing.JTextField();

    addButton = new javax.swing.JButton();
    updateButton = new javax.swing.JButton();
    deleteButton = new javax.swing.JButton();
    clearButton = new javax.swing.JButton();

    tableScrollPane = new javax.swing.JScrollPane();
    calendarTable = new javax.swing.JTable();


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
            "ACADEMIC CALENDAR"
    );


    // =========================================================
    // FORM LABELS
    // =========================================================

    dateLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    dateLabel.setText("DATE");


    workingDayLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    workingDayLabel.setText(
            "WORKING DAY"
    );


    descriptionLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    descriptionLabel.setText(
            "DESCRIPTION"
    );


    // =========================================================
    // INPUT FIELDS
    // =========================================================

    dateField.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    14
            )
    );

    dateField.setToolTipText(
            "Format: YYYY-MM-DD"
    );

    dateField.setPreferredSize(
            new java.awt.Dimension(
                    180,
                    36
            )
    );


    workingDayComboBox.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    14
            )
    );

    workingDayComboBox.setModel(
            new javax.swing.DefaultComboBoxModel<>(
                    new String[]{
                        "YES",
                        "NO"
                    }
            )
    );

    workingDayComboBox.setPreferredSize(
            new java.awt.Dimension(
                    180,
                    36
            )
    );


    descriptionField.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    14
            )
    );


    // =========================================================
    // BUTTONS
    // =========================================================

    addButton.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    12
            )
    );

    addButton.setText("ADD");

    addButton.setPreferredSize(
            new java.awt.Dimension(
                    110,
                    40
            )
    );

    addButton.addActionListener(
            evt -> addButtonActionPerformed(evt)
    );


    updateButton.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    12
            )
    );

    updateButton.setText("UPDATE");

    updateButton.setPreferredSize(
            new java.awt.Dimension(
                    110,
                    40
            )
    );

    updateButton.addActionListener(
            evt -> updateButtonActionPerformed(evt)
    );


    deleteButton.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    12
            )
    );

    deleteButton.setText("DELETE");

    deleteButton.setPreferredSize(
            new java.awt.Dimension(
                    110,
                    40
            )
    );

    deleteButton.addActionListener(
            evt -> deleteButtonActionPerformed(evt)
    );


    clearButton.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    12
            )
    );

    clearButton.setText("CLEAR");

    clearButton.setPreferredSize(
            new java.awt.Dimension(
                    110,
                    40
            )
    );

    clearButton.addActionListener(
            evt -> clearButtonActionPerformed(evt)
    );


    // =========================================================
    // TABLE
    // =========================================================

    calendarTable.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    13
            )
    );

    calendarTable.setRowHeight(30);

    calendarTable.setSelectionMode(
            javax.swing.ListSelectionModel.SINGLE_SELECTION
    );

    calendarTable.getTableHeader().setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    tableScrollPane.setViewportView(
            calendarTable
    );


    // =========================================================
    // ENTRY PANEL
    // =========================================================

    javax.swing.JPanel entryPanel =
            new javax.swing.JPanel(
                    new java.awt.GridBagLayout()
            );

    entryPanel.setBorder(
            javax.swing.BorderFactory.createTitledBorder(
                    "Calendar Entry"
            )
    );

    java.awt.GridBagConstraints gbc =
            new java.awt.GridBagConstraints();

    gbc.insets =
            new java.awt.Insets(
                    8,
                    12,
                    8,
                    12
            );

    gbc.fill =
            java.awt.GridBagConstraints.HORIZONTAL;


    // DATE
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;

    entryPanel.add(
            dateLabel,
            gbc
    );

    gbc.gridx = 1;
    gbc.weightx = 0.3;

    entryPanel.add(
            dateField,
            gbc
    );


    // WORKING DAY
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 0;

    entryPanel.add(
            workingDayLabel,
            gbc
    );

    gbc.gridx = 1;
    gbc.weightx = 0.3;

    entryPanel.add(
            workingDayComboBox,
            gbc
    );


    // DESCRIPTION
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 0;

    entryPanel.add(
            descriptionLabel,
            gbc
    );

    gbc.gridx = 1;
    gbc.weightx = 1.0;

    entryPanel.add(
            descriptionField,
            gbc
    );


    // =========================================================
    // ACTION PANEL
    // =========================================================

    javax.swing.JPanel actionPanel =
            new javax.swing.JPanel(
                    new java.awt.FlowLayout(
                            java.awt.FlowLayout.CENTER,
                            12,
                            8
                    )
            );

    actionPanel.add(addButton);
    actionPanel.add(updateButton);
    actionPanel.add(deleteButton);
    actionPanel.add(clearButton);


    // =========================================================
    // TABLE PANEL
    // =========================================================

    javax.swing.JPanel tablePanel =
            new javax.swing.JPanel(
                    new java.awt.BorderLayout()
            );

    tablePanel.setBorder(
            javax.swing.BorderFactory.createTitledBorder(
                    "Calendar Entries"
            )
    );

    tablePanel.add(
            tableScrollPane,
            java.awt.BorderLayout.CENTER
    );


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

    mainPanel.add(entryPanel);

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(8)
    );

    mainPanel.add(actionPanel);

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(10)
    );

    mainPanel.add(tablePanel);


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
            new CalendarFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton addButton;
    private javax.swing.JTable calendarTable;
    private javax.swing.JButton clearButton;
    private javax.swing.JTextField dateField;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField descriptionField;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton updateButton;
    private javax.swing.JComboBox<String> workingDayComboBox;
    private javax.swing.JLabel workingDayLabel;
    // End of variables declaration
}