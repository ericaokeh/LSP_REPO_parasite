package org.howard.edu.lsp.midterm.strategy;

/**
 * Driver to demonstrate Strategy Pattern for PriceCalculator.
 *
 * @author Erica
 */
public class Driver {

    /**
     * Main method demonstrating all pricing strategies.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        double basePrice = 100.0;

        PriceCalculator regularCalc = new PriceCalculator(new RegularPrice());
        PriceCalculator memberCalc = new PriceCalculator(new MemberPrice());
        PriceCalculator vipCalc = new PriceCalculator(new VIPPrice());
        PriceCalculator holidayCalc = new PriceCalculator(new HolidayPrice());

        System.out.println("REGULAR: " + regularCalc.calculatePrice(basePrice));
        System.out.println("MEMBER: " + memberCalc.calculatePrice(basePrice));
        System.out.println("VIP: " + vipCalc.calculatePrice(basePrice));
        System.out.println("HOLIDAY: " + holidayCalc.calculatePrice(basePrice));
    }
}