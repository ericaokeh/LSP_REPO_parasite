package org.howard.edu.lsp.midterm.strategy;

/**
 * Strategy for HOLIDAY customers – 15% discount.
 *
 * @author Erica
 */
public class HolidayPrice implements PriceStrategy {

    /**
     * Applies a 15% discount to the given price.
     *
     * @param price the original price
     * @return the price after 15% discount
     */
    public double calculate(double price) {
        return price * 0.85;
    }
}