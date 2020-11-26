package db_helpers;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public static final String url = "jdbc:sqlite:database/students_data.db";
    private static Connector instance = null;
    private Connection connection = null;
    private DatabaseMetaData databaseMetaData = null;

    private Connector() {

    }

    public void releaseResources() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Exception caught in Connector.releaseResources()");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static Connector getInstance() {
        if (instance == null)
            instance = new Connector();
        return instance;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database/students_data.db");

        }
        return connection;
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = null;
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        if (databaseMetaData == null) {
            databaseMetaData = connection.getMetaData();
        }
        return databaseMetaData;
    }
}
