package org.howard.edu.lsp.midterm.strategy;

/**
 * PriceCalculator uses a PriceStrategy to calculate final price.
 */
public class PriceCalculator {
    private PriceStrategy strategy;

    public PriceCalculator(PriceStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculatePrice(double price) {
        return strategy.calculate(price);
    }
}