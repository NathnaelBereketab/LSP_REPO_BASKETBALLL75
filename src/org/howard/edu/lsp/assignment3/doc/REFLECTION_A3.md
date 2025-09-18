CSCI 363 – Assignment 3: Object-Oriented Redesign Reflection

Overview
This reflection compares the Assignment 2 and Assignment 3 implementations of the ETL pipeline and how Assignment 3 demonstrates object-oriented principles.

What is Different About the Design?

Assignment 2 (Original Implementation):
  - Single `ETLPipeline` class with static methods
  - Inner classes: `Product` and `ExtractStats` 
  - All logic contained within static methods: `extract()`, `transform()`, `load()`, `printSummary()`
  - Everything in one file

The original implementation followed a approach where all the implentation was within a single class using static methods. This design worked but it didn't use object-oriented principles. The `Product` and `ExtractStats` classes were defined as inner classes, which limited the functions reusability and made the code harder to test/maintain. All ETL operations were implemented as static methods, which meant there was no object interaction.

Assignment 3 (Object-Oriented Redesign):
  - Four separate classes with distinct responsibilities
  - `BaseProduct` abstract class providing common product functionality
  - `Product` class extending `BaseProduct` with proper encapsulation and polymorphism
  - `ETLProcessor` handling all ETL operations (extract, transform, load)
  - `ETLPipeline` as orchestrator using composition
  - Clear separation of concerns across multiple files with inheritance hierarchy

The redesigned implementation follows proper object-oriented principles with a clear class hierarchy and separation of concerns. The `BaseProduct` abstract class establishes a foundation for product types with common functionality, while `Product` extends it to demonstrate inheritance and implements abstract methods for polymorphism. The `ETLProcessor` class encapsulates all data processing logic, and `ETLPipeline` acts as the main orchestrator using composition. This design improves maintainability, testability, and follows the single responsibility principle.

How is Assignment 3 More Object-Oriented?

Encapsulation:
- `Product` class uses private fields with public getter/setter methods
- Implementation details are hidden behind clean interfaces
- Each class manages its own data and behavior

The encapsulation in Assignment 3 is significantly improved compared to Assignment 2. In the original design, data was passed around as parameters between static methods, with no data hiding or controlled access. The new design uses private fields in both `BaseProduct` and `Product` classes, with protected setters in the base class and public getters for controlled access. This ensures that data integrity is maintained and implementation details are properly hidden from external classes.

Object-Oriented Decomposition:
- Single responsibility principle: each class has one clear purpose
- `BaseProduct` provides common product functionality and abstract methods
- `Product` manages product data, formatting, and implements abstract methods
- `ETLProcessor` handles all data processing operations
- `ETLPipeline` coordinates the overall workflow

The decomposition in Assignment 3 follows the single responsibility principle, where each class has one clear purpose. This is a major improvement over Assignment 2, where the single `ETLPipeline` class handled everything. The new design separates concerns: `BaseProduct` establishes the product foundation, `Product` handles specific product behavior, `ETLProcessor` manages data operations, and `ETLPipeline` orchestrates the workflow. This makes the code more maintainable, testable, and easier to extend with new functionality.

Inheritance:
- `Product` class extends `BaseProduct` abstract class
- Common functionality is inherited from the base class
- Abstract methods enforce implementation in subclasses

Assignment 3 introduces inheritance through the `BaseProduct` abstract class, which was completely absent in Assignment 2. The `Product` class extends `BaseProduct` and inherits common functionality like getters for product attributes. The abstract class defines an abstract method `computePriceRange()` that must be implemented by subclasses, ensuring consistent behavior while allowing for specific implementations. This demonstrates the "is-a" relationship and code reuse principles of object-oriented design.

Polymorphism:
- Abstract method `computePriceRange()` demonstrates polymorphism
- Same method call can have different implementations
- `@Override` annotation shows polymorphic behavior

Polymorphism is actively demonstrated in Assignment 3 through the abstract `computePriceRange()` method in `BaseProduct` and its implementation in `Product`. The ETL pipeline calls `product.computePriceRange()` during transformation, which uses the polymorphic behavior to determine the price range. This is a significant improvement over Assignment 2, which used a static utility method `determinePriceRange()`. The new approach allows for different product types to have different price range calculations while maintaining the same interface, demonstrating the "one interface, multiple implementations" principle.

Composition:
- `ETLPipeline` composes an `ETLProcessor` instance
- Classes work together through well-defined interfaces
- Loose coupling between components

Composition is demonstrated through the relationship between `ETLPipeline` and `ETLProcessor`. The `ETLPipeline` class creates and uses an `ETLProcessor` instance to handle the actual ETL operations, following the "has-a" relationship principle. This is a major improvement over Assignment 2, where all operations were static methods within a single class. The new design allows for better separation of concerns, easier testing (the processor can be mocked), and more flexible architecture where different processor implementations could be used without changing the pipeline logic.

Which OO Ideas Did You Use?

Object: Each `Product` instance represents a real-world product with state and behavior
Class: Four distinct classes (`BaseProduct`, `Product`, `ETLProcessor`, `ETLPipeline`) with clear responsibilities
Encapsulation: Private fields in `Product` class with controlled access through public methods
Inheritance: `Product` class extends `BaseProduct` abstract class, demonstrating inheritance hierarchy
Polymorphism: Abstract method `computePriceRange()` in `BaseProduct` is implemented in `Product` with `@Override`, demonstrating polymorphism through method overriding

How I Tested to Confirm Assignment 3 Works the Same as Assignment 2

Testing Strategy:
- Used identical input file (`data/products.csv`) for both implementations
- Compared output files byte-for-byte to ensure identical results
- Verified all transformation rules work correctly:
  - Names converted to uppercase (Book → BOOK)
  - Electronics discount applied (999.99 → 899.99 for Laptop)
  - Recategorization works (Electronics → Premium Electronics for high-value items)
  - Price ranges computed correctly (Low/Medium/High/Premium)
- Tested edge cases: empty file, missing file, malformed data
- Confirmed identical error handling and summary output

Verification Results:
- Output file `data/transformed_products.csv` is identical between A2 and A3
- Same transformation order and logic preserved
- Same error messages and summary format
- Same relative path handling and file structure

Design Benefits of Assignment 3

Maintainability:
- Each class can be modified independently
- Clear separation makes debugging easier
- Code is more readable and self-documenting

Extensibility:
- Easy to add new transformation rules to `ETLProcessor`
- `Product` class can be extended with new fields/methods
- Pipeline can be modified without changing core logic

Testability:
- Each class can be unit tested independently
- Dependencies can be mocked for isolated testing
- Clear interfaces make testing straightforward

Conclusion

Assignment 3 successfully demonstrates all object-oriented principles (encapsulation, inheritance, polymorphism, composition) while maintaining identical functionality to Assignment 2. The redesign improves code organization, maintainability, and extensibility without changing the core business logic or output format. The 4-class design with inheritance hierarchy effectively separates concerns while demonstrating proper OO design patterns and keeping the implementation straightforward and easy to understand.
