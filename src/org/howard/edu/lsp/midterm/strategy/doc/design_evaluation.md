# PriceCalculator Design Evaluation

The original `PriceCalculator` class used multiple `if` statements to calculate discounts for different customer types.  

Design issues:
- **Violation of Open/Closed Principle:** Adding a new customer type requires modifying the class.  
- **Difficult to maintain:** All discount logic is in one method.  
- **Poor extensibility:** Cannot easily add or change discount rules without touching existing code.

Refactoring using the Strategy Pattern decouples discount logic from the calculator, making the system modular, maintainable, and extensible.