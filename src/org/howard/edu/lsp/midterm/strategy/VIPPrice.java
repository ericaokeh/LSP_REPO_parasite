package org.howard.edu.lsp.midterm.strategy;

/**
 * VIP pricing strategy — applies a 20% discount.
 */
public class VIPPrice implements PriceStrategy {

    @Override
    public double calculate(double price) {
        return price * 0.80;
    }
}
