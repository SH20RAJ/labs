# Lab Exercises: Module II (Mastery)

## Challenge 1: The Log Summarizer

**Goal**: Use Associative Arrays and Awk.

Write `summarize_logs.sh`.

1.  Read a mock access log (format: `IP - - [Date] "Request" Status Bytes`).
    ```
    192.168.1.1 - - [01/Jan/2023 ...] "GET /index.html" 200 450
    192.168.1.2 - - [01/Jan/2023 ...] "POST /login" 403 120
    ```
2.  Count requests per IP address using an **Associative Array**.
3.  Count requests per Status Code (200, 404, 500).
4.  Print the Top 3 IPs and Top 3 Status Codes.

## Challenge 2: The Bulk Renamer

**Goal**: Parameter Expansion and Loops.

Write `renamer.sh`.

1.  Accept a directory and a file extension (e.g., `jpg`) as arguments (use `getopts`).
2.  Iterate through all files.
3.  Prefix every file with its creation date `YYYY-MM-DD_filename.jpg`.
4.  Replace spaces in filenames with underscores `_`.
    - Example: `My Vacation.jpg` -> `2023-01-01_My_Vacation.jpg`.

## Challenge 3: Reliable Backup with Rotation

**Goal**: Arrays, Traps, and Error Handling.

Write `backup_pro.sh`.

1.  Use `set -euo pipefail`.
2.  Define a `cleanup` function trapped on EXIT to remove temporary tarballs.
3.  Store backup paths in an Indexed Array.
4.  Create a backup.
5.  **Rotation**: Keep only the last 3 backups. Use an array to list existing backups, sort by time, and delete the oldest if count > 3.

## Challenge 4: Systemd Service Monitor

**Goal**: Automation.

Create a script that:

1.  Check if `nginx` is running (`systemctl is-active`).
2.  If NOT active:
    - Try to restart it.
    - Check again.
    - If still failed, send an alert (echo to stderr) and exit with code 1.
    - If recovered, log a message to `/var/log/recovery.log`.
