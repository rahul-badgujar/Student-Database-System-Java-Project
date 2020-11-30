package db_helpers.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import data_models.Model;
import data_models.StudentModel;
import db_helpers.Connector;
import utils.Utils;

public class StudentTable implements Table {
    static final String tableName = "Students";

    public enum StudentTableColumn {
        ROLL_NO, FULLNAME, DOB, AGE, GENDER, BRANCH, YEAR_OF_STUDY, DIVISION, CGPA, PHONE, EMAIL
    }

    private static StudentTable instance = null;

    private StudentTable() {
        super();
        try {
            createTable();
        } catch (ClassNotFoundException | SQLException e) {
            Utils.printError(tableName + " could not be created");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static StudentTable getInstance() {
        if (instance == null)
            instance = new StudentTable();
        return instance;
    }

    @Override
    public Statement createTable() throws ClassNotFoundException, SQLException {
        Connector connector = Connector.getInstance();
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n" + StudentTableColumn.ROLL_NO
                + " TEXT PRIMARY KEY, \n" + StudentTableColumn.FULLNAME + " TEXT NOT NULL, \n" + StudentTableColumn.DOB
                + " TEXT, \n" + StudentTableColumn.AGE + " INTEGER, \n" + StudentTableColumn.GENDER.toString()
                + " TEXT, \n" + StudentTableColumn.BRANCH.toString() + " TEXT NOT NULL, \n"
                + StudentTableColumn.YEAR_OF_STUDY.toString() + " TEXT NOT NULL, \n"
                + StudentTableColumn.DIVISION.toString() + " TEXT NOT NULL, \n" + StudentTableColumn.CGPA.toString()
                + " REAL, \n" + StudentTableColumn.PHONE.toString() + " TEXT, \n" + StudentTableColumn.EMAIL.toString()
                + " TEXT" + ");";

        Statement statement = connector.getConnection().createStatement();
        statement.execute(sql);
        return statement;
    }

    @Override
    public PreparedStatement insertRecord(Model model) throws ClassNotFoundException, SQLException {
        if (model instanceof StudentModel) {
            StudentModel student = (StudentModel) model;
            Connector connector = Connector.getInstance();
            PreparedStatement statement = student.toSqlInsertStatement(connector.getConnection());
            statement.executeUpdate();
            return statement;
        }
        return null;
    }

    @Override
    public List<Model> queryAllRecords() throws ClassNotFoundException, SQLException {

        Connector connector = Connector.getInstance();
        String sql = "SELECT * FROM " + tableName + ";";
        Statement statement = connector.getConnection().createStatement();
        ResultSet result = statement.executeQuery(sql);
        return StudentModel.fromSqlResult(result);

    }

    @Override
    public boolean checkRecordExistance(Model model) throws SQLException, ClassNotFoundException {
        if (model instanceof StudentModel) {
            StudentModel student = (StudentModel) model;
            Connector connector = Connector.getInstance();
            String sql = "SELECT * FROM " + tableName + " WHERE " + StudentTableColumn.ROLL_NO + " = ? ;";
            PreparedStatement statement = connector.getConnection().prepareStatement(sql);
            statement.setString(1, student.getRollNo());
            ResultSet result = statement.executeQuery();
            int count = 0;
            while (result.next())
                count++;
            return count > 0;
        }
        return false;
    }

    public List<Model> retrieveStudentDataUsingRollNo(String rollno) throws SQLException, ClassNotFoundException {
        Connector connector = Connector.getInstance();
        String sql = "SELECT * FROM " + tableName + " WHERE " + StudentTableColumn.ROLL_NO + " = ? ;";
        PreparedStatement statement = connector.getConnection().prepareStatement(sql);
        statement.setString(1, rollno);
        ResultSet result = statement.executeQuery();
        return StudentModel.fromSqlResult(result);
    }

    public boolean deleteStudentDataUsingRollNo(String rollno) throws ClassNotFoundException, SQLException {
        Connector connector = Connector.getInstance();
        String sql = "DELETE FROM " + tableName + " WHERE " + StudentTableColumn.ROLL_NO + " = ? ;";
        PreparedStatement statement = connector.getConnection().prepareStatement(sql);
        statement.setString(1, rollno);
        return statement.executeUpdate() != 0;
    }

    public boolean updateStudentData(Model model) throws ClassNotFoundException, SQLException {
        if (model instanceof StudentModel) {
            Connector connector = Connector.getInstance();
            StudentModel student = (StudentModel) model;
            String sql = "UPDATE " + StudentTable.tableName + " SET " + StudentTableColumn.FULLNAME.toString()
                    + " = ? ," + StudentTableColumn.DOB.toString() + " = ? ," + StudentTableColumn.AGE.toString()
                    + " = ? ," + StudentTableColumn.GENDER.toString() + " = ? ," + StudentTableColumn.BRANCH.toString()
                    + " = ? ," + StudentTableColumn.YEAR_OF_STUDY.toString() + " = ? ,"
                    + StudentTableColumn.DIVISION.toString() + " = ? ," + StudentTableColumn.PHONE.toString() + " = ? ,"
                    + StudentTableColumn.EMAIL.toString() + " = ? ," + StudentTableColumn.CGPA.toString() + " = ? "
                    + " WHERE " + StudentTableColumn.ROLL_NO + " = ? ;";
            PreparedStatement statement = connector.getConnection().prepareStatement(sql);
            statement.setString(1, student.getFullName());
            statement.setString(2, student.getDob());
            statement.setInt(3, student.getAge());
            statement.setString(4, student.getGender().toString());
            statement.setString(5, student.getBranch().toString());
            statement.setString(6, student.getYearOfStudy().toString());
            statement.setString(7, student.getDivision().toString());
            statement.setString(8, student.getPhone());
            statement.setString(9, student.getEmail());
            statement.setDouble(10, student.getCGPA());
            statement.setString(11, student.getRollNo());
            return statement.executeUpdate() != 0;
        }
        return false;
    }
}
