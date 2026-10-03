package ui;

import dao.UserDAO;
import model.User;
import util.PasswordUtil;

import javax.swing.JOptionPane;

public class LoginFrame extends javax.swing.JFrame {

    /**
     * Creates new form LoginFrame
     */
    public LoginFrame() {

    initComponents();

    setTitle(
            "Smart Student Attendance - Login"
    );

    setSize(520, 500);

    setLocationRelativeTo(null);

    usernameField.requestFocus();
}

    /**
     * This method is called from within the constructor
     * to initialize the form.
     */

    @SuppressWarnings("unchecked")
private void initComponents() {

    titleLabel = new javax.swing.JLabel();

    usernameLabel = new javax.swing.JLabel();
    usernameField = new javax.swing.JTextField();

    passwordLabel = new javax.swing.JLabel();
    passwordField = new javax.swing.JPasswordField();

    loginButton = new javax.swing.JButton();

    messageLabel = new javax.swing.JLabel();


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
                    24
            )
    );

    titleLabel.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    titleLabel.setText(
            "SMART STUDENT ATTENDANCE"
    );


    // =========================================================
    // USERNAME
    // =========================================================

    usernameLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    usernameLabel.setText(
            "USERNAME"
    );

    usernameField.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    14
            )
    );

    usernameField.setPreferredSize(
            new java.awt.Dimension(
                    320,
                    40
            )
    );


    // =========================================================
    // PASSWORD
    // =========================================================

    passwordLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    passwordLabel.setText(
            "PASSWORD"
    );

    passwordField.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    14
            )
    );

    passwordField.setPreferredSize(
            new java.awt.Dimension(
                    320,
                    40
            )
    );

    passwordField.addActionListener(
            evt -> loginButtonActionPerformed(evt)
    );


    // =========================================================
    // LOGIN BUTTON
    // =========================================================

    loginButton.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.BOLD,
                    13
            )
    );

    loginButton.setText(
            "LOGIN"
    );

    loginButton.setPreferredSize(
            new java.awt.Dimension(
                    150,
                    45
            )
    );

    loginButton.addActionListener(
            evt -> loginButtonActionPerformed(evt)
    );


    // =========================================================
    // MESSAGE
    // =========================================================

    messageLabel.setFont(
            new java.awt.Font(
                    "Segoe UI",
                    java.awt.Font.PLAIN,
                    12
            )
    );

    messageLabel.setHorizontalAlignment(
            javax.swing.SwingConstants.CENTER
    );

    messageLabel.setText("");


    // =========================================================
    // LOGIN CARD
    // =========================================================

    javax.swing.JPanel loginPanel =
            new javax.swing.JPanel(
                    new java.awt.GridBagLayout()
            );

    loginPanel.setBorder(
            javax.swing.BorderFactory.createCompoundBorder(
                    javax.swing.BorderFactory.createLineBorder(
                            java.awt.Color.LIGHT_GRAY
                    ),
                    javax.swing.BorderFactory.createEmptyBorder(
                            25,
                            35,
                            25,
                            35
                    )
            )
    );

    java.awt.GridBagConstraints gbc =
            new java.awt.GridBagConstraints();

    gbc.fill =
            java.awt.GridBagConstraints.HORIZONTAL;

    gbc.insets =
            new java.awt.Insets(
                    6,
                    5,
                    6,
                    5
            );


    // Username label
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1.0;

    loginPanel.add(
            usernameLabel,
            gbc
    );


    // Username field
    gbc.gridy = 1;

    loginPanel.add(
            usernameField,
            gbc
    );


    // Password label
    gbc.gridy = 2;

    loginPanel.add(
            passwordLabel,
            gbc
    );


    // Password field
    gbc.gridy = 3;

    loginPanel.add(
            passwordField,
            gbc
    );


    // Login button
    gbc.gridy = 4;
    gbc.insets =
            new java.awt.Insets(
                    20,
                    5,
                    10,
                    5
            );

    gbc.anchor =
            java.awt.GridBagConstraints.CENTER;

    loginPanel.add(
            loginButton,
            gbc
    );


    // Message
    gbc.gridy = 5;

    gbc.insets =
            new java.awt.Insets(
                    5,
                    5,
                    5,
                    5
            );

    loginPanel.add(
            messageLabel,
            gbc
    );


    // =========================================================
    // SUBTITLE
    // =========================================================

    javax.swing.JLabel subtitleLabel =
            new javax.swing.JLabel(
                    "Login to access the attendance management system"
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
    // MAIN PANEL
    // =========================================================

    javax.swing.JPanel mainPanel =
            new javax.swing.JPanel();

    mainPanel.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(
                    30,
                    45,
                    30,
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

    loginPanel.setAlignmentX(
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
            loginPanel
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



    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {

        String username = usernameField.getText().trim();

        String password =
                new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {

            messageLabel.setText("Please enter username and password.");
            return;
        }

        try {

            String passwordHash =
                    PasswordUtil.hashPassword(password);

            UserDAO userDAO = new UserDAO();

            User user =
                    userDAO.login(username, passwordHash);

if (user != null) {

    JOptionPane.showMessageDialog(
            this,
            "Welcome, " + user.getUsername() + "!"
    );

    DashboardFrame dashboard =
            new DashboardFrame();

    dashboard.setVisible(true);

    dispose();

} else {

                messageLabel.setText(
                        "Invalid username or password."
                );

            }

        } catch (Exception e) {

            messageLabel.setText(
                    "Unable to connect to database."
            );

            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {

            for (javax.swing.UIManager.LookAndFeelInfo info
                    : javax.swing.UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {

                    javax.swing.UIManager.setLookAndFeel(
                            info.getClassName()
                    );

                    break;
                }
            }

        } catch (Exception ex) {

            java.util.logging.Logger.getLogger(
                    LoginFrame.class.getName()
            ).log(
                    java.util.logging.Level.SEVERE,
                    null,
                    ex
            );
        }

        java.awt.EventQueue.invokeLater(
                new Runnable() {

                    public void run() {

                        new LoginFrame().setVisible(true);
                    }
                }
        );
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel messageLabel;
    // End of variables declaration
}