# Linux Kernel Modules (Deep Dive)

## 1. The Kernel Environment

- **No libc**: You cannot use `printf`, `malloc`, `sleep`.
- **Kernel Stack**: Very small (Simultaneous threads have ~8KB stack). Don't allocate huge arrays on stack!
- **Concurrency**: Your code can be preempted at any time.

## 2. API Reference

### Headers

```c
#include <linux/module.h>  // Core
#include <linux/kernel.h>  // Types and Macros
#include <linux/init.h>    // __init, __exit
#include <linux/kthread.h> // Threads
#include <linux/sched.h>   // Task Scheduling
#include <linux/delay.h>   // msleep, udelay
```

### Logging (`printk`)

**Levels**:

- `KERN_EMERG` (0): System is unusable.
- `KERN_ALERT` (1): Action required immediately.
- `KERN_CRIT` (2): Critical conditions.
- `KERN_ERR` (3): Error.
- `KERN_WARNING` (4): Warning.
- `KERN_INFO` (6): Informational.
- `KERN_DEBUG` (7): Debug-level.

Use `pr_info()`, `pr_err()` macros for cleaner code.

```c
pr_info("Module loaded successfully\n");
```

### Memory Allocation

**`kmalloc(size_t size, gfp_t flags)`**:

- Used for small allocations (< 128KB, usually physically contiguous).
- **Flags**:
  - `GFP_KERNEL`: Can sleep (wait for memory). Standard.
  - `GFP_ATOMIC`: Cannot sleep. Use in Interrupts.
  - `GFP_DMA`: Under 16MB (for DMA).

**`vmalloc(unsigned long size)`**:

- Allocates virtually contiguous memory (physically fragmented).
- Slower (TLB trashing). Used for large buffers.

**Freeing**:

- `kfree(ptr)`
- `vfree(ptr)`

## 3. The Build System (Kbuild)

Your Makefile calls the kernel's Makefile.

```make
obj-m += mymodule.o

all:
	make -C /lib/modules/$(shell uname -r)/build M=$(PWD) modules

clean:
	make -C /lib/modules/$(shell uname -r)/build M=$(PWD) clean
```

- `obj-m`: Build as module.
- `obj-y`: Build into kernel (static).
