# AI Usage Report — Assignment 5: IntegerSet

**Student:** Erica Okeh  
**Course:** Large Scale Programming (LSP)  
**Assignment:** Assignment 5 — IntegerSet Implementation  
**Date:** April 2026  

---

## Overview

I completed the core implementation of this assignment independently, drawing on prior knowledge of the Java Collections Framework, object-oriented design, and set theory from discrete mathematics. I used AI (Claude, Anthropic, Gemini) sparingly — only to clarify specific conceptual questions and strict requirement interpretations I encountered mid-implementation, not to generate code. All logic, design decisions, and test cases are my own.

---

## AI Interactions

### Inquiry 1 — `retainAll` mutation behavior

**My question:**
> I know `retainAll` modifies the list in-place, but I want to confirm: if I call `listA.retainAll(listB)`, does it mutate `listA` directly or return a new list? I'm building `intersect()` and I want to make sure I copy first before calling it, since the spec says originals must not be modified.

**AI response summary:**
Confirmed that `retainAll` mutates the calling list in-place and returns a `boolean` (whether the list changed), not a new list. This validated my plan to copy `this.set` into a new `ArrayList` before calling `retainAll`.

**How I used it:**
I had already written the copy-first pattern in my draft. This was purely a confirmation to make sure I wasn't misreading the Javadoc. No code was generated.

---

### Inquiry 2 — Handling exceptions while adhering to "Implement only IntegerSet.java"

**My question:**
> The spec requires me to throw an exception for operations like `largest()` on an empty set. I initially created a custom `IntegerSetException.java` file for this. However, the instructions strictly say "Implement only IntegerSet.java" and state that if the code doesn't compile, it's an automatic 0. Will submitting a second file break the grading script? 

**AI response summary:**
The AI pointed out that submitting a separate custom exception file violates the explicit "only IntegerSet.java" requirement. If the automated grader only compiles the main file, it will fail to find the custom exception, resulting in a zero. It recommended using standard, built-in Java exceptions like `IllegalStateException` (for empty sets) and `IllegalArgumentException` (for null arguments) instead.

**How I used it:**
This was a crucial clarification of the rubric's constraints. I deleted my custom `IntegerSetException.java` file and updated my validation checks to throw standard Java unchecked exceptions, ensuring my submission remains strictly single-file.

---

### Inquiry 3 — `Collections.sort` inside `toString` and side effects

**My question:**
> I need `toString()` to return a sorted string representation of the set, like `[1, 2, 3]`. If I use `Collections.sort(set)` directly on my backing `ArrayList`, it sorts it in-place. Even though sets are unordered mathematically, is it bad practice to mutate the internal list just to print it?

**AI response summary:**
The AI confirmed that while a mathematical set has no inherent order, mutating the internal data structure solely for a `toString()` call introduces an unnecessary side effect. It recommended sorting a copy to be completely safe and maintain strict encapsulation.

**How I used it:**
I decided to adopt the safer approach. Instead of sorting in-place, I used my existing private `getCopy()` method to create a defensive copy of the list, sorted that copy, and then returned its string representation. 

---

### Inquiry 4 — `equals(IntegerSet b)` vs. `equals(Object o)` in Java

**My question:**
> The spec lists `public boolean equals(IntegerSet b)`. If I only define that overload and not `equals(Object o)`, does that break anything? Does Java treat these as the same method?

**AI response summary:**
They are distinct. `equals(IntegerSet b)` is an overload — it does not override `Object.equals(Object o)`. If code calls `.equals()` through a reference typed as `Object`, only the `Object` version is invoked. For this assignment, since Driver.java calls `equals` directly on `IntegerSet` references, the overloaded version is called correctly. Overriding `Object.equals` as well would be more robust, but the spec's signature works as-is for the driver.

**How I used it:**
I had already written `equals(IntegerSet b)` and was curious whether the Java compiler would treat it as an override. To be as robust as possible, I ended up providing the required `equals(IntegerSet b)` method, but also formally overrode `equals(Object o)` and `hashCode()` to satisfy the full Java equality contract.

---

### Inquiry 5 — JUnit 5 `assertThrows` syntax confirmation

**My question:**
> I want to verify: in JUnit 5, to assert that a method throws a specific exception, I use `assertThrows(ExceptionClass.class, () -> methodCall())`. Is the lambda required, or can I pass a method reference?

**AI response summary:**
Both work. The lambda `() -> obj.method()` and a method reference `obj::method` (when the method takes no arguments) are both valid as `Executable` arguments to `assertThrows`. For clarity in test code, the lambda form is more common.

**How I used it:**
I already knew `assertThrows` existed from prior coursework. I just hadn't used JUnit 5 specifically in a while and wanted to confirm the exact syntax before writing the exception tests for `largest()` and `smallest()` on empty sets.

---

## External References Used

| Resource | Purpose |
|---|---|
| [Java SE Docs — ArrayList](https://docs.oracle.com/en/java/docs/api/java.base/java/util/ArrayList.html) | Reviewed `addAll`, `removeAll`, `retainAll`, `contains`, `remove(Object)` vs `remove(int)` |
| [Java SE Docs — Collections](https://docs.oracle.com/en/java/docs/api/java.base/java/util/Collections.html) | Confirmed behavior of `Collections.sort`, `Collections.min`, `Collections.max` |
| [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/) | Referenced `@Test`, `@BeforeEach`, `@DisplayName`, `assertThrows`, `assertDoesNotThrow` |
| Discrete Mathematics course notes | Reviewed formal definitions of union, intersection, difference, and complement |

---

## What I Did Not Use AI For

- Designing the class structure or choosing `ArrayList` as the backing data structure.
- Writing any of the required methods or the underlying set-algebra logic.
- Writing any of the JUnit 5 test cases.
- Deciding which edge cases to test (empty sets, single elements, disjoint sets, negative numbers, duplicate adds).
- The package structure or file organization.

---

## Reflection

The most interesting design challenge was ensuring that all set operations (`union`, `intersect`, `diff`, `complement`) return a new `IntegerSet` without mutating the originals. Methods like `retainAll` and `removeAll` operate in-place, so the pattern of copying `this.set` into a result set before applying those operations was essential. I also found it intellectually satisfying to implement `complement` as the inverse of `diff` — B minus A — and to write tests that explicitly verified both directions are distinct operations.

Testing edge cases (two empty sets, one empty and one non-empty, identical sets, disjoint sets) was something I planned from the start based on experience with instructor-level edge-case testing in prior assignments. Adapting to the strict "single file" requirement by shifting to built-in exceptions was a great lesson in reading rubrics carefully.