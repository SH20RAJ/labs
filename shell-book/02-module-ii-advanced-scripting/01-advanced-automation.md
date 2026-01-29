# Advanced Shell Features: Mastery

## 1. Parameter Expansion

Bash allows powerful string manipulation without external tools like `sed`.

| Syntax              | Description                                           | Example ($file="image.jpg")                   |
| :------------------ | :---------------------------------------------------- | :-------------------------------------------- |
| `${var:-default}`   | **Default Value**. Use default if var is unset/empty. | `${NAME:-User}` -> "User"                     |
| `${var:=default}`   | **Assign Default**. Set var to default if unset.      | `${NAME:=User}`                               |
| `${var:?error}`     | **Error if Unset**. Print message and exit.           | `${NAME:?Name is required}`                   |
| `${#var}`           | **Length**. Length of the string.                     | `${#file}` -> 9                               |
| `${var:offset:len}` | **Substring**. Slice string.                          | `${file:0:5}` -> "image"                      |
| `${var#pattern}`    | **Remove Prefix** (Shortest).                         | `${file#*.} ` -> "jpg"                        |
| `${var%pattern}`    | **Remove Suffix** (Shortest).                         | `${file%.*}` -> "image"                       |
| `${var/old/new}`    | **Replace** (First occurrence).                       | `${file/image/photo}` -> "photo.jpg"          |
| `${var//old/new}`   | **Replace All**.                                      | `${path//:/ }` -> Replace colons with spaces. |

**Example: File Extension Converter**

```bash
for file in *.jpeg; do
    mv "$file" "${file%.jpeg}.jpg"
done
```

## 2. Arrays

### Indexed Arrays

Standard lists.

```bash
fruits=("Apple" "Banana" "Cherry")

echo "${fruits[0]}"     # Apple
echo "${fruits[@]}"     # All items
echo "${#fruits[@]}"    # Count: 3

# Loop
for f in "${fruits[@]}"; do
    echo "I like $f"
done
```

### Associative Arrays (Map/Dict)

Requires Bash 4.0+. Store key-value pairs.

```bash
declare -A user_info
user_info[name]="Alice"
user_info[role]="Admin"

echo "${user_info[role]}" # Admin

# Loop Keys
for key in "${!user_info[@]}"; do
    echo "$key: ${user_info[$key]}"
done
```

## 3. Handling Flags (`getopts`)

Professional scripts accept flags like `-v` or `-f file`.

```bash
#!/bin/bash

VERBOSE=false
FILENAME=""

while getopts "vf:" opt; do
    case $opt in
        v) VERBOSE=true ;;
    	f) FILENAME=$OPTARG ;;
    	*) echo "Usage: $0 [-v] [-f filename]"; exit 1 ;;
    esac
done

if $VERBOSE; then echo "Processing $FILENAME..."; fi
```

## 4. Debugging & Traps

### Traps

Run code when script exits or gets a signal.

```bash
cleanup() {
    echo "Cleaning up temp files..."
    rm -f /tmp/myscript.*
}

# Run cleanup on EXIT (normal or error) and SIGINT (Ctrl+C)
trap cleanup EXIT SIGINT
```

### Debugging Modes

- `bash -x script.sh`: Run with debug print.
- `set -x` / `set +x`: Enable/Disable debug for a specific block.
- `PS4`: Customize the debug prompt. `export PS4='+${BASH_SOURCE}:${LINENO}: '` shows file and line number.
