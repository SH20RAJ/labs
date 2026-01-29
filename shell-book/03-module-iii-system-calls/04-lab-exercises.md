# Lab Exercises: Module III

## Exercise 1: MyCopy

**Goal**: File syscalls (`open`, `read`, `write`).

Write a C program `mycp` that acts like the `cp` command.

- Usage: `./mycp <source_file> <dest_file>`
- Buffer size: 4096 bytes.
- Permissions: Destination file should be readable/writable by user (0600).
- Error Handling: Print meaningful error if source doesn't exist.

## Exercise 2: Tiny Shell

**Goal**: `fork`, `exec`, `wait`.

Compile and run the basic shell code provided in the "Custom Shell Implementation" chapter.
**Enhance it**:

1. Add a `pwd` built-in command that uses `getcwd()`.
2. Add an `echo` built-in command.

## Exercise 3: Daemonize

**Goal**: Process management.

Write a C program called `logger_daemon.c` that:

1. Turns itself into a daemon process (runs in background, detached).
2. Every 5 seconds, writes the current timestamp to `/tmp/daemon.log`.
3. Run it, verify with `ps aux | grep logger`, and check the log file.
4. Kill it using `kill <pid>`.
