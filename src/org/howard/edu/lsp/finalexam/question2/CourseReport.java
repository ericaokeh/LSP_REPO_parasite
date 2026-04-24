package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete report implementation that generates a course report.
 * Displays the course name and enrollment count.
 */
public class CourseReport extends Report {

    private String courseName;
    private int enrollment;

    /** Loads course data. */
    @Override
    protected void loadData() {
        courseName = "CSCI 363";
        enrollment = 45;
    }

    /** Prints the course report header. */
    @Override
    protected void formatHeader() {
        System.out.println("Course Report");
    }

    /** Prints the course name and enrollment count. */
    @Override
    protected void formatBody() {
        System.out.println("Course: " + courseName);
        System.out.println("Enrollment: " + enrollment);
    }

    /** Prints the course report footer. */
    @Override
    protected void formatFooter() {
        System.out.println("End of Course Report");
    }
}