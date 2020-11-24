package db_helpers.tables;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import data_models.StudentModel;
import db_helpers.Connector;

public class StudentTable {

    public enum StudentTableColumn {
        ROLL_NO, FULLNAME, DOB, AGE, GENDER, BRANCH, YEAR_OF_STUDY, DIVISION, CGPA, PHONE, EMAIL
    }

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
        String sql = "CREATE TABLE IF NOT EXISTS Students (\n" + StudentTableColumn.ROLL_NO + " TEXT PRIMARY KEY, \n"
                + StudentTableColumn.FULLNAME + " TEXT NOT NULL, \n" + StudentTableColumn.DOB + " TEXT, \n"
                + StudentTableColumn.AGE + " INTEGER, \n" + StudentTableColumn.GENDER.toString() + " TEXT, \n"
                + StudentTableColumn.BRANCH.toString() + " TEXT NOT NULL, \n"
                + StudentTableColumn.YEAR_OF_STUDY.toString() + " TEXT NOT NULL, \n"
                + StudentTableColumn.DIVISION.toString() + " TEXT NOT NULL, \n" + StudentTableColumn.CGPA.toString()
                + " REAL, \n" + StudentTableColumn.PHONE.toString() + " TEXT, \n" + StudentTableColumn.EMAIL.toString()
                + " TEXT" + ");";

        Statement statement = connector.getConnection().createStatement();
        statement.execute(sql);
        return statement;
    }

    public PreparedStatement insertStudentData(StudentModel student) throws ClassNotFoundException, SQLException {
        Connector connector = Connector.getInstance();
        String sql = "INSERT INTO Students(" + StudentTableColumn.ROLL_NO.toString() + ","
                + StudentTableColumn.FULLNAME.toString() + "," + StudentTableColumn.DOB.toString() + ","
                + StudentTableColumn.AGE.toString() + "," + StudentTableColumn.GENDER.toString() + ","
                + StudentTableColumn.BRANCH.toString() + "," + StudentTableColumn.YEAR_OF_STUDY.toString() + ","
                + StudentTableColumn.DIVISION.toString() + "," + StudentTableColumn.CGPA.toString() + ","
                + StudentTableColumn.PHONE.toString() + "," + StudentTableColumn.EMAIL.toString()
                + ") VALUES(?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement statement = connector.getConnection().prepareStatement(sql);
        statement.setString(1, student.getRollNo());
        statement.setString(2, student.getFullName());
        statement.setString(3, student.getDob());
        statement.setInt(4, student.getAge());
        statement.setString(5, student.getGender().toString());
        statement.setString(6, student.getBranch().toString());
        statement.setString(7, student.getYearOfStudy().toString());
        statement.setString(8, student.getDivision().toString());
        statement.setDouble(9, student.getCGPA());
        statement.setString(10, student.getPhone());
        statement.setString(11, student.getEmail());
        statement.executeUpdate();
        return statement;
    }
}
