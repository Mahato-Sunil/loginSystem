// importing the necessary class

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PasswordPage implements ActionListener {
    JFrame frame;
    JLabel label, message;
    JLabel password, confirmPassword;
    JButton register;
    JPasswordField passwordField, confirmPwdField;
    // declaring the password field
    String password1, confirmPassword1;

    // user credentials
    String  email;

    PasswordPage(String email) {
        // initialization of user's credentials
        this.email = email;

        createFrame();
        setComponents();
        addComp();
    }

    // frame ui
    private void createFrame() {
        frame = new JFrame("Create Password... ");
        frame.setVisible(true);
        frame.setSize(420, 420);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // setting the dimessions of components
    private void setComponents() {
        label = new JLabel("Enter your Credentials...");
        label.setBounds(120, 30, 250, 40);
        label.setFont(new Font(null, Font.ITALIC, 16));
        label.setForeground(Color.BLUE);

        password = new JLabel("New Password :-");
        password.setBounds(80, 80, 150, 40);

        passwordField = new JPasswordField();
        passwordField.setBounds(80, 120, 230, 40);

        confirmPassword = new JLabel("Confirm Password :-");
        confirmPassword.setBounds(80, 150, 150, 40);

        confirmPwdField = new JPasswordField();
        confirmPwdField.setBounds(80, 190, 230, 40);

        register = new JButton("REGISTER");
        register.setBounds(150, 250, 100, 40);
        register.addActionListener(this);

        message = new JLabel();
        message.setBounds(100, 300, 280, 40);
        message.setForeground(Color.RED);
    }

    private void addComp() {
        frame.add(label);
        frame.add(password);
        frame.add(confirmPassword);
        frame.add(passwordField);
        frame.add(confirmPwdField);
        frame.add(register);
        frame.add(message);
    }

    // adding action listener
    @Override
    public void actionPerformed(ActionEvent e) {
        // getting the input and passing the values to global variables
        String pwd = String.valueOf(passwordField.getPassword());
        String confirmPwd = String.valueOf(confirmPwdField.getPassword());
        password1 = pwd;
        confirmPassword1 = confirmPwd;

        // register button
        if (e.getSource() == register) {
            if (pwd.equals("") || confirmPwd.equals("")) {
                message.setForeground(Color.red);
                message.setText("Please Enter All Credentials !");
            } else if (pwd.equals(confirmPwd)) {
                storePassword(email, confirmPwd);
            } else {
                message.setForeground(Color.red);
                message.setText("Password Doesn't Match");
            }
        }
    }

    // storing the passwords in the database
    private void storePassword(String email1, String cPassword) {
        // database credentials
        String url = "jdbc:mysql://localhost:3306/appointment-scheduler";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO login (userEmail, password) VALUES(?,?)";
            PreparedStatement statement = connect.prepareStatement(query);

            statement.setString(1, email1);
            statement.setString(2, cPassword);

            // checking if data is inserted in the database
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                frame.setVisible(false);
                new LoginPage();
            }
            connect.close();
        } catch (Exception err) {
            message.setText("Error: Database Offline ");
        }
    }
}