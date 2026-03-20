package org.howard.edu.lsp.midterm.strategy;

/**
 * Regular pricing strategy — no discount applied.
 */
public class RegularPrice implements PriceStrategy {

    @Override
    public double calculate(double price) {
        return price;
    }
}
