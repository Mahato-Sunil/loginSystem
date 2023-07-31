import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage implements ActionListener {
    JFrame frame = new JFrame("Login Page");   // creating of the frame
    JLabel heading = new JLabel("Welcome to Appointment Scheduler");    // for heading
    JLabel userIdLabel = new JLabel("Email Id :-");    // label for user name
    JLabel userPwdLabel = new JLabel("Password :-");    // label for password
    JLabel message = new JLabel();      // label for message
    JTextField userIdInp = new JTextField();     // input area for user name
    JPasswordField userPwdInp = new JPasswordField();   // input field for password
    JButton reset = new JButton("Reset");   // reseet button
    JButton submit = new JButton("Login");  // login button
    JButton signup = new JButton("Register");   // signup button

    // login page constructor
    LoginPage() {
        // designing the gui
        heading.setBounds(60, 10, 300, 40);
        heading.setFont(new Font(null, Font.ITALIC, 16));
        heading.setForeground(Color.blue);

        userIdLabel.setBounds(50, 100, 75, 40); // username
        userPwdLabel.setBounds(50, 150, 75, 40);    // password
        message.setBounds(125, 280, 250, 40);   // message

        userIdInp.setBounds(125, 100, 200, 40);
        userPwdInp.setBounds(125, 150, 200, 40);

        reset.setBounds(220, 230, 100, 40);
        reset.setFocusable(false);      // remove the focus / box from text
        reset.addActionListener(this);  // adding action listener

        submit.setBounds(110, 230, 100, 40);
        submit.setFocusable(false);      // remove the focus / box from text
        submit.addActionListener(this);  // adding action listener

        signup.setBounds(150, 320, 100, 40);
        signup.setFocusable(false);
        signup.addActionListener(this);

        // setting the frame width and height and making it visible
        frame.setVisible(true);
        frame.setSize(420, 420);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // adding the componets to the frame
        frame.add(heading);         // heading
        frame.add(userIdLabel);     // username label
        frame.add(userPwdLabel);    // password label
        frame.add(message);         // message label
        frame.add(userIdInp);       // username input area
        frame.add(userPwdInp);      // password input area
        frame.add(reset);           // reset button
        frame.add(submit);          // submit button
        frame.add(signup);          // sign up button
    }

    // event handler for login button
    @Override
    public void actionPerformed(ActionEvent e) {
        // reset  button
        if (e.getSource() == reset) {
            userIdInp.setText("");
            userPwdInp.setText("");
            message.setText("");
        }

        // login button
        if (e.getSource() == submit) {
            String id = userIdInp.getText();
            String password = String.valueOf(userPwdInp.getPassword());
            // creationg of validate class
            Validate valid = new Validate(id, password);

            if (id.equals("") || password.equals("")) {
                message.setForeground(Color.red);
                message.setText("Please Enter All Credentials !");
            } else {
                if (valid.checkValidity()) {
                    message.setForeground(Color.green);
                    frame.setVisible(false);
                    WelcomePage obj = new WelcomePage();
                    obj.NextWelcomePage();
                } else {
                    message.setForeground(Color.red);
                    message.setText("Username or Password Incorrect !");
                }
            }
        }

        // signup page
        if (e.getSource() == signup) {
            frame.setVisible(false);
            new SignUpPage();
        }
    }
}