package org.howard.edu.lsp.midterm.strategy;

/**
 * Strategy for VIP customers – 20% discount.
 */
public class VIPPrice implements PriceStrategy {
    public double calculate(double price) {
        return price * 0.80;
    }
}