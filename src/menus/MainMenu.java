package menus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

import data_models.Model;
import data_models.StudentModel;
import db_helpers.tables.StudentTable;

public class MainMenu extends Menu {
    public static final String MENU_TITLE = "Main Menu";
    private static final int CHOICE_ADD_STUDENT = 1;
    private static final int CHOICE_SEARCH_STUDENT = 2;
    private static final int CHOICE_DELETE_STUDENT = 3;
    private static final int CHOICE_UPDATE_STUDENT = 4;
    private static final int CHOICE_EXIT = 5;

    public MainMenu() {
        super(MENU_TITLE);

        this.menuItems = formItemsList();
        this.optionsCount = this.menuItems.size();

    }

    @Override
    public List<String> formItemsList() {
        List<String> items = new ArrayList<>();
        items.add("Add Student Record");
        items.add("Search Student Record");
        items.add("Delete Student Record");
        items.add("Update Student Record");
        items.add("Exit");
        return items;
    }

    @Override
    public void actOnChoice(int choice) {
        if (choice == CHOICE_ADD_STUDENT) {
            System.out.println("FILL STUDENT DETAILS");
            Model student = StudentModel.fromUserInput();
            StudentTable studentTable = StudentTable.getInstance();
            try {
                if (studentTable.checkRecordExistance(student) == false) {
                    studentTable.insertRecord(student);
                    System.out.println("Student Record Inserted Successfully...");
                } else {
                    System.out.println(
                            "Cannot Insert Student Record: Student Record already present in Database. Try Updating Data instead.");
                }
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Choice made: " + choice);
        }
    }

}
