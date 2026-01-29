# 🧹 Normalization: The Organizing Consultant

Normalization is the process of breaking big, messy tables into smaller, clean tables.

## 01. First Normal Form (1NF): "No Repeating Groups"

**Rule**: Every cell must contain only **ONE** value. (Atomic).
_Analogy_: Imagine an Excel cell with a comma-separated list of phone numbers "555-1234, 555-9876".

- **Violation**: You can't search or sort by phone number easily.
- **Fix**: Create a new row for each phone number.

## 02. Second Normal Form (2NF): "The Whole Key, and Nothing But the Key"

**Rule**: Must be in 1NF **AND** no Partial Dependency.

- **Concept**: If your Primary Key is `(Student_ID, Course_ID)`, then every storage column (like `Professor_Name`) must depend on BOTH.
- **Violation**: `Professor_Name` depends only on `Course_ID`, not on `Student_ID`.
- **Fix**: Move `Professor` info to a separate `Course` table.

## 03. Third Normal Form (3NF): "No Transitive Dependency"

**Rule**: Must be in 2NF **AND** no transitive dependency (`A -> B -> C`).

- **Concept**: Non-key columns shouldn't determine other non-key columns.
- **Violation**: `Student` table has `Zip_Code` and `City`.
  - `Student_ID` -> `Zip_Code` (Good).
  - `Zip_Code` -> `City` (Bad! This is a hidden relationship).
  - If many students live in the same Zip, we repeat "New York" 1000 times.
- **Fix**: Create a `ZipCodes` table.

## 04. BCNF (Boyce-Codd Normal Form): "The Stricter Boss"

**Rule**: For every dependency `X -> Y`, `X` must be a Super Key.

- It handles rare cases where 3NF isn't enough (usually with multiple overlapping candidate keys).

---

## 🧠 The Mnemonic: "1-2-3-B"

1.  **1NF**: **One** value per cell.
2.  **2NF**: **Two** parts of the key? Use the whole key.
3.  **3NF**: **Three**'s a crowd (A -> B -> C). Don't let columns depend on other columns.
4.  **BCNF**: **Boss** Mode (Keys must be super).
