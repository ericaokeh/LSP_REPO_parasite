package org.howard.edu.lsp.midterm.strategy;

/**
 * Strategy for MEMBER customers – 10% discount.
 */
public class MemberPrice implements PriceStrategy {
    public double calculate(double price) {
        return price * 0.90;
    }
}