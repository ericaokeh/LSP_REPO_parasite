# Assignment 3 Reflection

## What is different about the design?
In Assignment 2, my solution was highly procedural. All of the logic—reading the file, parsing strings, applying business rules, and writing the output—was centralized within a single `main` method in the `ETLPipeline` class. According to our object-oriented heuristics, this created a "God Class" that knew too much and did too much. 

In Assignment 3, I redesigned the system using concepts from CRC (Class-Responsibility-Collaborator) card modeling. I identified the necessary actors and distributed the intelligence horizontally. `ProductRowParser` has the sole responsibility of string extraction, `ProductTransformer` handles the business rules, and `ProductCsvWriter` handles file I/O. 

Additionally, I improved the accuracy of the business logic during this redesign. In Assignment 2, I used `Math.round()` and checked the > $500 "Premium Electronics" threshold *before* rounding. In Assignment 3's `ProductTransformer`, I implemented `BigDecimal` with `RoundingMode.HALF_UP` to precisely meet the spec's rounding rules, and I deliberately moved the "Premium" check to occur *after* the final rounded price is calculated to ensure edge cases are handled correctly.

## How is Assignment 3 more object-oriented?
Assignment 3 moves away from a top-down procedural script and instead relies on objects passing messages to one another. Rather than juggling raw `String[]` arrays, the pipeline constructs meaningful domain objects (`ProductRecord`) that carry their own state. By eliminating the centralized "God Class," the new design achieves strong cohesion (each class has a single, focused responsibility) and loose coupling. The `ETLPipeline` now acts simply as an orchestrator that delegates tasks to its specialized collaborators.

## Which OO ideas did you use?
* **Class & Object:** I defined specific blueprints mapping to distinct CRC responsibilities (e.g., parsing, transforming, writing) and instantiated them into objects to perform specific tasks.
* **Encapsulation:** I applied the heuristic of data hiding in `ProductRecord`. All data fields (id, name, price, category, priceRange) are marked as `private` and can only be accessed or modified through public getters and setters, protecting the internal state of the object.
* **Inheritance:** I created an abstract base class called `BaseCsvWriter` that contains common logic for writing CSV headers. My concrete class, `ProductCsvWriter`, extends this base class, inheriting the file-writing capabilities while supplying its own specific header.
* **Polymorphism:** I created a generic `Transformer<I, O>` interface. `ProductTransformer` implements this interface, establishing a contract. This allows the orchestrator to rely on the polymorphic `transform()` method without needing to know the complex implementation details inside.

## Explain how you tested to confirm Assignment 3 works the same as Assignment 2
I verified the program's correctness by running it against the original `products.csv` dataset and performing a side-by-side comparison of the resulting `transformed_products.csv`. Both outputs matched, including the new `PriceRange` column. 

I also explicitly tested edge cases to ensure the robust error handling from Assignment 2 carried over. I renamed the input file to trigger the "missing file" error, and ran the pipeline on a file with only a header to confirm the "empty input" logic worked precisely as it did before. Finally, I verified that the new `BigDecimal` logic accurately rounded prices and correctly classified products right on the boundary of the $500 Premium threshold.