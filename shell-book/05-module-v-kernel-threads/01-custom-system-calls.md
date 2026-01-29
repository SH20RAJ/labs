# Custom System Calls: Hacking the Kernel

Adding a system call requires modifying the kernel source and recompiling. This is the ultimate "Root" power.

## 1. The Syscall Table (x86_64)

The kernel maintains a table of function pointers.

- **File**: `arch/x86/entry/syscalls/syscall_64.tbl`
- **Format**: `<number> <abi> <name> <entry point>`
  ```text
  548   common   my_syscall   sys_my_syscall
  ```

## 2. Defining the Syscall (`SYSCALL_DEFINE`)

Use the macros in `<linux/syscalls.h>` to ensure correct argument handling and security checks (wrappers).

**Macro**: `SYSCALL_DEFINEx(name, type1, arg1, ...)` where x is number of args (0-6).

```c
// kernel/sys.c

SYSCALL_DEFINE2(my_math, int, a, int, b)
{
    printk(KERN_INFO "Syscall my_math called with %d, %d\n", a, b);
    return a + b;
}
```

## 3. Passing Arguments from User Space

Registers used for x64 Syscalls:

1.  **Syscall Number**: `RAX`
2.  **Args**: `RDI`, `RSI`, `RDX`, `R10`, `R8`, `R9`.
3.  **Return**: `RAX`.

## 4. Invoking without libc (`syscall()`)

Since `glibc` doesn't have a wrapper function for your new syscall yet, use `syscall()`.

```c
#define __NR_my_math 548

long res = syscall(__NR_my_math, 10, 20);
if (res < 0) {
    perror("syscall failed");
}
```

## 5. Security Implications

**NEVER trust user pointers!**
Users pass virtual addresses. The kernel cannot dereference them directly (security risk + they might be swapped out).

**Safe Data Copying**:

- `copy_from_user(to, from, n)`: Read data from user space.
- `copy_to_user(to, from, n)`: Write data to user space.

```c
SYSCALL_DEFINE1(set_msg, char __user *, msg)
{
    char buf[128];
    if (copy_from_user(buf, msg, sizeof(buf))) {
        return -EFAULT;
    }
    printk(KERN_INFO "Message: %s\n", buf);
    return 0;
}
```
