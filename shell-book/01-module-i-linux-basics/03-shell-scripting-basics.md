# Shell Scripting Basics

## Introduction

A shell script is simply a text file containing a series of commands. When executed, the shell runs them one by one. It's powerful for automation.

### Shebang

Every script should start with a "shebang" line to tell the system which interpreter to use.

```bash
#!/bin/bash
```

### Making a Script Executable

1. Create file: `touch myscript.sh`
2. Add code.
3. Make executable: `chmod +x myscript.sh`
4. Run: `./myscript.sh`

## 1. Variables

No data types, everything is a string (mostly). No spaces around `=`.

```bash
name="John"
count=10

echo "Hello, $name"
echo "Count is ${count}"
```

## 2. Conditionals (if-else)

Bash uses `[ ]` for tests. Spaces inside brackets are CRITICAL.

```bash
#!/bin/bash

read -p "Enter your age: " age

# -ge means Greater than or Equal
if [ "$age" -ge 18 ]; then
    echo "You are an adult."
elif [ "$age" -ge 13 ]; then
    echo "You are a teenager."
else
    echo "You are a child."
fi
```

### Comparison Operators

- **Numbers**: `-eq` (equal), `-ne` (not equal), `-gt` (greater), `-lt` (less).
- **Strings**: `=` (equal), `!=` (not equal), `-z` (empty string).
- **Files**: `-f` (exists & file), `-d` (exists & directory), `-e` (exists).

## 3. Loops

### For Loop

Iterate over a list or file pattern.

```bash
# Loop through numbers
for i in {1..5}; do
    echo "Number: $i"
done

# Loop through files
for file in *.txt; do
    echo "Found text file: $file"
done
```

### While Loop

Run while a condition is true.

```bash
count=1
while [ $count -le 5 ]; do
    echo "Count: $count"
    ((count++))     # Arithmetic expansion
done
```

## 4. Functions

Reusable blocks of code.

```bash
greet() {
    local name=$1   # First argument
    echo "Welcome back, $name!"
}

# Calling the function
greet "Alice"
greet "Bob"
```

## Example: Simple Log Rotator

```bash
#!/bin/bash

LOG_FILE="app.log"
BACKUP_DIR="backups"

# Check if log file exists
if [ -f "$LOG_FILE" ]; then
    mkdir -p "$BACKUP_DIR"

    timestamp=$(date +%Y%m%d_%H%M%S)
    mv "$LOG_FILE" "$BACKUP_DIR/app_$timestamp.log"

    touch "$LOG_FILE"
    echo "Log rotated successfully at $(date)"
else
    echo "Error: $LOG_FILE not found!"
fi
```
