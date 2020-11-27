package menus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import data_models.Model;
import data_models.StudentModel;
import db_helpers.tables.StudentTable;
import utils.Utils;

public class MainMenu extends Menu {
    public static final String MENU_TITLE = "Main Menu";
    private static final int CHOICE_ADD_STUDENT = 1;
    private static final int CHOICE_SEARCH_STUDENT = 2;
    private static final int CHOICE_DELETE_STUDENT = 4;
    private static final int CHOICE_UPDATE_STUDENT = 3;
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
        items.add("Update Student Record");
        items.add("Delete Student Record");
        items.add("List All Students");
        items.add("Exit");
        return items;
    }

    @Override
    public void actOnChoice(Integer choice) {
        Utils.clearScreen();
        if (choice == CHOICE_ADD_STUDENT) {
            choiceAddStudentCallback();
        } else if (choice == CHOICE_SEARCH_STUDENT) {
            choiceSearchStudentCallback();
        } else if (choice == CHOICE_LIST_ALL) {
            choiceListAllStudentsCallback();
        } else if (choice == CHOICE_DELETE_STUDENT) {
            choiceDeleteStudentCallback();
        } else if (choice == CHOICE_UPDATE_STUDENT) {
            choiceUpdateStudentCallback();
        } else if (choice == CHOICE_EXIT) {
            closeMenu();
        } else {
            System.out.println("Choice made: " + choice);
        }

    }

    private void choiceAddStudentCallback() {
        StudentTable studentTable = StudentTable.getInstance();
        System.out.println("\tFILL STUDENT DETAILS \n");
        Model student = StudentModel.fromUserInput();
        try {
            if (!studentTable.checkRecordExistance(student)) {
                studentTable.insertRecord(student);
                Utils.printlnSuccess("Student Record Inserted Successfully...");
            } else {
                Utils.printlnError(
                        "Cannot Insert Student Record: Student Record already present in Database. Try Updating Record instead.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            Utils.printlnException("Exception caught in MainMenu.choiceAddStudentCallback()");
            Utils.printlnException(e.getMessage());
            e.printStackTrace();
        }
    }

    private void choiceSearchStudentCallback() {
        StudentTable studentTable = StudentTable.getInstance();
        System.out.print("Enter Roll No to Search: ");
        Scanner scanner = Utils.getInstance().scanner;
        String rollToSearch = scanner.nextLine();
        try {
            List<Model> studentsFound = studentTable.retrieveStudentDataUsingRollNo(rollToSearch);
            if (studentsFound.isEmpty()) {
                Utils.printlnError("No Student Record FOUND with this Roll No...");
            } else {
                Utils.printlnSuccess("RECORD(s) FOUND...");
                for (Model student : studentsFound) {
                    System.out.println(student);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            Utils.printlnException("Exception caught in MainMenu.choiceSearchStudentCallback()");
            Utils.printlnException(e.getMessage());
            e.printStackTrace();
        }
    }

    private void choiceListAllStudentsCallback() {
        StudentTable studentTable = StudentTable.getInstance();
        System.out.println("\tALL STUDENTS RECORD \n");
        try {
            List<Model> allStudents = studentTable.queryAllRecords();
            if (allStudents.isEmpty()) {
                Utils.printlnError("Nothing to show, consider adding some Records");
            } else {
                for (int i = 0; i < allStudents.size(); i++) {
                    StudentModel student = (StudentModel) allStudents.get(i);
                    System.out.printf("%d. %s, %s-%s-%s [%s]", i + 1, student.getFullName(),
                            student.getBranch().toString(), student.getYearOfStudy().toString(),
                            student.getDivision().toString(), student.getRollNo()).println();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            Utils.printlnException("Exception caught in MainMenu.choiceListAllStudentsCallback()");
            Utils.printlnException(e.getMessage());
            e.printStackTrace();
        }

    }

    private void choiceDeleteStudentCallback() {
        StudentTable studentTable = StudentTable.getInstance();
        System.out.print("Enter Roll No of Student to Delete Record: ");
        Scanner scanner = Utils.getInstance().scanner;
        String rollToDelete = scanner.next();
        try {
            boolean recordDeleted = studentTable.deleteStudentDataUsingRollNo(rollToDelete);
            if (recordDeleted) {
                Utils.printlnSuccess("STUDENT RECORD DELETED SUCCESSFULLY...");
            } else {
                Utils.printlnError("NO SUCH STUDENT RECORD EXIST ALREADY...");
            }
        } catch (ClassNotFoundException | SQLException e) {
            Utils.printlnException("Exception caught in MainMenu.choiceDeleteStudentCallback()");
            Utils.printlnException(e.getMessage());
            e.printStackTrace();
        }
    }

    private void choiceUpdateStudentCallback() {
        StudentTable studentTable = StudentTable.getInstance();
        Scanner scanner = Utils.getInstance().scanner;
        System.out.print("Enter Roll No of Student to Update Record: ");
        String rollToUpdate = scanner.nextLine();
        try {
            List<Model> studentsFound = studentTable.retrieveStudentDataUsingRollNo(rollToUpdate);
            if (studentsFound.isEmpty()) {
                Utils.printlnError("NO SUCH STUDENT RECORD EXIST ALREADY...");
                return;
            }
            System.out.println("\tCURRENT STUDENT RECORD");
            System.out.println(studentsFound.get(0));
            System.out.println("\tPLEASE FILL IN UPDATED STUDENT RECORD for Roll No: " + rollToUpdate);
            Model studentToUpdate = StudentModel.fromUserUpdateRequestInput(rollToUpdate);
            boolean recordUpdated = studentTable.updateStudentData(studentToUpdate);
            if (recordUpdated) {
                Utils.printlnSuccess("STUDENT RECORD UPDATED SUCCESSFULLY...");
            } else {
                Utils.printlnError("NO SUCH STUDENT RECORD EXIST ALREADY...");
            }
        } catch (ClassNotFoundException | SQLException e) {
            Utils.printlnException("Exception caught in MainMenu.choiceUpdateStudentCallback()");
            Utils.printlnException(e.getMessage());
            e.printStackTrace();
        }
    }
}
