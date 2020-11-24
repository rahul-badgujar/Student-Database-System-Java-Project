package data_models;

public class StudentModel {
    private int rollno;
    private String name;
    private double marks;

    public StudentModel() {
        this(0, "NO NAME", 0.0);
    }

    public StudentModel(int rollno, String name, double marks) {
        this.rollno = rollno;
        this.name = name;
        this.marks = marks;
    }

    public int getRollNo() {
        return this.rollno;
    }

    public String getName() {
        return this.name;
    }

    public double getMarks() {
        return this.marks;
    }

    @Override
    public String toString() {
        return "Roll No: " + this.rollno + "\n" + "Name: " + this.name + "\n" + "Marks: " + this.marks;
    }
}
