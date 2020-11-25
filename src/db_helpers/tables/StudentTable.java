package db_helpers.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import data_models.Model;
import data_models.StudentModel;
import db_helpers.Connector;

public class StudentTable implements Table {
    static final String tableName = "Students";

    public enum StudentTableColumn {
        ROLL_NO, FULLNAME, DOB, AGE, GENDER, BRANCH, YEAR_OF_STUDY, DIVISION, CGPA, PHONE, EMAIL
    }

    private static StudentTable instance = null;

    private StudentTable() {
        super();
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
}
