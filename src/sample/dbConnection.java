package sample;

import java.sql.*;

public class dbConnection {
    Connection connection = null;
    public static Connection connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/mania/IdeaProjects/CSIA/src/sample\\identifier.sqlite");
                //System.out.println("Connce");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
