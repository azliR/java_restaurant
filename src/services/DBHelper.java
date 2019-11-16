package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a_lpha
 */
public class DBHelper {

    private static Connection connection;

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/receptionist";
            String user = "root";
            String password = "";

            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            connection = null;
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
