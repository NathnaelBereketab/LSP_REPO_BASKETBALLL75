CSCI 363/540 – Assignment 2: CSV ETL Pipeline in Java

Overview
This project implements a ETL pipeline that reads `data/products.csv`, applies transformations, and writes `data/transformed_products.csv`.

How to Build & Run (from project root)
- Run from project root so relative `data/` paths resolve.
- Compile: `javac -d out src/org/howard/edu/lsp/assignment2/ETLPipeline.java`
- Run: `java -cp out org.howard.edu.lsp.assignment2.ETLPipeline`

Files
- Input: data/products.csv
- Output: data/transformed_products.csv

Assumptions
- CSV fields contain no commas or quotes; delimiter is a comma.
- First row is a header and is not transformed.
- Program is run from the project root so relative `data/` paths resolve.
- Malformed rows (wrong column count or bad numbers) are skipped and counted.
- Prices are non-negative; US locale formatting for two decimals in output.

Design Notes
- The implementation was broken into extract → transform → load methods.
- It transforms the order to match the UPPERCASE name → 10% discount for Electronics (round half up) → recategorize to Premium Electronics if final price > 500 and original category was Electronics → derive PriceRange from final price.
- Rounding uses `Math.round(value * 100.0) / 100.0`.
- If there is a missing input file prints a clear error and exits; empty input produces an output file with only the header.

Testing
- Case A (Normal): Used the provided sample input; verified output values match the golden preview (e.g., 899.99 for Laptop, 179.99 for Headphones, 629.54 for Smartphone) and correct `PriceRange`/category changes.
- Case B (Empty): Created a file with only the header; confirmed the program writes only the header to `data/transformed_products.csv` and prints a summary with 0 transformed rows.
- Case C (Missing): Temporarily renamed `data/products.csv`; confirmed a clear error message is printed and the program exits without attempting to write data rows.
- Malformed rows: Injected a few bad lines (wrong column count and non-numeric price); confirmed they are skipped and reflected in the `Rows skipped` count.

External Sources
- Oracle Docs – BigDecimal and RoundingMode
Link: https://docs.oracle.com/javase/8/docs/api/java/math/RoundingMode.html
What I used: Understanding HALF_UP semantics for two-decimal rounding.
How adapted: Implemented rounding with Math.round(value * 100.0) / 100.0 to meet the spec while keeping code simple.

Baeldung – Reading Files in Java
Link: https://www.baeldung.com/reading-file-in-java
What I used: Patterns for reading files with NIO (Files.newBufferedReader, Paths.get).
How adapted: Used BufferedReader with UTF-8 and manual CSV split(",") per assignment constraints (no third-party CSV libs).

AI Usage: Claude 4
- Summary: AI assistance was used to implement certain portions of the step by step implementation I designed. Also, I used it to enforce the transform order. 
- Prompt used:
  - Prompt: "Implement the transform step of recategorizing if final price > 500 and original was Electronics, and compute PriceRange."
  - AI response (excerpt): "After applying the Electronics discount and rounding to two decimals, if the final price exceeds $500 and the original category was Electronics, set the category to 'Premium Electronics', then derive the PriceRange from the final price."
  - How it was used: The response was adapted into explicit helper methods for rounding and price range, with category checks and required column order.
- Summary: Used AI for targeted implementation details (rounding and transform order). All structure and code are mine.
- Prompt used:
  - Prompt: "Where in the transform order should I apply rounding and the > $500 recategorization?"
    - AI response (excerpt): "Apply discount, then round; if the final price > $500 and the original category was Electronics, set to Premium Electronics; finally compute the price range."
    - How it was used: Confirmed order: uppercase → discount → round → recategorize → price range.
- Summary: Used AI to ensure the robustness and edge cases of my solution.
  - Prompt: "Any edge cases to consider when splitting CSV lines by comma if we don't support quotes?"
  - AI response (excerpt): "If no quotes are supported, splitting by comma is acceptable; trim fields, validate expected column count, and skip malformed/empty lines."
    - How it was used: Kept simple split(",") with trimming and strict field count (4), skipping malformed rows.
 

Notes on Relative Paths
- Ensure `data/products.csv` exists under the project root when running.
- If the program cannot find the input file, check your current working directory.

Run Summary Output
The program prints: rows read (excluding header), transformed, skipped (malformed), and the output path.
