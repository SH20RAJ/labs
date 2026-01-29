# 🕵️ Query Processing & Optimization

When you write SQL, you tell the database **WHAT** you want, not **HOW** to get it. The **Query Optimizer** is the brain that figures out the "How".

## 01. The Life Cycle of a Query

1.  **Parsing**: Syntax check. ("Did you spell SELECT correctly?").
2.  **Translation**: Convert SQL to Relational Algebra.
3.  **Optimization**: Pick the cheapest plan.
    - _Should I use the Index or scan the whole table?_
    - _Should I join A to B, or B to A?_
4.  **Execution**: Run the plan.

## 02. Measures of Query Cost

Cost is usually measured in **Disk I/O** (How many blocks do we need to read from the hard drive?).

- CPU cost is usually negligible compared to Disk speed.

## 03. Evaluation of Expressions

### Materialization (The Temporary Storage)

Calculate one step, save result to a temporary table on disk, then use it for the next step.

- _Slow_. Lots of writing to disk.

### Pipelining (The Assembly Line)

Pass the result of one operation _immediately_ to the next operation in RAM.

- _Fast_. No temporary files.

## 🧠 Brain Hook: The GPS

- **SQL**: "Drive me to the Airport."
- **Optimizer**: "Checking routes... Route A is 10 miles but has traffic. Route B is 15 miles but clear. Choosing Route B."
