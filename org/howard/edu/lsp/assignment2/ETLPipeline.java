/*
 * Erica Okeh
 * 
 * ETL Pipeline that reads product data from a CSV file, applies transformations,
 * and writes the transformed data to a new CSV file.
 * 
 * Transformations:
 * 1. Convert product names to uppercase.
 * 2. Apply a 10% discount to Electronics category products.
 * 3. Reclassify Electronics products with price > $500 to "Premium Electronics".
 * 4. Add a PriceRange column based on the price.
 * 
 * Handles errors such as missing files, incorrect formats, and empty files.
 */

package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ETLPipeline {
    public static void main(String[] args) {
        String inputFilePath = "data/products.csv";
        String outputFilePath = "data/transformed_products.csv";

        List<String[]> transformedRows = new ArrayList<>();
        int rowsRead = 0, rowsTransformed = 0, rowsSkipped = 0;

        try {
            // Check if input file exists
            File inputFile = new File(inputFilePath);
            if (!inputFile.exists()) {
                System.out.println("Error: Input file not found at " + inputFilePath);
                return;
            }

            // Read the input file
            List<String> lines = Files.readAllLines(Paths.get(inputFilePath));
            if (lines.isEmpty()) {
                // Handle empty input file
                Files.write(Paths.get(outputFilePath), Collections.singletonList("ProductID,Name,Price,Category,PriceRange"));
                System.out.println("Input file is empty. Output file created with only the header.");
                return;
            }

            // Process rows
            String header = lines.get(0);
            transformedRows.add(header.split(","));
            for (int i = 1; i < lines.size(); i++) {
                rowsRead++;
                String line = lines.get(i).trim();

                // Skip blank rows or rows with incorrect format
                if (line.isEmpty() || line.split(",").length != 4) {
                    rowsSkipped++;
                    continue;
                }

                String[] fields = line.split(",");
                try {
                    int productId = Integer.parseInt(fields[0].trim());
                    String name = fields[1].trim().toUpperCase();
                    double price = Double.parseDouble(fields[2].trim());
                    String category = fields[3].trim();

                    // Apply transformations
                    if (category.equalsIgnoreCase("Electronics")) {
                        price *= 0.9; // Apply 10% discount
                        price = roundPrice(price);
                        if (price > 500.00) {
                            category = "Premium Electronics";
                        }
                    }

                    String priceRange = determinePriceRange(price);
                    transformedRows.add(new String[]{
                            String.valueOf(productId),
                            name,
                            String.format("%.2f", price),
                            category,
                            priceRange
                    });
                    rowsTransformed++;
                } catch (NumberFormatException e) {
                    rowsSkipped++;
                }
            }

            // Write transformed data to output file
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilePath))) {
                for (String[] row : transformedRows) {
                    writer.write(String.join(",", row));
                    writer.newLine();
                }
            }

            // Print summary
            System.out.println("Run Summary:");
            System.out.println("Rows read: " + rowsRead);
            System.out.println("Rows transformed: " + rowsTransformed);
            System.out.println("Rows skipped: " + rowsSkipped);
            System.out.println("Output file written to: " + outputFilePath);

        } catch (IOException e) {
            System.out.println("Error: Unable to process the file.");
        }
    }

    private static double roundPrice(double price) {
        return Math.round(price * 100.0) / 100.0;
    }

    private static String determinePriceRange(double price) {
        if (price <= 10.00) {
            return "Low";
        } else if (price <= 100.00) {
            return "Medium";
        } else if (price <= 500.00) {
            return "High";
        } else {
            return "Premium";
        }
    }
}