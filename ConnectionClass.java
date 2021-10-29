package garage;

import java.sql.*;

public class ConnectionClass {

    Connection connector;

    public Connection connect() {

        try {
            connector = DriverManager.getConnection("jdbc:mysql://localhost:3306/garage_db", "root", "1234");
        } catch (SQLException connectionException) {
            System.out.println("Couldn't connect to database");
        }

        return connector;
    }
}
