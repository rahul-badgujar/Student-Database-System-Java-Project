package data_models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import db_helpers.tables.StudentTable;

public class StudentModel implements Model {

    public enum Branch {
        IT, CS, ETC, CIVIL, MECH, NOT_DEFINED
    }

    public enum Division {
        A, B, C, D, NOT_DEFINED
    }

    public enum Gender {
        MALE, FEMALE, TRANS, NOT_DEFINED
    }

    public enum YearOfStudy {
        FE, SE, TE, BE, NOT_DEFINED
    }

    private String rollno;
    private String fullname;
    private String dateofbirth;
    private Integer age;
    private Gender gender;
    private Branch branch;
    private YearOfStudy yearOfStudy;
    private Division division;
    private Double CGPA;
    private String phone;
    private String email;

    public String getFullName() {
        return this.fullname;
    }

    public void setFullName(String fullname) {
        this.fullname = fullname;
    }

    public String getRollNo() {
        return this.rollno;
    }

    public void setRollNo(String rollno) {
        this.rollno = rollno;
    }

    public String getDob() {
        return this.dateofbirth;
    }

    public void setDob(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Branch getBranch() {
        return this.branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public YearOfStudy getYearOfStudy() {
        return this.yearOfStudy;
    }

    public void setYearOfStudy(YearOfStudy yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Division getDivision() {
        return this.division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Double getCGPA() {
        return this.CGPA;
    }

    public void setCGPA(Double CGPA) {
        this.CGPA = CGPA;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentModel() {
        this.rollno = "NA";
        this.fullname = "NA";
        this.dateofbirth = "NA";
        this.age = 0;
        this.gender = Gender.NOT_DEFINED;
        this.branch = Branch.NOT_DEFINED;
        this.yearOfStudy = YearOfStudy.NOT_DEFINED;
        this.division = Division.NOT_DEFINED;
        this.CGPA = 0.0;
        this.phone = "0000000000";
        this.email = "na@na.com";
    }

    public static List<Model> fromSqlResult(ResultSet result) throws SQLException {
        List<Model> students = new ArrayList<>();
        while (result.next()) {
            StudentModel student = new StudentModel();
            student.setRollNo(result.getString(1));
            student.setFullName(result.getString(2));
            student.setDob(result.getString(3));
            student.setAge(result.getInt(4));
            student.setGender(Gender.valueOf(result.getString(5)));
            student.setBranch(Branch.valueOf(result.getString(6)));
            student.setYearOfStudy(YearOfStudy.valueOf(result.getString(7)));
            student.setDivision(Division.valueOf(result.getString(8)));
            student.setCGPA(result.getDouble(9));
            student.setPhone(result.getString(10));
            student.setEmail(result.getString(11));
            students.add(student);
        }
        return students;
    }

    public static Model fromUserInput() {
        Scanner scanner = new Scanner(System.in);
        StudentModel student = new StudentModel();

        System.out.print("Enter Roll No: ");
        student.rollno = scanner.nextLine();
        System.out.print("Enter Full Name: ");
        student.fullname = scanner.nextLine();
        System.out.print("Enter Date of Birth (DD/MM/YYYY): ");
        student.dateofbirth = scanner.nextLine();
        System.out.print("Enter Gender (MALE/FEMALE/TRANS/NOT_DEFINED): ");
        student.gender = Gender.valueOf(scanner.nextLine());
        System.out.print("Enter Branch (IT/CS/ETC/CIVIL/MECH/NOT_DEFINED): ");
        student.branch = Branch.valueOf(scanner.nextLine());
        System.out.print("Enter Year of Study (FE/SE/TE/BE): ");
        student.yearOfStudy = YearOfStudy.valueOf(scanner.nextLine());
        System.out.print("Enter Division (A/B/C/D/NOT_DEFINED): ");
        student.division = Division.valueOf(scanner.nextLine());
        System.out.print("Enter Phone: ");
        student.phone = scanner.nextLine();
        System.out.print("Enter Email: ");
        student.email = scanner.nextLine();
        System.out.print("Enter CGPA: ");
        student.CGPA = scanner.nextDouble();

        scanner.close();
        return student;
    }

    @Override
    public String toString() {
        return "Roll No: " + this.rollno + "\n" + "Full Name: " + this.fullname + "\n" + "Date of Birth: "
                + this.dateofbirth + "\n" + "Gender: " + this.gender + "\n" + "Age: " + this.age + "\n" + "Branch: "
                + this.branch + "\n" + "Year of Study: " + this.yearOfStudy + "\n" + "Division: " + this.division + "\n"
                + "CGPA: " + this.CGPA + "\n" + "Phone: " + this.phone + "\n" + "Email: " + this.email + "\n";
    }

    @Override
    public PreparedStatement toSqlInsertStatement(Connection connection) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Students(" + StudentTable.StudentTableColumn.ROLL_NO.toString() + ","
                + StudentTable.StudentTableColumn.FULLNAME.toString() + ","
                + StudentTable.StudentTableColumn.DOB.toString() + "," + StudentTable.StudentTableColumn.AGE.toString()
                + "," + StudentTable.StudentTableColumn.GENDER.toString() + ","
                + StudentTable.StudentTableColumn.BRANCH.toString() + ","
                + StudentTable.StudentTableColumn.YEAR_OF_STUDY.toString() + ","
                + StudentTable.StudentTableColumn.DIVISION.toString() + ","
                + StudentTable.StudentTableColumn.CGPA.toString() + ","
                + StudentTable.StudentTableColumn.PHONE.toString() + ","
                + StudentTable.StudentTableColumn.EMAIL.toString() + ") VALUES(?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, this.rollno);
        statement.setString(2, this.fullname);
        statement.setString(3, this.dateofbirth);
        statement.setInt(4, this.age);
        statement.setString(5, this.gender.toString());
        statement.setString(6, this.branch.toString());
        statement.setString(7, this.yearOfStudy.toString());
        statement.setString(8, this.division.toString());
        statement.setDouble(9, this.CGPA);
        statement.setString(10, this.phone);
        statement.setString(11, this.email);
        return statement;
    }

}
