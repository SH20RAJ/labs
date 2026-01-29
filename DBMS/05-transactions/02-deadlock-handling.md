# 🚦 Deadlocks & Protocols: The Traffic Jam

## 01. The Deadlock (Stalemate)

**Scenario**:

- User A holds `Lock(Resource 1)` and wants `Resource 2`.
- User B holds `Lock(Resource 2)` and wants `Resource 1`.
- **Result**: Neither can move. Forever.

### 🛑 Handling Deadlocks

1.  **Prevention**: Limit how specific processes request requests so deadlock is impossible. (e.g., "Must request all locks at once").
2.  **Avoidance (Banker's Algorithm)**: Before granting a lock, the OS simulates if it _could_ lead to an unsafe state. If yes, it says "Wait".
3.  **Detection & Recovery**: Let it happen. Then shoot one process (Rollback) to break the cycle.

## 02. Locking Protocols (The Bathroom Key)

### Shared Lock (S) - "Read Only"

- Many people can hold a Shared Key.
- Anyone can **Enter and Look**, but **No one can touch**.

### Exclusive Lock (X) - "Read/Write"

- Only ONE person can hold this.
- When I have it, **No one else can enter**, not even to look.

### Two-Phase Locking (2PL)

Standard protocol to ensure Serializability.

1.  **Growing Phase**: Transaction acquires all needed locks. (Cannot release any).
2.  **Shrinking Phase**: Transaction releases locks. (Cannot acquire any new ones).

_Analogy_: You gather all ingredients (Growing) before you start cooking. Once you put the flour back in the pantry (Shrinking), you can't grab the sugar.
