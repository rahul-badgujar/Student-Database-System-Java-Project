package db_helpers.tables;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import data_models.StudentModel;
import db_helpers.Connector;

public class StudentTable {
    private static StudentTable instance = null;

    private StudentTable() {

    }

    public static StudentTable getInstance() {
        if (instance == null)
            instance = new StudentTable();
        return instance;
    }

    public Statement createTable() throws ClassNotFoundException, SQLException {
        Connector connector = Connector.getInstance();
        String sql = "CREATE TABLE IF NOT EXISTS Students (\n" + "roll_no INTEGER PRIMARY KEY, \n"
                + "name TEXT NOT NULL, \n" + "marks REAL \n" + ");";
        Statement statement = connector.getConnection().createStatement();
        statement.execute(sql);
        return statement;
    }

    public PreparedStatement insertStudentData(StudentModel student) throws ClassNotFoundException, SQLException {
        Connector connector = Connector.getInstance();
        String sql = "INSERT INTO Students(roll_no, name, marks) VALUES(?,?,?);";
        PreparedStatement statement = connector.getConnection().prepareStatement(sql);

        statement.executeUpdate();
        return statement;
    }
}
