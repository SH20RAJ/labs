# 🧮 Relational Algebra: The Math Behind SQL

Before we speak SQL, we must understand the logic. Relational Algebra is a **procedural query language**. It tells the database _how_ to get the data.

## 🔑 The Basic Operations

### 1. Selection (σ - Sigma)

**Filter rows.**

- _Analogy_: "Show me only the students who wear glasses."
- **Notation**: $\sigma_{age > 18}(Students)$
- **SQL Equivalence**: `WHERE` clause.

### 2. Projection (π - Pi)

**Filter columns.**

- _Analogy_: "I only want to see your Names, not your Phone Numbers."
- **Notation**: $\pi_{name}(Students)$
- **SQL Equivalence**: `SELECT name` clause.

### 3. Cartesian Product (×)

**Combine everything.**

- _Analogy_: "Every student meets every teacher."
- If you have 10 Students and 5 Teachers, you get 50 combinations.
- **Warning**: Usually creates a huge mess unless filtered!

### 4. Join (⋈)

**Intelligent Combination.**

- _Analogy_: "Connect Student to Teacher only if the Student is in that Teacher's class."
- It is basically Product (×) + Selection (σ).

## 🧠 Relational Algebra visualized

Imagine two tables:
**A (Eaters)**: `[Alice, Bob]`
**B (Foods)**: `[Pizza, Burger]`

- **A × B**:
  1.  Alice - Pizza
  2.  Alice - Burger
  3.  Bob - Pizza
  4.  Bob - Burger

- **σ (Selection)** where Eaters="Alice":
  1.  Alice - Pizza
  2.  Alice - Burger
