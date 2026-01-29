# System Info, Permissions, and Processes

## 1. File Permissions and Ownership

Linux follows a strict permission model. Every file has an owner, a group, and a set of permissions for three categories:

1. **User (u)**: The owner of the file.
2. **Group (g)**: Users in the file's group.
3. **Others (o)**: Everyone else.

### Understanding `ls -l` output

```bash
-rwxr-xr-- 1 user group 4096 Jan 1 12:00 script.sh
```

The first column `-rwxr-xr--` tells the story:

- **Type**: First `-` means file, `d` means directory.
- **User**: `rwx` (Read, Write, Execute).
- **Group**: `r-x` (Read, Execute, NO Write).
- **Other**: `r--` (Read only).

### `chmod` - Change Mode

Modify permissions using symbolic or numeric mode.

**Numeric Mode (Octal)**:

- Read = 4
- Write = 2
- Execute = 1

Examples:

```bash
$ chmod 755 script.sh   # u=rwx (7), g=rx (5), o=rx (5)
$ chmod 644 data.txt    # u=rw (6), g=r (4), o=r (4)
$ chmod 777 public.dir  # DANGEROUS: Everyone can write!
```

### `chown` - Change Owner

Change file ownership (usually requires `sudo`).

```bash
$ sudo chown newuser:newgroup file.txt
```

## 2. System Information

### Hardware & OS Info

```bash
$ uname -a              # Kernel and system info
$ hostname              # Network name of the machine
$ uptime                # How long system has been running
$ whoami                # Current user
$ top / htop            # Real-time process and resource monitoring
```

### Disk Usage

```bash
$ df -h                 # Disk space usage (Filesystem)
$ du -sh foldername/    # Disk usage of specific directory
```

### Memory Usage

```bash
$ free -h               # RAM and Swap usage
```

## 3. Managing Processes

Every running program is a process with a unique **PID** (Process ID).

### Listing Processes

```bash
$ ps aux                # Snapshot of all running processes
$ ps -ef                # Another format for listing processes
```

### Killing Processes

Terminating a hung or unwanted program.

```bash
$ kill 1234             # Kill process with PID 1234 (SIGTERM - polite kill)
$ kill -9 1234          # Force kill (SIGKILL - immediate termination)
```

## 4. Analyzing Logs

System logs are crucial for troubleshooting. Most reside in `/var/log`.

- `/var/log/syslog`: General system messages.
- `/var/log/auth.log`: Authentication attempts (sudo, login).
- `/var/log/dmesg`: Kernel buffer (boot logs).

**Example Analysis**:

```bash
# Find all failed login attempts
$ grep "Failed password" /var/log/auth.log

# Watch system logs in real-time
$ tail -f /var/log/syslog
```
