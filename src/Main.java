import java.sql.SQLException;
import java.util.List;

import data_models.StudentModel;
import db_helpers.tables.StudentTable;

public class Main {

    public static void main(String[] args) {
        StudentTable studentTable = StudentTable.getInstance();
        try {
            studentTable.createTable();
            List<StudentModel> students = studentTable.queryAllStudents();
            for (StudentModel student : students) {
                System.out.println(student);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
