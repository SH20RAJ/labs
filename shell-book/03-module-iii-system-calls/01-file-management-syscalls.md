# File Management System Calls

In this module, we move from shell commands to C programming, interacting directly with the Linux kernel via **System Calls**.

## 1. What is a System Call?

A system call (syscall) is the programmatic way a program requests a service from the kernel.

- **User Space**: Where your C program runs.
- **Kernel Space**: Where the syscall handles the request.

## 2. File I/O System Calls

Unlike high-level C functions (`fopen`, `fprintf`), syscalls work with **File Descriptors (int)**.

### Headers Required

```c
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
```

### `open()`

Opens a file and returns a file descriptor (fd).

```c
int fd = open("file.txt", O_RDONLY);
if (fd < 0) {
    perror("Error opening file");
}
```

**Flags**:

- `O_RDONLY`: Read only.
- `O_WRONLY`: Write only.
- `O_CREAT`: Create file if not exists.
- `O_TRUNC`: Truncate file length to 0.

### `read()`

Reads bytes from a file descriptor into a buffer.

```c
char buffer[100];
ssize_t bytesRead = read(fd, buffer, sizeof(buffer));
```

### `write()`

Writes bytes from a buffer to a file descriptor.

```c
char *msg = "Hello Kernel";
write(fd, msg, strlen(msg));
```

### `close()`

Closes the file descriptor.

```c
close(fd);
```

### Example: Copy File Program (Simple `cp`)

```c
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

#define BUFFER_SIZE 1024

int main(int argc, char *argv[]) {
    if (argc != 3) {
        printf("Usage: %s <source> <dest>\n", argv[0]);
        return 1;
    }

    int src = open(argv[1], O_RDONLY);
    if (src < 0) { perror("Source error"); return 1; }

    int dest = open(argv[2], O_WRONLY | O_CREAT | O_TRUNC, 0644);
    if (dest < 0) { perror("Dest error"); close(src); return 1; }

    char buffer[BUFFER_SIZE];
    ssize_t bytes;

    while ((bytes = read(src, buffer, BUFFER_SIZE)) > 0) {
        write(dest, buffer, bytes);
    }

    close(src);
    close(dest);
    return 0;
}
```
