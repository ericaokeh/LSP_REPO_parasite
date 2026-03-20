package org.howard.edu.lsp.midterm.strategy;

/**
 * Driver class to demonstrate the Strategy Pattern for pricing.
 */
public class Driver {

    public static void main(String[] args) {
        double basePrice = 100.00;

        PriceCalculator calculator = new PriceCalculator(new RegularPrice());
        System.out.println("Regular Price: $" + calculator.calculate(basePrice));

        calculator.setStrategy(new MemberPrice());
        System.out.println("Member Price:  $" + calculator.calculate(basePrice));

        calculator.setStrategy(new VIPPrice());
        System.out.println("VIP Price:     $" + calculator.calculate(basePrice));

        calculator.setStrategy(new HolidayPrice());
        System.out.println("Holiday Price: $" + calculator.calculate(basePrice));
    }
}
