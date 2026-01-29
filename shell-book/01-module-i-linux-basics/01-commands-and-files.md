# Unix/Linux Commands and File Operations: The Encyclopedia

## Introduction

This module is a comprehensive reference for the most essential Linux commands. We cover not just usage, but the critical **flags and options** that distinguish a power user from a beginner.

---

## 1. Directory Navigation & Management

### `ls` - List Directory Contents

**Usage**: `ls [OPTIONS] [FILE]`

| Option | Description                                                           | Example       |
| :----- | :-------------------------------------------------------------------- | :------------ |
| `-l`   | **Long listing format**. Shows permissions, owner, size, mod time.    | `ls -l`       |
| `-a`   | **All**. Shows hidden files (starting with `.`).                      | `ls -la`      |
| `-h`   | **Human-readable**. Prints sizes in KB, MB, GB (works with `-l`).     | `ls -lh`      |
| `-R`   | **Recursive**. Lists subdirectories recursively.                      | `ls -R`       |
| `-t`   | **Sort by Time**. Modification time, newest first.                    | `ls -lt`      |
| `-r`   | **Reverse**. Reverses the sort order (e.g., Oldest first with `-tr`). | `ls -ltr`     |
| `-S`   | **Sort by Size**. Largest files first.                                | `ls -lS`      |
| `-d`   | **Directory**. List directory entries instead of contents.            | `ls -ld /etc` |
| `-i`   | **Inode**. Print the index number of each file.                       | `ls -i`       |

### `cd` - Change Directory

**Usage**: `cd [DIRECTORY]`

- `cd` : Go to **Home** directory (same as `cd ~`).
- `cd -` : Go to **Previous** directory (swaps back and forth).
- `cd ..` : Go **Up** one level.
- `cd ../..` : Go **Up** two levels.
- `cd /` : Go to **Root**.

### `mkdir` - Make Directory

**Usage**: `mkdir [OPTIONS] DIRECTORY...`

| Option | Description                                                         |
| :----- | :------------------------------------------------------------------ |
| `-p`   | **Parents**. No error if exists, make parent directories as needed. |
| `-v`   | **Verbose**. Print a message for each created directory.            |
| `-m`   | **Mode**. Set file permissions (like chmod) at creation.            |

**Example**:

```bash
mkdir -p projects/java/src  # Creates 'projects', 'java', and 'src' if missing
mkdir -m 700 private_folder # Read/Write/Exec for user only
```

---

## 2. File Manipulation

### `cp` - Copy

**Usage**: `cp [OPTIONS] SOURCE DEST`

| Option     | Description                                                                 |
| :--------- | :-------------------------------------------------------------------------- |
| `-r`, `-R` | **Recursive**. Copy directories and their contents.                         |
| `-i`       | **Interactive**. Prompt before overwrite.                                   |
| `-u`       | **Update**. Copy only when the SOURCE file is newer than the destination.   |
| `-v`       | **Verbose**. Explain what is being done.                                    |
| `-p`       | **Preserve**. Preserve mode, ownership, and timestamps.                     |
| `-a`       | **Archive**. Preserves everything (recursive + preserve). Best for backups. |

**Pro Tip**:

```bash
cp -a /var/www/html /backup/html_snap  # Exact backup
```

### `mv` - Move (Rename)

**Usage**: `mv [OPTIONS] SOURCE DEST`

| Option | Description                                        |
| :----- | :------------------------------------------------- |
| `-i`   | **Interactive**. Prompt before overwrite.          |
| `-u`   | **Update**. Move only if SOURCE is newer.          |
| `-n`   | **No Clobber**. Do not overwrite an existing file. |

### `rm` - Remove

**Usage**: `rm [OPTIONS] FILE...`

| Option     | Description                                                      |
| :--------- | :--------------------------------------------------------------- |
| `-r`, `-R` | **Recursive**. Remove directories and their contents.            |
| `-f`       | **Force**. Ignore nonexistent files and arguments, never prompt. |
| `-i`       | **Interactive**. Prompt before every removal.                    |
| `-v`       | **Verbose**. Explain what is being done.                         |

**DANGER**: `rm -rf /` will delete your entire OS. Always check your path.

### `touch` - Change File Timestamps

**Usage**: `touch [OPTIONS] FILE`

| Option | Description                                |
| :----- | :----------------------------------------- |
| `-a`   | Change only access time.                   |
| `-m`   | Change only modification time.             |
| `-t`   | Use specific time `[[CC]YY]MMDDhhmm[.ss]`. |
| `-c`   | **No Create**. Do not create any files.    |

---

## 3. Power Searing & Filtering

### `find` - Search Directory Hierarchy

**Usage**: `find [PATH] [EXPRESSION]`

| Test             | Description                                            |
| :--------------- | :----------------------------------------------------- |
| `-name PATTERN`  | Base of file name (e.g., `*.txt`). Case sensitive.     |
| `-iname PATTERN` | Case insensitive name search.                          |
| `-type c`        | File type: `f` (file), `d` (directory), `l` (symlink). |
| `-size N`        | File size: `+10M` (>10MB), `-1k` (<1KB).               |
| `-mtime N`       | Modified N\*24 hours ago. `+7` (>7 days), `-1` (<24h). |
| `-perm MODE`     | File permissions match MODE (e.g., `644`).             |
| `-user NAME`     | File belongs to user NAME.                             |

| Action            | Description                                                 |
| :---------------- | :---------------------------------------------------------- |
| `-delete`         | Delete files (Use with caution!).                           |
| `-exec CMD {} \;` | Execute CMD on each file. `{}` is placeholder for filename. |
| `-print`          | Print filename (Default action).                            |

**Complex Examples**:

```bash
# Find all .log files > 100MB and delete them
find /var/log -name "*.log" -size +100M -delete

# Find files modified in last 24h and copy them
find . -type f -mtime -1 -exec cp {} /backup/daily/ \;

# Find all empty directories
find . -type d -empty
```

### `grep` - Print Lines Matching a Pattern

**Usage**: `grep [OPTIONS] PATTERN [FILE...]`

| Option     | Description                                                           |
| :--------- | :-------------------------------------------------------------------- | ------------- |
| `-i`       | **Ignore Case**.                                                      |
| `-v`       | **Invert Match**. Select non-matching lines.                          |
| `-c`       | **Count**. Print only a count of matching lines.                      |
| `-l`       | **Files with Matches**. Print only names of FILEs containing matches. |
| `-n`       | **Line Number**. Print line number with output lines.                 |
| `-r`, `-R` | **Recursive**. Read all files under each directory.                   |
| `-E`       | **Extended Regex**. Use full regex power (`                           | `, `+`, `?`). |
| `-A N`     | **After**. Print N lines of trailing context.                         |
| `-B N`     | **Before**. Print N lines of leading context.                         |
| `-C N`     | **Context**. Print N lines of output context.                         |

**Real World Examples**:

```bash
# Search for 'error' in all files recursively, ignoring case, with line numbers
grep -rni "error" /var/log/

# Find lines that do NOT contain "success"
grep -v "success" access.log

# Find IP addresses (Regex)
grep -Eo "[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}" access.log
```
