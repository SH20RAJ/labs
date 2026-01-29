# Adding a Custom System Call

Adding a system call is an advanced task that requires recompiling the kernel. **Note: This guide assumes a Linux Kernel 5.x/6.x environment.**

## The Concept

1. **Syscall Table**: A list of function pointers.
2. **Syscall Number**: unique ID for your call.
3. **Kernel Function**: The C implementation (`sys_mycall`).

## Steps to Add a Syscall

### 1. Define the System Call

In the kernel source tree, find a suitable place (e.g., `kernel/sys.c` or a new file).

```c
#include <linux/kernel.h>
#include <linux/syscalls.h>

SYSCALL_DEFINE0(hello_kernel)
{
    printk("Hello from the System Call!\n");
    return 0;
}
```

### 2. Register the Syscall

Edit the syscall table. Arch specific: `arch/x86/entry/syscalls/syscall_64.tbl`.

Add a line at the end:

```
450    common    hello_kernel    sys_hello_kernel
```

_(450 is an example number, must be the next available)._

### 3. Add Prototype

In `include/linux/syscalls.h`, add:

```c
asmlinkage long sys_hello_kernel(void);
```

### 4. Compile and Install Kernel

```bash
make -j$(nproc)
sudo make modules_install
sudo make install
reboot
```

## Invoking the New Syscall

Since `glibc` doesn't know about it, use `syscall()`.

```c
#include <unistd.h>
#include <sys/syscall.h>
#include <stdio.h>

#define SYS_HELLO_KERNEL 450 // The number you assigned

int main() {
    long res = syscall(SYS_HELLO_KERNEL);
    printf("Syscall returned %ld\n", res);
    return 0;
}
```
