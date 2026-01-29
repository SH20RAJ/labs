# Unix/Linux Commands and File Operations

## Introduction

The Linux shell (usually Bash) is the primary interface for interacting with the operating system. Mastery of basic commands is essential for any power user or system administrator.

## 1. Basic Navigation and Directory Operations

### `pwd` - Print Working Directory

Shows your current location in the filesystem.

```bash
$ pwd
/home/user
```

### `cd` - Change Directory

Navigate between directories.

```bash
$ cd /etc       # Go to /etc
$ cd ..         # Go up one level
$ cd ~          # Go to home directory
$ cd -          # Go to previous directory
```

### `ls` - List Directory Contents

Lists files and folders.

```bash
$ ls            # Simple list
$ ls -l         # Long listing (permissions, size, owner)
$ ls -a         # Show hidden files (starting with .)
$ ls -lh        # Long listing with human-readable sizes (MB, GB)
```

## 2. File Operations

### `touch` - Create Empty Files

Updates timestamp or creates a new empty file.

```bash
$ touch newfiles.txt
```

### `mkdir` - Make Directory

Creates new folders.

```bash
$ mkdir myfolder
$ mkdir -p parent/child/grandchild   # Create parent directories as needed
```

### `cp` - Copy

Copies files or directories.

```bash
$ cp source.txt dest.txt
$ cp -r source_dir dest_dir     # Recursive copy for directories
```

### `mv` - Move or Rename

Moves files or renames them.

```bash
$ mv file.txt newlocation/      # Move
$ mv oldname.txt newname.txt    # Rename
```

### `rm` - Remove

Deletes files (PERMANENTLY).

```bash
$ rm file.txt
$ rm -r foldername              # Recursive remove (for directories)
$ rm -rf foldername             # Force remove (dangerous!)
```

## 3. Viewing File Content

### `cat` - Concatenate

Displays entire file content.

```bash
$ cat /etc/passwd
```

### `less` and `more`

View huge files page by page. `less` is more powerful.

```bash
$ less bigfile.log
# Press 'q' to exit
```

### `head` and `tail`

View the beginning or end of a file.

```bash
$ head -n 5 file.txt    # First 5 lines
$ tail -n 10 file.txt   # Last 10 lines
$ tail -f syslog        # Follow file as it grows (great for logs)
```

## 4. Searching

### `find`

Search for files by name, size, time, etc.

```bash
$ find . -name "*.txt"          # Find text files in current directory
$ find /var -size +100M         # Find files larger than 100MB
```

### `grep` - Global Regular Expression Print

Search for text WITHIN files.

```bash
$ grep "error" application.log
$ grep -r "TODO" .              # Recursive search in all files
```

## 5. Introduction to Pipes and Redirection

### Redirection (`>`, `>>`)

- `>` writes output to a file (overwrites).
- `>>` appends output to a file.

```bash
$ echo "Hello World" > hello.txt
$ echo "Another line" >> hello.txt
```

### Pipes (`|`)

Takes the output of one command and gives it as input to another.

```bash
$ cat access.log | grep "404" | wc -l
```

**Explanation**:

1. `cat` reads the file.
2. `grep` filters for lines with "404".
3. `wc -l` counts the number of lines found.
