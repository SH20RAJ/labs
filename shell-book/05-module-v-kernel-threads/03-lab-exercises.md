# Lab Exercises: Module V

## Exercise 1: The Periodic Logger

**Goal**: Kernel Threads `kthread_run`.

Write a kernel module `periodic.c` that:

1. Starts a kernel thread on initialization.
2. The thread loops indefinitely (until stopped) and prints "Tick Tock: <jiffies>" to the kernel log every 2 seconds.
3. Stops the thread gracefully on module unload.

## Exercise 2: Race Condition Simulator

**Goal**: Understand synchronization.

1. Create a module with a shared global variable `int counter = 0`.
2. Launch two threads that increment `counter` 1000 times in a loop.
3. **Without Locks**: Run it. Print the final value of `counter`. Is it 2000? Probably not.
4. **With Locks**: Add a `spinlock` or `mutex`. Run it again. Is it 2000 now?

## Exercise 3: User-Space Tester

**Goal**: Invoking syscalls.

_Note: You cannot easily add a real syscall without recompiling the kernel. For this exercise, we will simulate it using a kernel module + `debugfs` or `procfs`._

1. Create a kernel module that creates a file `/proc/mysyscall`.
2. When you write to this file (e.g., `echo "hello" > /proc/mysyscall`), the kernel module acts like your syscall implementation.
3. Write a C program that opens `/proc/mysyscall` and writes data to it.
