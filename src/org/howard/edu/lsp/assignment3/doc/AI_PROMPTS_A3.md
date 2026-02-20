# AI Prompts Transcript (Assignment 3)


This file lists key prompts I used with the AI assistant and short excerpts of the resulting guidance/changes.
The prompts are phrased to show step-by-step understanding, design reasoning, and verification.


## Prompt 1
**Prompt:**
"Can you help me analyze my Assignment 2 ETL code and point out which parts have mixed responsibilities? I want to understand what to separate first to make it more object-oriented."


**AI response excerpt:**
- Identified the long `main` method as mixing input, parsing, transformation, and output logic.
- Suggested introducing a product data model and splitting responsibilities into parser/transformer/writer/orchestrator.
- Explained why this improves readability and maintainability without changing behavior.


## Prompt 2
**Prompt:**
"Before changing code, can we define a class-by-class plan that preserves Assignment 2 behavior exactly (same paths, transformations, skipping rules, and error messages)?"


**AI response excerpt:**
- Proposed a behavior-preserving decomposition:
 - `ETLPipeline` (main/orchestrator)
 - `ProductRowParser`
 - `ProductTransformer`
 - `ProductCsvWriter`
 - `ProductRecord`
- Highlighted invariants to protect: `data/products.csv` input, `data/transformed_products.csv` output, and unchanged transformation order.


## Prompt 3
**Prompt:**
"Let’s implement this incrementally and verify after each step so I can see how the refactor keeps the same outputs."


**AI response excerpt:**
- Created Assignment 3 source files with package `org.howard.edu.lsp.assignment3`.
- Implemented the same transformation and skip/error logic as Assignment 2.
- Compiled and ran both A2 and A3 to verify matching behavior.


## Prompt 4
**Prompt:**
"Can you explain the folder/package placement and then align it with my course structure so Assignment 3 is in the expected location?"


**AI response excerpt:**
- Clarified the difference between source-root layout and package naming.
- Moved files to match the required `src/org/howard/edu/lsp/assignment3` structure.


## Prompt 5
**Prompt:**
"Please review my Assignment 3 against the rubric and show what documentation is still missing, especially Javadocs for classes and public methods."


**AI response excerpt:**
- Added Javadocs to all Assignment 3 classes and public methods.
- Kept logic unchanged and verified compilation after documentation updates.


## Prompt 6
**Prompt:**
"Can we run a final checklist pass including required output file, reflection, AI transcript, and special test cases (missing input and empty input)?"


**AI response excerpt:**
- Added `REFLECTION_A3.md` and `AI_PROMPTS_A3.md` in the required doc folder.
- Verified normal run output and validated missing-input and empty-input behavior.


## Notes
- Prompts and excerpts are summarized for clarity.
- I reviewed each AI suggestion before applying it to ensure assignment requirements were still met.


