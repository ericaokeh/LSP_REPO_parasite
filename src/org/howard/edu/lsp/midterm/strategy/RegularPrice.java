package org.howard.edu.lsp.midterm.strategy;

/**
 * Strategy for REGULAR customers – no discount.
 *
 * @author Erica
 */
public class RegularPrice implements PriceStrategy {

    /**
     * Returns the full price with no discount applied.
     *
     * @param price the original price
     * @return the original price unchanged
     */
    public double calculate(double price) {
        return price;
    }
}