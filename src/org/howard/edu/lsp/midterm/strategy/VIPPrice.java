package org.howard.edu.lsp.midterm.strategy;

/**
 * Strategy for VIP customers – 20% discount.
 *
 * @author Erica
 */
public class VIPPrice implements PriceStrategy {

    /**
     * Applies a 20% discount to the given price.
     *
     * @param price the original price
     * @return the price after 20% discount
     */
    public double calculate(double price) {
        return price * 0.80;
    }
}