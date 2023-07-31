import javax.swing.*;
import java.awt.*;

public class WelcomePage {
    public void NextWelcomePage() {
        JFrame frame = new JFrame();
        JLabel label1 = new JLabel("Welcome To the Page ");
        label1.setBounds(80, 150, 400, 40);

        JLabel label2 = new JLabel("😄  😄  😄  😄");
        label2.setBounds(200, 200, 400, 40);

        label1.setFont(new Font(null, Font.ITALIC, 30));
        label1.setForeground(Color.green);


        // setting the frame width and height and making it visible
        frame.setVisible(true);
        frame.setSize(480, 480);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // adding the components
        frame.add(label1);
        frame.add(label2);
        frame.setTitle("Welcome Page");
    }
}
