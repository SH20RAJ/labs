# 🏥 Design Clinic: Module III

You are the Doctor. Diagnose the table mess.

## Case 1: The Music Store

**Table**: `Album_Sales`
`Columns`: `[Sale_ID, Album_Name, Artist, Genre, Customer_Name, Customer_Email]`

**Diagnosis**:

1.  **Redundancy**: Every time we sell an album by "Pink Floyd", we repeat "Rock" (Genre).
2.  **Anomaly**: If "Pink Floyd" changes their genre style, we have to update thousands of sales records.
3.  **Violation**:
    - `Album_Name` -> `Artist`
    - `Album_Name` -> `Genre`
    - `Customer_Name` -> `Customer_Email`
    - These are Transitive Dependencies!

**Treatment (Normalization)**:
Split into 3 tables:

1.  **Albums**: `[Album_ID, Name, Artist, Genre]`
2.  **Customers**: `[Customer_ID, Name, Email]`
3.  **Sales**: `[Sale_ID, Album_ID, Customer_ID]`

## Case 2: The Project Manager

**Table**: `Employee_Projects`
`Columns`: `[Emp_ID, Emp_Name, Project_ID, Hours_Worked]`
`Primary Key`: `(Emp_ID, Project_ID)`

**Diagnosis**:

- `Emp_Name` depends only on `Emp_ID` (Part of the key).
- This violates **2NF** (Partial Dependency).

**Treatment**:

1.  **Employees**: `[Emp_ID, Emp_Name]`
2.  **Works_On**: `[Emp_ID, Project_ID, Hours_Worked]`

## 🧠 Active Recall

Close your eyes.

- What is the "Virus" of database design? (Anomalies).
- What does 1NF forbid? (Lists in cells).
- What does 3NF forbid? (Columns relying on other columns).
