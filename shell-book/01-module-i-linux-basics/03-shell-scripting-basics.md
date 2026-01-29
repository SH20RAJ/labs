# Shell Scripting Deep Dive: The Basics

## 1. The Shebang & Modes

The **Shebang** (`#!`) tells the kernel which interpreter to use.

```bash
#!/bin/bash
```

### Script Execution Modes (`set`)

Change shell behavior for safer scripts.

- `set -e`: **Exit on Error**. Stop script if any command fails.
- `set -u`: **Nounset**. Error if using undefined variable.
- `set -x`: **Debug**. Print commands as they execute.
- `set -o pipefail`: Fail pipeline if _any_ command fails (not just the last).

**Recommended Header**:

```bash
#!/bin/bash
set -euo pipefail
```

## 2. Variables & Quoting Rules

### Variable Types

- **Local**: `var=value` (Only visible in current block/function).
- **Environment**: `export VAR=value` (Visible to child processes).
- **Readonly**: `readonly VAR=value` (Constant).

### Quoting Matters

| Quote Type   | Syntax      | Behavior                                     |
| :----------- | :---------- | :------------------------------------------- |
| **Double**   | `"$VAR"`    | **Expands** variables. Preserves whitespace. |
| **Single**   | `'$VAR'`    | **Literal**. No expansion. Prints `$VAR`.    |
| **Backtick** | `` `cmd` `` | Legacy command substitution. Avoid.          |

**Rule of Thumb**: ALWAYS quote your variables (`"$VAR"`) to prevent word splitting on spaces.

### Special Variables

- `$?`: Exit code of last command. (0 = Success).
- `$0`: Script name.
- `$#`: Number of arguments.
- `$$`: PID of current shell.
- `$!`: PID of last background job.
- `$@`: All arguments (individually quoted).
- `$*`: All arguments (single string).

## 3. Logic & Conditionals

### The `[[` vs `[`

Always use **double brackets** `[[ ... ]]` in Bash. It's safer and more powerful than POSIX `[ ... ]`.

### Logical Operators

- `&&` (AND): Run next command only if previous succeeded.
- `||` (OR): Run next command only if previous failed.
- `!` (NOT).

```bash
mkdir dir && cd dir || echo "Failed to create dir"
```

### Test Operators

Inside `[[ ... ]]`:

| Operator         | Type   | Description                                |
| :--------------- | :----- | :----------------------------------------- |
| `-z "$VAR"`      | String | True if string is **Empty** (Zero length). |
| `-n "$VAR"`      | String | True if string is **Not Empty**.           |
| `"$A" == "$B"`   | String | Equality.                                  |
| `-eq`, `-ne`     | Number | Equal, Not Equal.                          |
| `-lt`, `-gt`     | Number | Less Than, Greater Than.                   |
| `-f "file"`      | File   | Exists and is a regular **File**.          |
| `-d "dir"`       | File   | Exists and is a **Directory**.             |
| `-r`, `-w`, `-x` | File   | Readable, Writable, Executable.            |
| `-s "file"`      | File   | Exists and size > 0.                       |

## 4. Loops & Control Flow

### `for` Loop

```bash
# C-Style
for ((i=0; i<10; i++)); do
    echo "Count $i"
done

# Range (Bash 4)
for i in {1..10}; do ... done

# Items
for file in *.txt; do
    echo "Processing $file"
done
```

### `while` Loop

Best for reading files line-by-line.

```bash
while read -r line; do
    echo "Line: $line"
done < file.txt
```

### `case` Statement

Better than many `if-elif`.

```bash
case "$1" in
    start)
        start_server
        ;;
    stop)
        stop_server
        ;;
    *)
        echo "Usage: $0 {start|stop}"
        exit 1
        ;;
esac
```

## 5. Functions

```bash
my_func() {
    local param1=$1
    local param2=$2
    # Return specific status code
    return 0
}

# Capture output
result=$(my_func "arg1")
```
