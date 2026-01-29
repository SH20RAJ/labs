# 🗣️ SQL Mastery: Talking to the Data

SQL (Structured Query Language) is how we command the database.

## 🏗️ DDL vs DML vs DCL

- **DDL (Data Definition)**: Building the shelves. (`CREATE`, `DROP`, `ALTER`).
- **DML (Data Manipulation)**: Putting books on the shelves. (`INSERT`, `UPDATE`, `DELETE`).
- **DCL (Data Control)**: Deciding who enters the library. (`GRANT`, `REVOKE`).

## 🔍 The Anatomy of a Query

```sql
SELECT columns        -- projection (π)
FROM tables           -- Cartesian Product (×)
WHERE condition       -- Selection (σ)
GROUP BY column       -- organizing piles
HAVING condition      -- Filtering piles
ORDER BY column;      -- Sorting results
```

## 🤝 JOINS: The Visualization

**Mental Hook: The Venn Diagram**

1.  **INNER JOIN**: The intersection. Only matching pairs.
    - _Analogy_: "People who are BOTH Students AND Employees."
2.  **LEFT (OUTER) JOIN**: All left + matches.
    - _Analogy_: "All Students, even if they don't have a class assigned yet."

3.  **RIGHT (OUTER) JOIN**: All right + matches.
    - _Analogy_: "All Classes, even if no student has signed up."

4.  **FULL JOIN**: Everyone.
    - _Analogy_: "Everything in the universe."

## 🧩 Advanced SQL

### Aggregate Functions

`COUNT`, `SUM`, `AVG`, `MAX`, `MIN`.

- **Rule**: You cannot mix normal columns with aggregates unless you `GROUP BY`.

### Subqueries (Nested Queries)

Thinking inside the box.

```sql
SELECT name FROM Students
WHERE age > (SELECT AVG(age) FROM Students);
```

_Translation_: "Find students older than the average student."

### Triggers (The Automatic Guard)

Code that runs automatically when something happens.

- **Example**: "When a book is borrowed, automatically decrease the available stock count."
