# File Management System Calls: The Kernel Interface

In system programming, we bypass standard libraries (`FILE *`, `fopen`) and speak directly to the kernel using **File Descriptors** (integers).

## 1. The Big 5 Syscalls

Header: `<fcntl.h>`, `<unistd.h>`

### `open()` - The Gatekeeper

**Prototype**: `int open(const char *pathname, int flags, mode_t mode);`

| Flag        | Description                                                       |
| :---------- | :---------------------------------------------------------------- |
| `O_RDONLY`  | Open for reading only.                                            |
| `O_WRONLY`  | Open for writing only.                                            |
| `O_RDWR`    | Open for reading and writing.                                     |
| **O_CREAT** | Create file if it doesn't exist. **Requires `mode` arg**.         |
| `O_TRUNC`   | Truncate file length to 0 (Erases content).                       |
| `O_APPEND`  | Write operations append to end of file.                           |
| `O_EXCL`    | Used with `O_CREAT`. Fail if file already exists. (Atomic check). |
| `O_SYNC`    | Write waits for physical disk I/O (Slower, Safer).                |

**Mode (Permissions)**:
Used only with `O_CREAT`. e.g., `0644` (`S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH`).

### `read()` & `write()`

**Prototype**: `ssize_t read(int fd, void *buf, size_t count);`

- **Returns**: Number of bytes read/written.
- **0**: End of File (EOF) for read.
- **-1**: Error (Check `errno`).

**Handling Partial Reads**:
Always check the return value. Network sockets or pipes may return fewer bytes than requested.

### `close()`

**Prototype**: `int close(int fd);`
Always close FDs to prevent leaks.

### `lseek()` - Moving the Cursor

**Prototype**: `off_t lseek(int fd, off_t offset, int whence);`

| Whence     | Description                                   |
| :--------- | :-------------------------------------------- |
| `SEEK_SET` | Offset from **Beginning** of file.            |
| `SEEK_CUR` | Offset from **Current** position.             |
| `SEEK_END` | Offset from **End** (Used to find file size). |

## 2. Error Handling (`errno`)

All syscalls return `-1` on error and set the global variable `errno`.

**Headers**: `<errno.h>`, `<string.h>`

| Method       | Description                                    | Example                            |
| :----------- | :--------------------------------------------- | :--------------------------------- |
| `perror()`   | Prints "Message: Error description" to stderr. | `perror("open failed");`           |
| `strerror()` | Returns string description of error code.      | `printf("%s\n", strerror(errno));` |

**Common Codes**:

- `EACCES`: Permission denied.
- `ENOENT`: No such file or directory.
- `EEXIST`: File exists (`O_CREAT | O_EXCL`).
- `EINTR`: Interrupted by signal. (Should retry).

## 3. Advanced I/O

### `dup()` and `dup2()`

Duplicate a file descriptor. Crucial for **Redirection**.

- `dup2(oldfd, newfd)`: Atomic. Closes `newfd` if open, then copies `oldfd` to `newfd`.
- **Example**: `dup2(fd, STDOUT_FILENO)` makes `printf` write to file `fd`.

### `ioctl()` and `fcntl()`

Control device parameters or file properties.

- `fcntl(fd, F_SETFL, O_NONBLOCK)`: Make I/O non-blocking.
