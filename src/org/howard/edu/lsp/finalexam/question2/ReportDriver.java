package org.howard.edu.lsp.finalexam.question2;

import java.util.ArrayList;
import java.util.List;

/**
 * Driver class that demonstrates the Template Method pattern using polymorphism.
 * Adds multiple report types to a list and generates each one.
 */
public class ReportDriver {

    /**
     * Main method that creates and runs StudentReport and CourseReport.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        List<Report> reports = new ArrayList<>();

        reports.add(new StudentReport());
        reports.add(new CourseReport());

        for (Report report : reports) {
            report.generateReport();
            System.out.println();
        }
    }
}