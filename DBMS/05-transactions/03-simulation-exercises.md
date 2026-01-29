# 🎮 Simulation Exercises: Module V

## Simulation 1: The Lost Update

**Scenario**:

- Account A has $1000.
- **T1 (You)**: Read A ($1000). Withdraw $100. Write A ($900).
- **T2 (Spouse)**: Read A ($1000). Deposit $500. Write A ($1500).

**If they run at the exact same time (Interleaved):**

1.  T1 Reads ($1000).
2.  T2 Reads ($1000).
3.  T1 Writes ($900).
4.  T2 Writes ($1500).

**Final Balance**: $1500.
**Actual Balance Should Be**: $1400.
**What happened?** T1's update was "Lost" (overwritten by T2).
**Fix**: Exclusive Locks!

## Simulation 2: The Deadlock Game

**Setup**: Two pieces of paper (Resource X, Resource Y). Two players.
**Rules**:

1.  Player 1 grabs X.
2.  Player 2 grabs Y.
3.  Player 1 says "Give me Y". (Blocked).
4.  Player 2 says "Give me X". (Blocked).
    **Resolution**: One player must "Rollback" (put their paper down).

## 🧠 Active Recall

- What does "D" in ACID stand for? (Durability).
- If I have a Shared Lock, can you get an Exclusive Lock? (No).
- If I have a Shared Lock, can you get a Shared Lock? (Yes).
