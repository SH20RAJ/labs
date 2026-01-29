# 🧠 Brain Teasers: Module I

Test your understanding. Solutions are at the bottom (don't peek!).

## Puzzle 1: The Startup

You are building a database for "Uber for Dogs".

- Users have Names and Phone Numbers.
- Dogs have Breeds and Ages.
- A User can own many Dogs.
- A Dog belongs to exactly one User.

**Question**: Draw the ER Diagram mentally. Is the relationship 1:1, 1:N, or M:N?

## Puzzle 2: The Weak Link

In a University database, we store `Room` numbers (101, 102).
**Question**: If we delete the `Building` entity (e.g., "Science Block"), do the `Rooms` inside it still exist?

- Yes?
- No?
- Why is `Room` a Weak Entity?

## Puzzle 3: The Attribute Trap

You need to store a User's address: "123 Main St, New York, NY".
**Question**: Should `Address` be a single circle (Attribute) or should it be split into `Street`, `City`, `State`?
_(Hint: Think about searching for "Everyone in NY")._

---

## 💡 Solutions

**Puzzle 1**:

- **Relationship**: 1:N (One-to-Many).
- One User ↔️ Many Dogs.

**Puzzle 2**:

- **Answer**: No.
- If the Building is gone, the Rooms physically disappear. `Room` depends on `Building` for existence.

**Puzzle 3**:

- **Answer**: Split it! This is a **Composite Attribute**.
- Storing it as one string makes querying "New York" residents very slow and hard (you'd need complex string matching).
