package org.howard.edu.lsp.finalexam.question2;

/**
 * Abstract base class that defines the Template Method pattern for generating reports.
 * Subclasses must implement each step of the report workflow.
 */
public abstract class Report {

    /**
     * Template method that defines the fixed report generation workflow.
     * Executes steps in the required order.
     */
    public final void generateReport() {
        loadData();

        System.out.println("=== HEADER ===");
        formatHeader();

        System.out.println("=== BODY ===");
        formatBody();

        System.out.println("=== FOOTER ===");
        formatFooter();
    }

    /** Loads data required for the report. */
    protected abstract void loadData();

    /** Formats and prints the report header content. */
    protected abstract void formatHeader();

    /** Formats and prints the report body content. */
    protected abstract void formatBody();

    /** Formats and prints the report footer content. */
    protected abstract void formatFooter();
}