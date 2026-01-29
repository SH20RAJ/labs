# 🧹 Anomalies & Keys: The Mess and the Labels

## 01. Database Anomalies (The Redundant Data Virus)

Why do we need good design? Because bad design behaves like a virus.

**Scenario**: You have one big table `Student_Course_Prof`.
`[Student: Alice, Course: Math, Prof: Dr. X, Prof_Phone: 555-0199]`
`[Student: Bob,   Course: Math, Prof: Dr. X, Prof_Phone: 555-0199]`

### 1. Update Anomaly (The Sync Issue)

**Dr. X changes his phone number.**

- You have to find _every single row_ where Dr. X appears and update it.
- If you miss one, Alice sees the new number, but Bob sees the old one. **Inconsistency!**

### 2. Insertion Anomaly (The Ghost Data)

**We hire Dr. Y, but he isn't teaching yet.**

- We cannot add Dr. Y to the table because the Primary Key includes `Student_ID`. No student = No record.
- Dr. Y sits in the hallway, undocumented.

### 3. Deletion Anomaly (The Black Hole)

**Alice leaves the college. She was the only student in Dr. Z's class.**

- If we delete Alice's row, **we accidentally delete Dr. Z's existence too**.
- Dr. Z vanishes from the database just because a student dropped out.

---

## 02. Functional Dependencies (FD)

**The "Because of this, then that" Rule.**

- **Notation**: `A -> B` (A determines B).
- _Analogy_: `Social_Security_Number -> Name`.
  - If I know your SSN, I uniquely know your Name.
  - (If I know your Name, I do NOT know your SSN. Lots of "John Smiths").

---

## 03. Keys (The Identity Cards)

### Super Key

Any set of columns that uniquely identifies a row.

- `(ID, Name, Phone)` is a Super Key.
- `(ID, Name)` is a Super Key.
- `(ID)` is a Super Key.

### Candidate Key (The Minimal Super Key)

The smallest possible Super Key. No extra baggage.

- `(ID)` is a Candidate Key.
- `(SSN)` is a Candidate Key.

### Primary Key (PK) (The Chosen One)

The Candidate Key the database admin chose to be the main identifier.

- **Rule**: Cannot be NULL.

### Foreign Key (FK) (The Link)

A primary key from another table found in this table.

- _Analogy_: Your Passport Number written on a Visa application. The Visa isn't the Passport, but it _refers_ to it.
