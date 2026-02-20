package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * An abstract base class providing common CSV writing functionality.
 * Demonstrates Inheritance.
 */
public abstract class BaseCsvWriter {

    /**
     * Subclasses must provide the specific CSV header string.
     * @return the CSV header row
     */
    protected abstract String getHeader();

    /**
     * Writes only the header to the specified file path.
     * @param filePath the destination file path
     * @throws IOException if a file access error occurs
     */
    public void writeHeaderOnly(String filePath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(getHeader());
            bw.newLine();
        }
    }
}