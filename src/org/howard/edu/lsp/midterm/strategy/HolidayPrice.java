package org.howard.edu.lsp.midterm.strategy;

/**
 * Holiday pricing strategy — applies a 15% discount.
 */
public class HolidayPrice implements PriceStrategy {

    @Override
    public double calculate(double price) {
        return price * 0.85;
    }
}
