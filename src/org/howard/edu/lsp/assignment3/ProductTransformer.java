package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
  Transforms parsed string data into strongly-typed ProductRecord objects.
  Implements the Transformer interface to demonstrate Polymorphism.
 */
public class ProductTransformer implements Transformer<ProductRowParser.ParsedRow, ProductRecord> {

    @Override
    public ProductRecord transform(ProductRowParser.ParsedRow input) throws NumberFormatException {
        ProductRecord record = new ProductRecord();
        
        record.setId(Integer.parseInt(input.id));
        
        // 1. Transform Name to Uppercase
        record.setName(input.name.toUpperCase());
        
        double price = Double.parseDouble(input.price);
        String category = input.category;
        
        // 2. Apply Electronics discount FIRST
        if (category.equalsIgnoreCase("Electronics")) {
            price *= 0.9;
        }
        
        // 3. Round price for ALL products using BigDecimal for exact HALF_UP rounding
        BigDecimal bd = BigDecimal.valueOf(price);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        price = bd.doubleValue();
        
        // 4. Check for Premium Electronics AFTER rounding
        if (category.equalsIgnoreCase("Electronics") && price > 500.00) {
            category = "Premium Electronics";
        }
        
        record.setPrice(price);
        record.setCategory(category);
        
        // 5. Determine PriceRange based on the final rounded price
        record.setPriceRange(determinePriceRange(price));
        
        return record;
    }

    /**
     * Helper method to determine the price range category.
     */
    private String determinePriceRange(double price) {
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