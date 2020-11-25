import java.sql.SQLException;
import java.util.List;

import data_models.Model;
import db_helpers.tables.StudentTable;

public class Main {

    public static void main(String[] args) {
        var studentTable = StudentTable.getInstance();
        try {
            studentTable.createTable();
            List<Model> students = studentTable.queryAllRecords();
            for (Model student : students) {
                System.out.println(student);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
