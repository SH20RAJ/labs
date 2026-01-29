# Process Management System Calls

## 1. Process Creation: `fork()`

`fork()` is the only way to create a new process in Unix.

**Prototype**: `pid_t fork(void);`

| Return Value | Meaning                                              |
| :----------- | :--------------------------------------------------- |
| `0`          | Running in **Child** process.                        |
| `> 0`        | Running in **Parent** process. (Value is Child PID). |
| `-1`         | Error (e.g., limit reached).                         |

**Memory Handling**:
Uses **Copy-on-Write (COW)**. Parent and Child share physical memory pages until one of them writes to memory. Then, the kernel duplicates only that page.

## 2. Executing Programs: The `exec()` Family

Replaces the current process image with a new program.
**It does not return** (unless error).

| Function                        | Path Search?    | Args Format | Environment |
| :------------------------------ | :-------------- | :---------- | :---------- |
| `execl(path, arg0, ..., NULL)`  | No              | List        | Inherit     |
| `execlp(file, arg0, ..., NULL)` | **Yes ($PATH)** | List        | Inherit     |
| `execle(path, ..., NULL, envp)` | No              | List        | **Custom**  |
| `execv(path, argv[])`           | No              | Array       | Inherit     |
| `execvp(file, argv[])`          | **Yes ($PATH)** | Array       | Inherit     |
| `execvpe(file, argv[], envp)`   | **Yes**         | Array       | **Custom**  |

## 3. Process Synchronization: `wait()`

### `waitpid()`

**Prototype**: `pid_t waitpid(pid_t pid, int *wstatus, int options);`

| Option      | Description                                                |
| :---------- | :--------------------------------------------------------- |
| `WNOHANG`   | Return immediately if no child has exited. (Non-blocking). |
| `WUNTRACED` | Also return if child is stopped (SIGSTOP).                 |

### Inspecting Status `wstatus`

- `WIFEXITED(status)`: True if exited normally.
- `WEXITSTATUS(status)`: Get the exit code (0-255).
- `WIFSIGNALED(status)`: True if killed by signal.
- `WTERMSIG(status)`: Get the number of the signal that killed it.

## 4. Zombies and Orphans

- **Zombie**: A child that exited but parent hasn't `wait()`ed. Consumes a PID but no memory.
- **Orphan**: Parent died before child. Child is adopted by `init` (PID 1).

## 5. Daemonization (The Right Way)

1.  `fork()`, parent exits. (Verify not group leader).
2.  `setsid()` (Create new session, detach TTY).
3.  `fork()` again (Verify not session leader, cannot re-acquire TTY).
4.  `chdir("/")`.
5.  `umask(0)`.
6.  Close all FDs (0, 1, 2).
