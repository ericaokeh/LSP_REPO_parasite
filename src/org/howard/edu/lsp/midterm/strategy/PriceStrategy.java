package org.howard.edu.lsp.midterm.strategy;

/**
 * Interface for Strategy Pattern to calculate price.
 *
 * @author Erica
 */
public interface PriceStrategy {
    /**
     * Calculate final price based on strategy.
     * 
     * @param price initial price
     * @return final price
     */
    double calculate(double price);
}