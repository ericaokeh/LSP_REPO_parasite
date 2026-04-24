Heuristic 1:
Name:
Distribute system intelligence horizontally as uniformly as possible

Explanation:
This heuristic means that responsibilities should be spread across multiple classes rather than concentrated in one central class. In lecture, this was illustrated using the home heating system example, where instead of one class controlling everything, classes like Room and Furnace communicate as peers. It improves readability because each class has a single, focused responsibility, making it easier to understand what each class does. It improves maintainability because changes to one class do not ripple through the entire system, making it easier to modify and extend.



Heuristic 2:
Name:
Do not create god classes/objects in your system

Explanation:
This heuristic warns against creating a single class that controls most of the system's behavior. In lecture, the HeatFlowRegulator was shown as a "god class" because it gathered low-level data from Room objects and made all decisions itself. It improves readability because smaller, focused classes are easier to read and understand than one large class doing everything. It improves maintainability because when behavior is spread appropriately, modifying one part of the system does not require understanding or touching the entire codebase.



Heuristic 3:
Name:
Beware of classes that have many access methods defined in their public interface

Explanation:
This heuristic focuses on reducing coupling and keeping behavior with data. In lecture, it was shown that having many get and set methods can indicate poor design, where one class is repeatedly accessing another class's data to perform logic. In the example, HeatFlowRegulator relied heavily on Room's access methods instead of letting Room decide when it needed heat. It improves readability because moving logic into the class that owns the data results in cleaner, more intuitive interfaces. It improves maintainability because reducing dependencies between classes means changes in one class are less likely to break others.
