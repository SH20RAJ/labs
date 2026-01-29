# 🏎️ Indexing: The Fast Lane

Imagine a 1000-page book. You need to find the definition of "Polymorphism".

- **Option A**: Read page 1, then page 2, ... until page 865. (Full Table Scan).
- **Option B**: Go to the Index at the back, find "Polymorphism: Page 865", jump there. (Index Scan).

**An Index is a sorted copy of selected columns.**

## 01. Ordered Indices & B+ Trees

Most databases use **B+ Trees** (Balanced Trees).

### 🌳 The B+ Tree Visualization

Think of it as a "Decision Map".

- **Root Node**: "Values < 50 go Left. Values > 50 go Right."
- **Leaf Node**: Holds the actual pointer to the data row.

**Why B+ Trees?**

1.  **Balanced**: Every path from Root to Leaf is the same length. Predictable speed.
2.  **Sorted**: Good for range queries (`WHERE age BETWEEN 20 AND 30`).

## 02. Hashing (The Locker Room)

Used for **Equality** searches (`WHERE id = 101`).

- _Analogy_: You are assigned Locker #101.
- **Hash Function**: Takes your ID, calculates a math formula, and tells you exactly which bucket (locker) your data is in.
- **Pros**: O(1) Speed. Instant.
- **Cons**: Terrible for ranges (`> 100`).

## 03. Static vs Dynamic Hashing

- **Static**: Fixed number of lockers. If full, we have a problem (collisions/overflow).
- **Dynamic**: The locker room expands automatically when full.
