package menus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.*;

import data_models.Model;
import data_models.StudentModel;
import db_helpers.tables.StudentTable;
import utils.Utils;

public class MainMenu extends Menu {
    public static final String MENU_TITLE = "Main Menu";
    private static final int CHOICE_ADD_STUDENT = 1;
    private static final int CHOICE_SEARCH_STUDENT = 2;
    private static final int CHOICE_DELETE_STUDENT = 3;
    private static final int CHOICE_UPDATE_STUDENT = 4;
    private static final int CHOICE_LIST_ALL = 5;
    private static final int CHOICE_EXIT = 6;

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
        items.add("List All Students");
        items.add("Exit");
        return items;
    }

    @Override
    public void actOnChoice(int choice) {

        if (choice == CHOICE_ADD_STUDENT) {
            choiceAddStudentCallback();
        } else if (choice == CHOICE_SEARCH_STUDENT) {
            choiceSearchStudentCallback();
        } else if (choice == CHOICE_LIST_ALL) {
            choiceListAllStudentsCallback();
        } else {
            System.out.println("Choice made: " + choice);
        }

    }

    private void choiceAddStudentCallback() {
        StudentTable studentTable = StudentTable.getInstance();
        System.out.println("FILL STUDENT DETAILS");
        Model student = StudentModel.fromUserInput();
        try {
            if (studentTable.checkRecordExistance(student) == false) {
                studentTable.insertRecord(student);
                System.out.println("Student Record Inserted Successfully...");
            } else {
                System.out.println(
                        "Cannot Insert Student Record: Student Record already present in Database. Try Updating Data instead.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Exception caught in MainMenu.choiceAddStudentCallback()");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void choiceSearchStudentCallback() {
        StudentTable studentTable = StudentTable.getInstance();
        System.out.print("Enter Roll No to Search: ");
        Scanner scanner = Utils.getInstance().scanner;
        String rollToSearch = scanner.next();
        try {
            List<Model> studentsFound = studentTable.retrieveStudentDataUsingRollNo(rollToSearch);
            if (studentsFound.isEmpty()) {
                System.out.println("No Student Record FOUND with this Roll No...");
            } else {
                System.out.println("RECORDS FOUND...");
                for (Model student : studentsFound) {
                    System.out.println(student);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Exception caught in MainMenu.choiceSearchStudentCallback()");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void choiceListAllStudentsCallback() {
        StudentTable studentTable = StudentTable.getInstance();
        System.out.println("ALL STUDENTS RECORD");
        try {
            List<Model> allStudents = studentTable.queryAllRecords();
            if (allStudents.isEmpty()) {
                System.out.print("Nothing to show, consider adding some Records");
            } else {
                for (Model student : allStudents) {
                    System.out.println(student);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Exception caught in MainMenu.choiceListAllStudentsCallback()");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

}
