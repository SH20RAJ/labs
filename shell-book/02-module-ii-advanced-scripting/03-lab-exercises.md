# Lab Exercises: Module II

## Exercise 1: Command Line Tools

**Goal**: Create a script that acts differently based on arguments.

Create a script `calc.sh` that takes 3 arguments: `operation` (`add`, `sub`, `mul`), `number1`, and `number2`.

- Usage: `./calc.sh add 5 10` -> Output: `15`
- Handle invalid operations and missing arguments gracefully.

## Exercise 2: System Health Monitor

**Goal**: Automation and file logging.

Write a script `health_check.sh` that:

1. Logs the current date and time to `health.log`.
2. Checks if CPU load (1 min average) is above 1.0 (use `uptime` or `top` or `/proc/loadavg`).
3. Checks if disk space on root `/` is used more than 80%.
4. If either condition is met, print "SYSTEM WARNING" to the log file.

## Exercise 3: Automating Backups with Rotation

**Goal**: Advanced file handling and arrays.

Create `smart_backup.sh` that:

1. Takes a source folder path as argument.
2. Creates a tarball in a `backups/` folder with timestamp.
3. **Rotation Logic**: Keep only the last 5 backups. Delete the oldest ones if there are more than 5 files in the backup folder.

## Exercise 4: Cron Setup

**Goal**: Scheduling.

1. Add a cron entry to run your `health_check.sh` every 10 minutes.
2. Add a cron entry to run `smart_backup.sh` every day at 3 AM.
   _(Note: Do not actually modify your system's crontab if you are on a shared machine, just write down the valid cron lines)_.
