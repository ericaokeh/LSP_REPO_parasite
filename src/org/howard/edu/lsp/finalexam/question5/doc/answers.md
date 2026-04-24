Heuristic 1:
Name:
Distribute system intelligence horizontally as uniformly as possible

Explanation:
This heuristic means that responsibilities should be spread across multiple classes rather than concentrated in one central class. In lecture, this was illustrated using the home heating system example, where instead of one class controlling everything, classes like Room and Furnace communicate as peers. This improves maintainability and flexibility because responsibilities are distributed, making the system easier to modify and extend.



Heuristic 2:
Name:
Do not create god classes/objects in your system

Explanation:
This heuristic warns against creating a single class that controls most of the system’s behavior. In lecture, the HeatFlowRegulator was shown as a “god class” because it gathered low-level data from Room objects and made all decisions itself. It acted as an “omnipotent controller” that did not allow other classes to participate in decision-making. This makes the system harder to maintain and modify because too much responsibility is concentrated in one place.


Heuristic 3:
Name:
Beware of classes that have many access methods defined in their public interface

Explanation:
This heuristic focuses on reducing coupling and keeping behavior with data. In lecture, it was shown that having many get and set methods can indicate poor design, where one class is repeatedly accessing another class’s data to perform logic. In the example, HeatFlowRegulator relied heavily on Room’s access methods instead of letting Room decide when it needed heat. The improved design moved this responsibility into the Room class, reducing dependency and making the system cleaner and easier to maintain.


