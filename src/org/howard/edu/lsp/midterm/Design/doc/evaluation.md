# Evaluation of OrderProcessor Design

The original `OrderProcessor` class violates several object-oriented design principles:

1. **Single Responsibility Principle:** The class handles multiple responsibilities: calculating tax, printing receipts, writing to files, sending emails, applying discounts, and logging. This makes the class difficult to maintain or extend.  

2. **Encapsulation:** Fields such as `customerName`, `email`, `item`, and `price` are public. This exposes internal data and allows external code to modify it arbitrarily.  

3. **Tight Coupling:** Logic for printing, file I/O, emailing, and discount application is tightly coupled. Changes in one behavior could inadvertently break others.  

4. **Maintainability & Extensibility:** Adding new features, such as different discount policies or logging methods, requires modifying the core class, violating the Open/Closed Principle.

Overall, the current design is fragile and not scalable. Refactoring into smaller, well-defined classes improves maintainability, testability, and reusability.