package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete report implementation that generates a student report.
 * Displays the student's name and GPA.
 */
public class StudentReport extends Report {

    private String studentName;
    private double gpa;

    /** Loads student data. */
    @Override
    protected void loadData() {
        studentName = "John Doe";
        gpa = 3.8;
    }

    /** Prints the student report header. */
    @Override
    protected void formatHeader() {
        System.out.println("Student Report");
    }

    /** Prints the student's name and GPA. */
    @Override
    protected void formatBody() {
        System.out.println("Student Name: " + studentName);
        System.out.println("GPA: " + gpa);
    }

    /** Prints the student report footer. */
    @Override
    protected void formatFooter() {
        System.out.println("End of Student Report");
    }
}