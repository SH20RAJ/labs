# Lab Exercises: Module III (Mastery)

## Challenge 1: `cp -R` in C

**Goal**: Recursive Copy using System Calls.

Write `mycp.c` that supports:

1.  Recursively copying directories (Check `S_ISDIR(st.st_mode)` from `stat()`).
2.  Preserving file permissions (`fchmod(dest_fd, st.st_mode)`).
3.  Handling Errors: If a file fails, print `perror` but continue to next.

## Challenge 2: The Fork-Bomb Prevention

**Goal**: `fork()` limits.

Write a program that:

1.  Loops and calls `fork()`.
2.  Inside the child, loop forever.
3.  **BUT**: Before forking, check `getrlimit(RLIMIT_NPROC)` to see the system limit.
4.  If the number of children reaches 50% of the limit, STOP forking and print "Safety Limit Reached".
5.  Then, kill all children (`SIGKILL`) and exit gracefully.

## Challenge 3: Pipe Operator

**Goal**: Implement `ls | wc -l` in C.

Write `mypipe.c` that manually creates the pipeline:

1.  `pipe()`.
2.  `fork()` -> Child 1: `ls`. Redirect stdout to pipe write-end.
3.  `fork()` -> Child 2: `wc -l`. Redirect stdin to pipe read-end.
4.  Parent closes pipe ends and waits for both.

## Challenge 4: The Daemon with Logs

**Goal**: `dup2` + Daemon.

1.  Daemonize the process.
2.  Open `/var/log/mydaemon.log`.
3.  Use `dup2` to redirect `STDOUT` and `STDERR` to this log file.
4.  Now, any `printf()` or `perror()` will automatically go to the log file.
5.  Run a loop that prints "Daemon Timestamp: <time>" every 10 seconds.
