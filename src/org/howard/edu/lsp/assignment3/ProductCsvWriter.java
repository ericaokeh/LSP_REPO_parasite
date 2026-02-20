package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Handles the writing of ProductRecord objects to a CSV file.
 * Extends BaseCsvWriter to demonstrate Inheritance.
 */
public class ProductCsvWriter extends BaseCsvWriter {

    @Override
    protected String getHeader() {
        return "ProductID,Name,Price,Category,PriceRange";
    }

    /**
     * Writes a list of transformed ProductRecords to a CSV file.
     * @param filePath the destination file path
     * @param records the list of ProductRecords to write
     * @throws IOException if a file access error occurs
     */
    public void write(String filePath, List<ProductRecord> records) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(getHeader());
            bw.newLine();
            
            for (ProductRecord record : records) {
                bw.write(record.toCsvRow());
                bw.newLine();
            }
        }
    }
}
