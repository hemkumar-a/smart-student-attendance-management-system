package ui;

import dao.StudentDAO;
import model.Student;
import util.QRGenerator;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.awt.image.BufferedImage;
import java.util.List;

public class StudentFrame extends javax.swing.JFrame {

    private final StudentDAO studentDAO = new StudentDAO();
    private int selectedStudentId = -1;


    public StudentFrame() {

    initComponents();

    setTitle("Smart Student Attendance - Student Management");

    setSize(1100, 760);

    setLocationRelativeTo(null);

    loadStudents();
}

    // =========================================================
    // LOAD ALL STUDENTS
    // =========================================================

    private void loadStudents() {

        List<Student> students = studentDAO.getAllStudents();

        DefaultTableModel model =
                (DefaultTableModel) studentTable.getModel();

        model.setRowCount(0);

        for (Student student : students) {

            model.addRow(new Object[]{
                student.getId(),
                student.getRegistrationNo(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getGender(),
                student.getDepartment(),
                student.getYear(),
                student.getSection(),
                student.getQrCode()
            });
        }
    }

    // =========================================================
    // CLEAR FORM
    // =========================================================

    private void clearFields() {

        registrationField.setText("");
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        departmentField.setText("");
        sectionField.setText("");

        genderComboBox.setSelectedIndex(0);
        yearComboBox.setSelectedIndex(0);

        qrCodeValueLabel.setText("QR: -");

        selectedStudentId = -1;

        studentTable.clearSelection();
    }

    // =========================================================
    // GENERATE QR VALUE
    // =========================================================

    private String generateQRValue() {

        String registrationNo =
                registrationField.getText().trim();

        if (registrationNo.isEmpty()) {
            return "";
        }

        return "STUDENT-" + registrationNo.toUpperCase();
    }

    // =========================================================
    // ADD STUDENT
    // =========================================================

    private void addStudent() {

        if (!validateFields()) {
            return;
        }

        String qrCode = generateQRValue();

        Student student = new Student(
                0,
                registrationField.getText().trim(),
                nameField.getText().trim(),
                emailField.getText().trim(),
                phoneField.getText().trim(),
                genderComboBox.getSelectedItem().toString(),
                departmentField.getText().trim(),
                Integer.parseInt(
                        yearComboBox.getSelectedItem().toString()
                ),
                sectionField.getText().trim(),
                qrCode
        );

        boolean success = studentDAO.addStudent(student);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student added successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadStudents();
            clearFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to add student.\n"
                    + "Registration number or QR code may already exist.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // UPDATE STUDENT
    // =========================================================

    private void updateStudent() {

        if (selectedStudentId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student from the table first."
            );

            return;
        }

        if (!validateFields()) {
            return;
        }

        String qrCode = generateQRValue();

        Student student = new Student(
                selectedStudentId,
                registrationField.getText().trim(),
                nameField.getText().trim(),
                emailField.getText().trim(),
                phoneField.getText().trim(),
                genderComboBox.getSelectedItem().toString(),
                departmentField.getText().trim(),
                Integer.parseInt(
                        yearComboBox.getSelectedItem().toString()
                ),
                sectionField.getText().trim(),
                qrCode
        );

        boolean success = studentDAO.updateStudent(student);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student updated successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadStudents();
            clearFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to update student.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // DELETE STUDENT
    // =========================================================

    private void deleteStudent() {

        if (selectedStudentId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student from the table first."
            );

            return;
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this student?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        boolean success =
                studentDAO.deleteStudent(selectedStudentId);

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student deleted successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadStudents();
            clearFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to delete student.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // SEARCH STUDENTS
    // =========================================================

    private void searchStudents() {

        String keyword =
                searchField.getText().trim();

        if (keyword.isEmpty()) {
            loadStudents();
            return;
        }

        List<Student> students =
                studentDAO.searchStudents(keyword);

        DefaultTableModel model =
                (DefaultTableModel) studentTable.getModel();

        model.setRowCount(0);

        for (Student student : students) {

            model.addRow(new Object[]{
                student.getId(),
                student.getRegistrationNo(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getGender(),
                student.getDepartment(),
                student.getYear(),
                student.getSection(),
                student.getQrCode()
            });
        }
    }

// =========================================================
// VALIDATE FORM
// =========================================================

private boolean validateFields() {

    String registrationNo =
            registrationField.getText().trim();

    String name =
            nameField.getText().trim();

    String email =
            emailField.getText().trim();

    String phone =
            phoneField.getText().trim();

    String department =
            departmentField.getText().trim();

    String section =
            sectionField.getText().trim();


    // Registration Number
    if (registrationNo.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Registration number is required.",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
        );

        registrationField.requestFocus();
        return false;
    }

    if (registrationNo.length() > 50) {

        JOptionPane.showMessageDialog(
                this,
                "Registration number must not exceed 50 characters.",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
        );

        registrationField.requestFocus();
        return false;
    }


    // Student Name
    if (name.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Student name is required.",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
        );

        nameField.requestFocus();
        return false;
    }

    if (name.length() > 100) {

        JOptionPane.showMessageDialog(
                this,
                "Student name must not exceed 100 characters.",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
        );

        nameField.requestFocus();
        return false;
    }

    if (!name.matches("[A-Za-z .'-]+")) {

        JOptionPane.showMessageDialog(
                this,
                "Student name can contain only letters, spaces, dots, apostrophes and hyphens.",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
        );

        nameField.requestFocus();
        return false;
    }


    // Email - optional
    if (!email.isEmpty()) {

        if (email.length() > 150) {

            JOptionPane.showMessageDialog(
                    this,
                    "Email must not exceed 150 characters.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            emailField.requestFocus();
            return false;
        }

        if (!email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid email address.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            emailField.requestFocus();
            return false;
        }
    }


    // Phone - optional
    if (!phone.isEmpty()) {

        if (phone.length() > 20) {

            JOptionPane.showMessageDialog(
                    this,
                    "Phone number must not exceed 20 characters.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            phoneField.requestFocus();
            return false;
        }

        if (!phone.matches("[0-9+()\\- ]{7,20}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid phone number.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            phoneField.requestFocus();
            return false;
        }
    }


    // Department
    if (department.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Department is required.",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
        );

        departmentField.requestFocus();
        return false;
    }

    if (department.length() > 100) {

        JOptionPane.showMessageDialog(
                this,
                "Department must not exceed 100 characters.",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
        );

        departmentField.requestFocus();
        return false;
    }


    // Section
    if (section.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Section is required.",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
        );

        sectionField.requestFocus();
        return false;
    }

    if (section.length() > 20) {

        JOptionPane.showMessageDialog(
                this,
                "Section must not exceed 20 characters.",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
        );

        sectionField.requestFocus();
        return false;
    }


    return true;
}
   
    // =========================================================
    // TABLE ROW SELECTION
    // =========================================================

    private void studentTableMouseClicked(
            java.awt.event.MouseEvent evt) {

        int row = studentTable.getSelectedRow();

        if (row == -1) {
            return;
        }

        selectedStudentId =
                Integer.parseInt(
                        studentTable.getValueAt(row, 0).toString()
                );

        registrationField.setText(
                studentTable.getValueAt(row, 1).toString()
        );

        nameField.setText(
                studentTable.getValueAt(row, 2).toString()
        );

        emailField.setText(
                studentTable.getValueAt(row, 3).toString()
        );

        phoneField.setText(
                studentTable.getValueAt(row, 4).toString()
        );

        genderComboBox.setSelectedItem(
                studentTable.getValueAt(row, 5).toString()
        );

        departmentField.setText(
                studentTable.getValueAt(row, 6).toString()
        );

        yearComboBox.setSelectedItem(
                studentTable.getValueAt(row, 7).toString()
        );

        sectionField.setText(
                studentTable.getValueAt(row, 8).toString()
        );

        qrCodeValueLabel.setText(
                "QR: " +
                studentTable.getValueAt(row, 9).toString()
        );
    }

    // =========================================================
    // GENERATED GUI CODE
    // =========================================================

  @SuppressWarnings("unchecked")
private void initComponents() {

    titleLabel = new javax.swing.JLabel();

    registrationLabel = new javax.swing.JLabel();
    registrationField = new javax.swing.JTextField();

    nameLabel = new javax.swing.JLabel();
    nameField = new javax.swing.JTextField();

    emailLabel = new javax.swing.JLabel();
    emailField = new javax.swing.JTextField();

    phoneLabel = new javax.swing.JLabel();
    phoneField = new javax.swing.JTextField();

    genderLabel = new javax.swing.JLabel();
    genderComboBox = new javax.swing.JComboBox<>();

    departmentLabel = new javax.swing.JLabel();
    departmentField = new javax.swing.JTextField();

    yearLabel = new javax.swing.JLabel();
    yearComboBox = new javax.swing.JComboBox<>();

    sectionLabel = new javax.swing.JLabel();
    sectionField = new javax.swing.JTextField();

    qrCodeValueLabel = new javax.swing.JLabel();
    qrPreviewLabel = new javax.swing.JLabel();
    generateQRButton = new javax.swing.JButton();

    addButton = new javax.swing.JButton();
    updateButton = new javax.swing.JButton();
    deleteButton = new javax.swing.JButton();
    clearButton = new javax.swing.JButton();

    searchLabel = new javax.swing.JLabel();
    searchField = new javax.swing.JTextField();
    searchButton = new javax.swing.JButton();

    scrollPane = new javax.swing.JScrollPane();
    studentTable = new javax.swing.JTable();


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
            "STUDENT MANAGEMENT"
    );


    // =========================================================
    // FORM LABELS
    // =========================================================

    registrationLabel.setText(
            "Registration No:"
    );

    nameLabel.setText("Name:");

    emailLabel.setText("Email:");

    phoneLabel.setText("Phone:");

    genderLabel.setText("Gender:");

    departmentLabel.setText("Department:");

    yearLabel.setText("Year:");

    sectionLabel.setText("Section:");


    // =========================================================
    // COMBO BOXES
    // =========================================================

    genderComboBox.setModel(
            new javax.swing.DefaultComboBoxModel<>(
                    new String[]{
                        "Male",
                        "Female",
                        "Other"
                    }
            )
    );

    yearComboBox.setModel(
            new javax.swing.DefaultComboBoxModel<>(
                    new String[]{
                        "1",
                        "2",
                        "3",
                        "4"
                    }
            )
    );


    // =========================================================
    // QR PANEL
    // =========================================================

    qrPreviewLabel.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    qrPreviewLabel.setVerticalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    qrPreviewLabel.setBorder(
            javax.swing.BorderFactory.createTitledBorder(
                    "QR Preview"
            )
    );

    qrPreviewLabel.setPreferredSize(
            new java.awt.Dimension(220, 220)
    );

    qrCodeValueLabel.setText("QR: -");

    qrCodeValueLabel.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    generateQRButton.setText(
            "GENERATE QR"
    );


    // =========================================================
    // BUTTONS
    // =========================================================

    addButton.setText("ADD");

    updateButton.setText("UPDATE");

    deleteButton.setText("DELETE");

    clearButton.setText("CLEAR");

    searchButton.setText("SEARCH");


    // =========================================================
    // ACTION LISTENERS
    // =========================================================

    addButton.addActionListener(
            evt -> addStudent()
    );

    updateButton.addActionListener(
            evt -> updateStudent()
    );

    deleteButton.addActionListener(
            evt -> deleteStudent()
    );

    clearButton.addActionListener(
            evt -> clearFields()
    );

    searchButton.addActionListener(
            evt -> searchStudents()
    );

    generateQRButton.addActionListener(
            evt -> generateStudentQR()
    );


    // =========================================================
    // TABLE
    // =========================================================

    studentTable.setModel(
            new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "ID",
                        "Registration No",
                        "Name",
                        "Email",
                        "Phone",
                        "Gender",
                        "Department",
                        "Year",
                        "Section",
                        "QR Code"
                    }
            ) {

                Class<?>[] types = new Class[]{
                    java.lang.Integer.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.Integer.class,
                    java.lang.String.class,
                    java.lang.String.class
                };

                @Override
                public Class<?> getColumnClass(
                        int columnIndex) {

                    return types[columnIndex];
                }

                @Override
                public boolean isCellEditable(
                        int rowIndex,
                        int columnIndex) {

                    return false;
                }
            }
    );

    studentTable.setSelectionMode(
            javax.swing.ListSelectionModel.SINGLE_SELECTION
    );

    studentTable.setRowHeight(28);

    studentTable.setAutoResizeMode(
            javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN
    );

    studentTable.getTableHeader().setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    studentTable.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    13
            )
    );

    studentTable.addMouseListener(
            new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked(
                        java.awt.event.MouseEvent evt) {

                    studentTableMouseClicked(evt);
                }
            }
    );

    scrollPane.setViewportView(
            studentTable
    );


    // =========================================================
    // STUDENT DETAILS PANEL
    // =========================================================

    javax.swing.JPanel detailsPanel =
            new javax.swing.JPanel(
                    new java.awt.GridBagLayout()
            );

    detailsPanel.setBorder(
            javax.swing.BorderFactory.createTitledBorder(
                    "Student Details"
            )
    );

    java.awt.GridBagConstraints gbc =
            new java.awt.GridBagConstraints();

    gbc.insets =
            new java.awt.Insets(
                    7,
                    8,
                    7,
                    8
            );

    gbc.fill =
            java.awt.GridBagConstraints.HORIZONTAL;


    // Registration
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0;

    detailsPanel.add(
            registrationLabel,
            gbc
    );

    gbc.gridx = 1;
    gbc.weightx = 1;

    detailsPanel.add(
            registrationField,
            gbc
    );


    // Name
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 0;

    detailsPanel.add(
            nameLabel,
            gbc
    );

    gbc.gridx = 1;
    gbc.weightx = 1;

    detailsPanel.add(
            nameField,
            gbc
    );


    // Email
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 0;

    detailsPanel.add(
            emailLabel,
            gbc
    );

    gbc.gridx = 1;
    gbc.weightx = 1;

    detailsPanel.add(
            emailField,
            gbc
    );


    // Phone
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weightx = 0;

    detailsPanel.add(
            phoneLabel,
            gbc
    );

    gbc.gridx = 1;
    gbc.weightx = 1;

    detailsPanel.add(
            phoneField,
            gbc
    );


    // Gender
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.weightx = 0;

    detailsPanel.add(
            genderLabel,
            gbc
    );

    gbc.gridx = 1;
    gbc.weightx = 1;

    detailsPanel.add(
            genderComboBox,
            gbc
    );


    // Department
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.weightx = 0;

    detailsPanel.add(
            departmentLabel,
            gbc
    );

    gbc.gridx = 1;
    gbc.weightx = 1;

    detailsPanel.add(
            departmentField,
            gbc
    );


    // Year
    gbc.gridx = 0;
    gbc.gridy = 6;
    gbc.weightx = 0;

    detailsPanel.add(
            yearLabel,
            gbc
    );

    gbc.gridx = 1;
    gbc.weightx = 1;

    detailsPanel.add(
            yearComboBox,
            gbc
    );


    // Section
    gbc.gridx = 0;
    gbc.gridy = 7;
    gbc.weightx = 0;

    detailsPanel.add(
            sectionLabel,
            gbc
    );

    gbc.gridx = 1;
    gbc.weightx = 1;

    detailsPanel.add(
            sectionField,
            gbc
    );


    // =========================================================
    // QR PANEL
    // =========================================================

    javax.swing.JPanel qrPanel =
            new javax.swing.JPanel();

    qrPanel.setBorder(
            javax.swing.BorderFactory.createTitledBorder(
                    "QR Code"
            )
    );

    qrPanel.setLayout(
            new javax.swing.BoxLayout(
                    qrPanel,
                    javax.swing.BoxLayout.Y_AXIS
            )
    );

    qrPreviewLabel.setAlignmentX(
            javax.swing.JComponent.CENTER_ALIGNMENT
    );

    qrCodeValueLabel.setAlignmentX(
            javax.swing.JComponent.CENTER_ALIGNMENT
    );

    generateQRButton.setAlignmentX(
            javax.swing.JComponent.CENTER_ALIGNMENT
    );

    qrPanel.add(qrPreviewLabel);

    qrPanel.add(
            javax.swing.Box.createVerticalStrut(8)
    );

    qrPanel.add(qrCodeValueLabel);

    qrPanel.add(
            javax.swing.Box.createVerticalStrut(10)
    );

    qrPanel.add(generateQRButton);


    // =========================================================
    // TOP PANEL
    // =========================================================

    javax.swing.JPanel topPanel =
            new javax.swing.JPanel(
                    new java.awt.GridBagLayout()
            );

    java.awt.GridBagConstraints topGbc =
            new java.awt.GridBagConstraints();

    topGbc.insets =
            new java.awt.Insets(5, 5, 5, 5);

    topGbc.fill =
            java.awt.GridBagConstraints.BOTH;


    topGbc.gridx = 0;
    topGbc.gridy = 0;
    topGbc.weightx = 0.65;
    topGbc.weighty = 1.0;

    topPanel.add(
            detailsPanel,
            topGbc
    );


    topGbc.gridx = 1;
    topGbc.weightx = 0.35;

    topPanel.add(
            qrPanel,
            topGbc
    );


    // =========================================================
    // ACTION PANEL
    // =========================================================

    javax.swing.JPanel actionPanel =
            new javax.swing.JPanel(
                    new java.awt.FlowLayout(
                            java.awt.FlowLayout.CENTER,
                            12,
                            5
                    )
            );

    actionPanel.add(addButton);
    actionPanel.add(updateButton);
    actionPanel.add(deleteButton);
    actionPanel.add(clearButton);


    // =========================================================
    // SEARCH PANEL
    // =========================================================

    javax.swing.JPanel searchPanel =
            new javax.swing.JPanel(
                    new java.awt.BorderLayout(10, 5)
            );

    searchPanel.setBorder(
            javax.swing.BorderFactory.createTitledBorder(
                    "Search Students"
            )
    );

    searchPanel.add(
            searchLabel,
            java.awt.BorderLayout.WEST
    );

    searchPanel.add(
            searchField,
            java.awt.BorderLayout.CENTER
    );

    searchPanel.add(
            searchButton,
            java.awt.BorderLayout.EAST
    );


    // =========================================================
    // TABLE PANEL
    // =========================================================

    javax.swing.JPanel tablePanel =
            new javax.swing.JPanel(
                    new java.awt.BorderLayout()
            );

    tablePanel.setBorder(
            javax.swing.BorderFactory.createTitledBorder(
                    "Student List"
            )
    );

    tablePanel.add(
            scrollPane,
            java.awt.BorderLayout.CENTER
    );


    // =========================================================
    // MAIN PANEL
    // =========================================================

    javax.swing.JPanel mainPanel =
            new javax.swing.JPanel();

    mainPanel.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(
                    10,
                    15,
                    15,
                    15
            )
    );

    mainPanel.setLayout(
            new javax.swing.BoxLayout(
                    mainPanel,
                    javax.swing.BoxLayout.Y_AXIS
            )
    );


    // Header
    mainPanel.add(titleLabel);

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(15)
    );


    // Student + QR
    mainPanel.add(topPanel);

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(10)
    );


    // Buttons
    mainPanel.add(actionPanel);

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(8)
    );


    // Search
    mainPanel.add(searchPanel);

    mainPanel.add(
            javax.swing.Box.createVerticalStrut(8)
    );


    // Table
    mainPanel.add(
            tablePanel
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
    private void generateStudentQR() {

    if (selectedStudentId == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Please select a student from the table first."
        );

        return;
    }

    String qrValue = generateQRValue();

    if (qrValue.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Registration number is missing."
        );

        return;
    }

    try {

        BufferedImage qrImage =
                QRGenerator.generateQRCode(
                        qrValue,
                        200,
                        200
                );

        qrPreviewLabel.setIcon(
                new ImageIcon(qrImage)
        );

        qrPreviewLabel.setText("");

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Unable to generate QR code.",
                "QR Error",
                JOptionPane.ERROR_MESSAGE
        );

        e.printStackTrace();
    }
}
    // =========================================================
    // VARIABLES
    // =========================================================

    private javax.swing.JButton addButton;
    private javax.swing.JButton updateButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton generateQRButton;
    private javax.swing.JLabel qrPreviewLabel;

    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel registrationLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JLabel departmentLabel;
    private javax.swing.JLabel yearLabel;
    private javax.swing.JLabel sectionLabel;
    private javax.swing.JLabel qrCodeValueLabel;
    private javax.swing.JLabel searchLabel;

    private javax.swing.JTextField registrationField;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField phoneField;
    private javax.swing.JTextField departmentField;
    private javax.swing.JTextField sectionField;
    private javax.swing.JTextField searchField;

    private javax.swing.JComboBox<String> genderComboBox;
    private javax.swing.JComboBox<String> yearComboBox;

    private javax.swing.JTable studentTable;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JButton searchButton;

}