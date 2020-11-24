import java.sql.SQLException;

import data_models.StudentModel;
import db_helpers.tables.StudentTable;

public class Main {

    public static void main(String[] args) {
        StudentTable studentTable = StudentTable.getInstance();
        try {
            studentTable.createTable();
            StudentModel stud = new StudentModel();
            studentTable.insertStudentData(stud);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
