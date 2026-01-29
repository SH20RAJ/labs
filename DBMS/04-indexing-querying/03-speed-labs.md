# 🏎️ Speed Labs: Module IV

## Lab 1: index vs Linear Scan

**Scenario**: You have 1 Million Users. You want `SELECT * FROM Users WHERE id = 999999`.

1.  **Without Index**: The DB reads row 1, row 2... row 999,999.
    - _Cost_: 1,000,000 operations.
2.  **With B+ Tree Index**:
    - Root -> Level 1 -> Level 2 -> Leaf.
    - _Cost_: ~4 operations.
    - **Difference**: Massive.

## Lab 2: The Hash Collision

**Scenario**: You are using Hash Indexing.

- Hash Function: `ID % 10`.
- Buckets: 0 to 9.

**Data**:

- User 10 (Bucket 0)
- User 20 (Bucket 0)
- User 30 (Bucket 0)

**Problem**: Bucket 0 is overflowing. This is a **Collision**.
**Question**: What happens to performance when collisions increase?

- _Answer_: It degrades from O(1) to O(N) (Linear scan inside the bucket).

## Lab 3: The Query Plan

**Visualizing**: Write `EXPLAIN` before your SQL query.

```sql
EXPLAIN SELECT * FROM Students WHERE age > 20;
```

- Look for "Full Table Scan" vs "Index Range Scan".
- "Full Table Scan" is the enemy of performance.
