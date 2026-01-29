# Linux Kernel Modules (LKM)

## 1. What is a Kernel Module?

The Linux kernel is monolithic but extensible. LKMs allow dynamic loading/unloading of code (drivers, filesystems) without rebooting.

## 2. Hello World Module

Kernel code is written in C but has different rules:

- No `libc` (no `printf`, use `printk`).
- No floating point math.
- Small stack space.

### The Code (`hello.c`)

```c
#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Your Name");
MODULE_DESCRIPTION("A simple Hello World LKM");

static int __init hello_init(void) {
    printk(KERN_INFO "Hello, Kernel World!\n");
    return 0; // Success
}

static void __exit hello_exit(void) {
    printk(KERN_INFO "Goodbye, Kernel World!\n");
}

module_init(hello_init);
module_exit(hello_exit);
```

## 3. Compiling Modules

You need a `Makefile` that points to the kernel headers.

### `Makefile`

```make
obj-m += hello.o

all:
	make -C /lib/modules/$(shell uname -r)/build M=$(PWD) modules

clean:
	make -C /lib/modules/$(shell uname -r)/build M=$(PWD) clean
```

### Build & Run

1. `make` -> Generates `hello.ko`.
2. `sudo insmod hello.ko` -> Insert module.
3. `dmesg | tail` -> Check logs for "Hello, Kernel World!".
4. `sudo rmmod hello` -> Remove module.
5. `dmesg | tail` -> Check logs for "Goodbye...".

## 4. Module Parameters

Pass arguments when loading a module.

```c
#include <linux/moduleparam.h>

static int myint = 42;
module_param(myint, int, 0644); // name, type, permissions
```

Inside `hello_init`, you can use `myint`.
Load with:

```bash
sudo insmod hello.ko myint=100
```
