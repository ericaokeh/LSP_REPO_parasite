package org.howard.edu.lsp.midterm.strategy;

/**
 * Strategy for REGULAR customers – no discount.
 */
public class RegularPrice implements PriceStrategy {
    public double calculate(double price) {
        return price;
    }
}