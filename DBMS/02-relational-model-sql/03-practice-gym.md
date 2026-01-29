# 🏋️ Practice Gym: Module II

SQL is a muscle. You have to train it.

## 🥋 White Belt: Warmups

1.  **The Selector**: Write a query to select the `Name` and `City` of all customers from the `Customers` table.
2.  **The Filter**: Select all `Orders` where the `Amount` is greater than 500.
3.  **The Sorter**: List all `Employees` sorted by `Salary` (highest first).

## 🥋 Blue Belt: Joins

**Scenario**:

- `Students` table: `id`, `name`.
- `Enrollments` table: `student_id`, `course_name`.

1.  **Question**: specific a query to list **Students who have NOT enrolled in any course**.
    - _Hint_: This requires a LEFT JOIN and checking for NULL.

## 🥋 Black Belt: Aggregation & Grouping

**Scenario**: `Orders` table: `order_id`, `customer_id`, `amount`, `date`.

1.  **Question**: Find the `customer_id` of customers who have spent more than $1000 in total.
    - _Steps_: `SUM(amount)`, `GROUP BY customer_id`, `HAVING SUM > 1000`.

## 🧠 Mental Model Check

- When you see `GROUP BY`, imagine sorting a deck of cards into piles by suit (Hearts, Spades...).
- `WHERE` filters individual cards.
- `HAVING` filters the entire pile.
