package org.howard.edu.lsp.midterm.strategy;

/**
 * Member pricing strategy — applies a 10% discount.
 */
public class MemberPrice implements PriceStrategy {

    @Override
    public double calculate(double price) {
        return price * 0.90;
    }
}
