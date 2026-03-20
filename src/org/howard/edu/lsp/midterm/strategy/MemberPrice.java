package org.howard.edu.lsp.midterm.strategy;

/**
 * Strategy for MEMBER customers – 10% discount.
 *
 * @author Erica
 */
public class MemberPrice implements PriceStrategy {

    /**
     * Applies a 10% discount to the given price.
     *
     * @param price the original price
     * @return the price after 10% discount
     */
    public double calculate(double price) {
        return price * 0.90;
    }
}