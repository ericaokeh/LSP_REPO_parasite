package org.howard.edu.lsp.assignment3;

/**
 * Parses a raw CSV string into a usable data structure.
 */
public class ProductRowParser {

    /**
     * A simple Data Transfer Object (DTO) to hold raw string data.
     */
    public static class ParsedRow {
        public String id;
        public String name;
        public String price;
        public String category;
    }

    /**
     * Parses a line of CSV text into a ParsedRow object.
     * @param line the raw CSV line
     * @return a ParsedRow object, or null if the line is invalid
     */
    public ParsedRow parse(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] columns = line.split(",");
        
        // Match Assignment 2 logic: skip rows that don't have exactly 4 columns
        if (columns.length != 4) {
            return null; 
        }

        ParsedRow row = new ParsedRow();
        row.id = columns[0].trim();
        row.name = columns[1].trim();
        row.price = columns[2].trim();
        row.category = columns[3].trim();
        
        return row;
    }
}