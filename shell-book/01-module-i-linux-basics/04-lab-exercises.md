# Lab Exercises: Module I (Expanded)

## Challenge 1: The "Finder" Script

**Goal**: Combine `find`, `grep`, and `cp`.

Write a script `archive_logs.sh` that:

1.  Finds all files in `/var/log` (or a test folder) that end in `.log`.
2.  BUT only if they contain the text "ERROR".
3.  AND are larger than 1MB.
4.  Copy them to a `backup/errors/` folder.
5.  Append the current date to the filename (e.g., `syslog_20231020.log`).

## Challenge 2: Permission Repair

**Goal**: User `chmod`, `find`, and loops.

You have a web directory `/var/www/html` that is a mess.
Write a script `fix_perms.sh` that ensures:

1.  All **Directories** have permission `755` (`drwxr-xr-x`).
2.  All **Files** have permission `644` (`-rw-r--r--`).
3.  The `upload` folder (`/var/www/html/upload`) must be writable by the group (`775` and SGID).

**Hint**: Use `find` with `-type d` vs `-type f`.

## Challenge 3: Process Killer

**Goal**: `ps`, `awk`, `kill`.

Write a script that kills a process by name, but safely.

1.  Usage: `./kill_app.sh <process_name>`
2.  Find the PID of the process.
3.  Send `SIGTERM` first.
4.  Wait 5 seconds.
5.  Check if it's still running.
6.  If yes, send `SIGKILL` and print "Force Kiling PID...".

## Challenge 4: The System Auditor

**Goal**: Advanced reporting.

Create `audit.sh` that generates a `report.txt` containing:

1.  **Disk Usage**: Percentage of root `/` partition.
2.  **Memory**: Free RAM in MB.
3.  **Top Process**: The name of the process consuming the most CPU right now.
4.  **Open Ports**: List of listening TCP ports (hint: `ss` or `netstat`).
