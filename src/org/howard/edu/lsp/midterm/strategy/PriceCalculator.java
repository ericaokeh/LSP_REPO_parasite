package org.howard.edu.lsp.midterm.strategy;

/**
 * PriceCalculator uses a PriceStrategy to calculate final price.
 *
 * @author Erica
 */
public class PriceCalculator {

    private PriceStrategy strategy;

    /**
     * Constructs a PriceCalculator with the given pricing strategy.
     *
     * @param strategy the pricing strategy to use
     */
    public PriceCalculator(PriceStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the final price using the current strategy.
     *
     * @param price the original price
     * @return the final price after applying the strategy
     */
    public double calculatePrice(double price) {
        return strategy.calculate(price);
    }
}