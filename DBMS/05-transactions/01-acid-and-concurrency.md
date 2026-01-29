# 🏦 ACID & Concurrency: The Bank Vault Promise

A **Transaction** is a unit of work (a sequence of operations).
_Analogy_: Transferring $100 from Alice to Bob.

1.  Debit Alice $100.
2.  Credit Bob $100.

## 01. The ACID Properties

The "Constitution" of the database. It MUST hold true.

### A - Atomicity (All or Nothing)

**Concept**: Either the entire transaction happens, or none of it happens.

- _Scenario_: Power fails after Debiting Alice but before Crediting Bob.
- _Result_: Rollback! Alice gets her money back. No half-finished states.

### C - Consistency (Rules are Rules)

**Concept**: The DB goes from one valid state to another valid state.

- _Scenario_: You try to transfer money you don't have (resulting in negative balance).
- _Result_: Transaction rejected. Database constraints (integrity) are preserved.

### I - Isolation (Don't peek)

**Concept**: Transactions running in parallel shouldn't interfere with each other.

- _Scenario_: I am checking my balance while you are sending me money.
- _Result_: I see the balance either _before_ or _after_ your transfer, never _during_.

### D - Durability (Written in Stone)

**Concept**: Once a transaction is committed, it stays committed. Even if the building burns down.

- _Mechanism_: Write-ahead logging to non-volatile storage (Disk).

## 02. Transaction States

1.  **Active**: Running.
2.  **Partially Committed**: Last statement executed.
3.  **Committed**: Saved permanently.
4.  **Failed**: Error occurred.
5.  **Aborted**: Rolled back.
