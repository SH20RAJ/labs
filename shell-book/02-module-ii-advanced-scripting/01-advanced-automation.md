# Advanced Shell Scripting and Automation

## 1. Command Line Arguments

Scripts often need dynamic input. Arguments passed to a script are accessible via `$1`, `$2`, etc.

- `$0`: Name of the script.
- `$1 - $9`: The first nine arguments.
- `$#`: Total number of arguments.
- `$@` or `$*`: All arguments as a list.
- `$?`: Exit status of the last executed command (0 = success, non-zero = failure).

### Example: User Management Script

```bash
#!/bin/bash

USERNAME=$1
GROUP=$2

if [ -z "$USERNAME" ] || [ -z "$GROUP" ]; then
    echo "Usage: $0 <username> <group>"
    exit 1
fi

# Check if group exists, if not create it
if ! getent group "$GROUP" > /dev/null; then
    echo "Creating group $GROUP..."
    sudo groupadd "$GROUP"
fi

# Create user and add to group
echo "Creating user $USERNAME..."
sudo useradd -m -g "$GROUP" "$USERNAME"
echo "User $USERNAME created successfully."
```

## 2. Shell Expansions

### Brace Expansion

Generate arbitrary strings.

```bash
echo {A,B,C}{1..3}
# Output: A1 A2 A3 B1 B2 B3 C1 C2 C3

mkdir -p project/{src,bin,docs,test}
```

### Command Substitution

Use the output of a command inside another command.

```bash
# Old style (backticks)
TODAY=`date +%F`

# New style (recommended)
TODAY=$(date +%F)
echo "Today is $TODAY"
```

## 3. Advanced I/O Redirection

- **Standard Input (stdin)**: File Descriptor 0
- **Standard Output (stdout)**: File Descriptor 1
- **Standard Error (stderr)**: File Descriptor 2

### Redirecting Error

```bash
# Redirect stdout to file, stderr to terminal (default)
ls folder > list.txt

# Redirect stderr to file
ls non_existent_folder 2> error.log

# Redirect stdout AND stderr to same file
ls folder > all_output.log 2>&1
```

### Here Documents (Heredoc)

Pass multi-line input to a command.

```bash
cat <<EOF > config.txt
ServerName localhost
Port 8080
DocumentRoot /var/www/html
EOF
```

## 4. Arrays

Bash supports one-dimensional arrays.

```bash
servers=("web01" "db01" "cache01")

echo "First server: ${servers[0]}"
echo "All servers: ${servers[@]}"

for s in "${servers[@]}"; do
    ping -c 1 "$s"
done
```
