# Development Log – Question 3

**Author:** Erica

## Resources Used
- AI (ChatGPT) to validate Strategy Pattern design.
- Lecture notes on Strategy Pattern.
- Java documentation for interfaces and polymorphism.

## Reasoning & Process
- Recognized that PriceCalculator with multiple if-statements is not maintainable.  
- Defined `PriceStrategy` interface.  
- Implemented separate strategy classes: RegularPrice, MemberPrice, VIPPrice, HolidayPrice.  
- Updated PriceCalculator to accept a PriceStrategy instance.  
- Created Driver to test all customer types with base price 100.0.  
- Verified output matches spec: REGULAR 100.0, MEMBER 90.0, VIP 80.0, HOLIDAY 85.0.  
- AI was used to review design approach; all code and implementation decisions were independently written.