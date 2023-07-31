// importing the necessary class

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SignUpPage implements ActionListener {
    // instantiatiating necessary classes
    JFrame frame;
    JLabel label, message;
    JLabel uid, fname, lname, contact, email;
    JButton reset, submit;
    JTextField uidInp, fnameInp, lnameInp, contactInp, emailInp;
// class constructor

    SignUpPage() {
        createFrame();  //create frame
        setComponents();    // setting dimensions of components
        addComp();      // adding components

    }

    // setting the dimessions of components
    private void setComponents() {
        label = new JLabel("Register Yourself..");
        label.setBounds(120, 5, 250, 40);
        label.setFont(new Font(null, Font.ITALIC, 16));
        label.setForeground(Color.BLUE);

        uid = new JLabel("User ID :- ");
        uid.setBounds(30, 40, 100, 40);

        fname = new JLabel("First Name :-");
        fname.setBounds(30, 80, 100, 40);

        lname = new JLabel("Last Name :-");
        lname.setBounds(30, 130, 100, 40);

        contact = new JLabel("Contact No. :-");
        contact.setBounds(30, 180, 100, 40);

        email = new JLabel("Email ID :-");
        email.setBounds(30, 230, 100, 40);

        uidInp = new JTextField();
        uidInp.setBounds(130, 40, 220, 40);

        fnameInp = new JTextField();
        fnameInp.setBounds(130, 80, 220, 40);

        lnameInp = new JTextField();
        lnameInp.setBounds(130, 130, 220, 40);

        contactInp = new JTextField();
        contactInp.setBounds(130, 180, 220, 40);

        emailInp = new JTextField();
        emailInp.setBounds(130, 230, 220, 40);

        reset = new JButton("RESET");
        reset.setBounds(100, 300, 100, 40);
        reset.addActionListener(this);

        submit = new JButton("SUBMIT");
        submit.setBounds(220, 300, 100, 40);
        submit.addActionListener(this);

        message = new JLabel();
        message.setBounds(100, 330, 280, 40);
        message.setForeground(Color.RED);
    }

    // frame ui
    private void createFrame() {
        frame = new JFrame("Register Yourself ");
        frame.setVisible(true);
        frame.setSize(420, 420);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // add components
    private void addComp() {
        frame.add(label);   // label
        frame.add(uid);     // uid label
        frame.add(fname);   // first name
        frame.add(lname);   // last name
        frame.add(contact);   // contact number
        frame.add(email);   // email id
        frame.add(uidInp);  // user id input field
        frame.add(fnameInp);   // input firtst name
        frame.add(lnameInp);   // input last name
        frame.add(contactInp);   // input contact number
        frame.add(emailInp);   // input email id
        frame.add(reset);   // reset button
        frame.add(submit);   // submit button
        frame.add(message);     // message label
    }

    // adding action listener
    @Override
    public void actionPerformed(ActionEvent e) {
        // getting details
        String userId = uidInp.getText();
        String firstName = fnameInp.getText();
        String lastName = lnameInp.getText();
        String number = contactInp.getText();
        String emailId = emailInp.getText();

        // reset button
        if (e.getSource() == reset) {
            uidInp.setText("");
            fnameInp.setText("");
            lnameInp.setText("");
            contactInp.setText("");
            emailInp.setText("");
            message.setText("");
        }

        if (e.getSource() == submit) {
            if (firstName.equals("") || lastName.equals("") || number.equals("") || emailId.equals("")) {
                message.setForeground(Color.red);
                message.setText("Please Enter All Credentials !");
            } else {
                registerDetails(userId, firstName, lastName, number, emailId);
                frame.setVisible(false);
            }
        }
    }

    // storing the registratioin details in the data base
    private void registerDetails(String uid, String fname, String lname, String number1, String email1) {
        // database credentials
        String url = "jdbc:mysql://localhost:3306/appointment-scheduler";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO registration (id, FirstName, LastName, number, email) VALUES(?,?,?,?,?)";
            PreparedStatement statement = connect.prepareStatement(query);

            statement.setString(1, uid);
            statement.setString(2, fname);
            statement.setString(3, lname);
            statement.setString(4, number1);
            statement.setString(5, email1);

            // checking if data is inserted in the database
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                frame.setVisible(false);
                new PasswordPage(email1);
            }
            connect.close();
        } catch (Exception err) {
            message.setText("Could Not Communicate with database");
        }
    }
}
