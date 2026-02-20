package org.howard.edu.lsp.assignment3;

/**
 * Represents a transformed product record ready to be written to the output file.
 * Demonstrates Encapsulation by hiding fields and exposing getters/setters.
 */
public class ProductRecord {
    private int id;
    private String name;
    private double price;
    private String category;
    private String priceRange;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getPriceRange() { return priceRange; }
    public void setPriceRange(String priceRange) { this.priceRange = priceRange; }

    /**
     * Formats the record as a CSV string.
     * @return a comma-separated string representation of the record
     */
    public String toCsvRow() {
        // Format price to exactly 2 decimal places to match Assignment 2 output
        return id + "," + name + "," + String.format("%.2f", price) + "," + category + "," + priceRange;
    }
}