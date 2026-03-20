package org.howard.edu.lsp.midterm.strategy;

/**
 * Strategy for HOLIDAY customers – 15% discount.
 */
public class HolidayPrice implements PriceStrategy {
    public double calculate(double price) {
        return price * 0.85;
    }
}