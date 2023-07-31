// importing the necessary files

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Validate {
    String url = "jdbc:mysql://localhost:3306/appointment-scheduler";
    String username = "root";
    String password = "";
    String uid, upassword;
    String orgUserName, orgUserPassword;

    Validate(String uid, String upassword) {
        this.uid = uid;
        this.upassword = upassword;
    }

    public Boolean checkValidity() {
        boolean isValid = false;
        // connecting the database
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url, username, password);
            Statement statement = connect.createStatement();
            String query = "SELECT userEmail, password FROM login";
            ResultSet result = statement.executeQuery(query);
            int i = 0;
            while (result.next()) {
                orgUserName = result.getString("userEmail");
                orgUserPassword = result.getString("password");
                isValid = (uid.equals(orgUserName) && upassword.equals(orgUserPassword));
                if(isValid)
                    break;
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error ");
        }
        return isValid;
    }
}