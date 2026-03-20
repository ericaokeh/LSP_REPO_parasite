package org.howard.edu.lsp.midterm.strategy;

/**
 * Context class for the Strategy Pattern.
 * Uses a PriceStrategy to calculate the final price.
 */
public class PriceCalculator {

    private PriceStrategy strategy;

    public PriceCalculator(PriceStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PriceStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculate(double price) {
        return strategy.calculate(price);
    }
}
