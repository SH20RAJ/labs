# Job Scheduling and System Monitoring

## 1. Job Scheduling with Cron

`cron` is the time-based job scheduler in Linux. Users have their own crontabs.

### Crontabs

Edit your crontab:

```bash
crontab -e
```

### Syntax

```
# m h  dom mon dow   command
  * *   *   *   *    command_to_run
  | |   |   |   |
  | |   |   |   +---- Day of Week (0 - 7) (Sunday=0 or 7)
  | |   |   +-------- Month (1 - 12)
  | |   +------------ Day of Month (1 - 31)
  | +---------------- Hour (0 - 23)
  +------------------ Minute (0 - 59)
```

**Examples**:

- `0 0 * * * /home/user/backup.sh` -> Run every day at midnight.
- `*/15 * * * * php script.php` -> Run every 15 minutes.
- `0 9 1 * *` -> Run at 9 AM on the 1st of every month.

## 2. Process Monitoring & Management

### `top` and `htop`

Interactive process viewers.

- **CPU%**: Percentage of CPU used.
- **MEM%**: Percentage of RAM used.
- **Load Average**: System load over 1, 5, 15 minutes.

### Background and Foreground Jobs

- `&`: Run command in background.
  ```bash
  $ sleep 100 &
  ```
- `jobs`: List background jobs.
- `fg %1`: Bring job 1 to foreground.
- `bg %1`: Resume stopped job in background.

## 3. System Resource Monitoring Scripts

### Memory Usage Script

```bash
#!/bin/bash
# Alert if free memory is low

THRESHOLD=500  # MB
FREE_MEM=$(free -m | grep Mem | awk '{print $4}')

if [ "$FREE_MEM" -lt "$THRESHOLD" ]; then
    echo "WARNING: Low Memory! Only ${FREE_MEM}MB free." | mail -s "Memory Alert" admin@example.com
fi
```

### Disk Usage Script

```bash
#!/bin/bash
# Check disk usage of root /

USAGE=$(df / | grep / | awk '{ print $5 }' | sed 's/%//g')

if [ "$USAGE" -gt 90 ]; then
    echo "CRITICAL: Disk usage is at ${USAGE}%!"
fi
```

## 4. Kernel Log Monitoring

Kernel messages are vital for diagnosing hardware or driver issues.

- **dmesg**: Prints the message buffer.
- **/var/log/kern.log**: Persistent kernel logs.

**Pattern Matching in Logs**:

```bash
#!/bin/bash
# Check for Segfaults in kernel logs

if grep -q "segfault" /var/log/kern.log; then
    echo "Segmentation faults detected in kernel log!"
fi
```
