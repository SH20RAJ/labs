# Scheduling, Monitoring, and Text Processing

## 1. Advanced Scheduling

### `cron` Deep Dive

The daemon checks `/etc/crontab` and `/var/spool/cron`.

**Special Strings**:

- `@reboot`: Run once at startup.
- `@daily`: Run once a day (midnight).
- `@hourly`: Run once an hour.

**System-wide vs User**:

- User: `crontab -e` (No user field).
- System: `/etc/crontab` (Has user field).
  `* * * * * root /path/to/script.sh`

### `at` - One-time Scheduling

Run a command _once_ in the future.

```bash
echo "backup.sh" | at 2:00 AM
atq     # List jobs
atrm 1  # Remove job 1
```

### Systemd Timers (Modern Alternative)

More powerful than cron.

- **Unit File**: `foo.service`.
- **Timer File**: `foo.timer`.
  ```ini
  [Timer]
  OnCalendar=daily
  Persistent=true
  ```

## 2. Advanced Text Processing (`awk` & `sed`)

### `awk` - Field Processing

Great for columns (CSV, logs).

**Syntax**: `awk 'pattern { action }' file`

- `$0`: Whole line. `$1`: First column. `$NF`: Last column.
- `NR`: Line number. `NF`: Number of fields.
- `-F`: Field separator (Default space).

**Examples**:

```bash
# Print username (1st col) of users with UID > 1000 (3rd col) from /etc/passwd
awk -F: '$3 > 1000 { print $1 }' /etc/passwd

# Sum a column of numbers
ls -l | awk '{ sum += $5 } END { print sum }'
```

### `sed` - Stream Editor

Great for search and replace.

**Syntax**: `sed 's/find/replace/flags' file`

- `s`: Substitute.
- `g`: Global (all occurrences in line).
- `-i`: Edit in-place (save changes to file).
- `d`: Delete line.

**Examples**:

```bash
# Replace 'foo' with 'bar' in all lines, save to file
sed -i 's/foo/bar/g' config.conf

# Delete lines 1 to 5
sed '1,5d' file.txt

# Delete lines containing "error"
sed '/error/d' log.txt

# Print only lines 10-20
sed -n '10,20p' file.txt
```

## 3. System Monitoring

### `vmstat`

Virtual Memory Statistics.
`vmstat 1` : Update every second.

- **si/so**: Swap In/Out. (Bad if high).
- **bi/bo**: Block I/O.

### `iostat`

Disk I/O stats.
`iostat -x 1`

- **%util**: Utilization. If near 100%, disk is saturated.

### `lsof` - List Open Files

Crucial for debugging "File in use" errors.

```bash
lsof -i :80        # Who is using port 80?
lsof -u alice      # Files opened by alice
lsof /var/log/syslog # Who is writing to this file?
```
