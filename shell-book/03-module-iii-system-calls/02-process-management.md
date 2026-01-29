# Process Management System Calls

## 1. Process Creation: `fork()`

`fork()` creates a new process by duplicating the calling process.

- The new process is the **Child**.
- The calling process is the **Parent**.

```c
#include <unistd.h>
#include <stdio.h>

int main() {
    pid_t pid = fork();

    if (pid < 0) {
        perror("Fork failed");
    } else if (pid == 0) {
        printf("I am the child process (PID: %d)\n", getpid());
    } else {
        printf("I am the parent process. My child's PID is %d\n", pid);
    }
    return 0;
}
```

## 2. Executing Programs: `exec()` Family

`fork()` creates a copy. `exec()` replaces the current process image with a new program.
Common variants: `execl`, `execv`, `execvp`.

```c
// Example: Running 'ls -l'
char *args[] = {"ls", "-l", NULL};
execvp("ls", args);
// Code below here will NOT run if execvp succeeds!
perror("execvp failed");
```

## 3. Waiting for Processes: `wait()`

A parent should wait for its child to finish to avoid creating "zombie" processes.

```c
#include <sys/wait.h>

int status;
wait(&status); // Waits for any child to terminate
if (WIFEXITED(status)) {
    printf("Child exited with status %d\n", WEXITSTATUS(status));
}
```

## 4. Background Processes & Daemons

### Creating a Daemon

A daemon is a background process not attached to a terminal.
**Steps**:

1. `fork()` and exit parent (detach from shell).
2. `setsid()` (create new session).
3. `chdir("/")` (unmount safety).
4. `umask(0)` (reset permissions).
5. Close file descriptors (stdin, stdout, stderr).
