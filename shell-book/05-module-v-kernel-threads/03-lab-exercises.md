# Lab Exercises: Module V (Mastery)

## Challenge 1: The Race Condition

**Goal**: Demonstrate the need for locks.

1.  Create a module with a global variable `static int count = 0`.
2.  Create a function `void increment(void *data)` that runs a loop 1,000,000 times: `count++`.
3.  Launch **two** kernel threads running this function.
4.  Wait for them. Print `count`.
5.  **Observe**: Is it 2,000,000? Likely less.
6.  **Fix**: Add `atomic_inc(&count)` or `spin_lock`.

## Challenge 2: The Producer-Consumer

**Goal**: Semaphores (`down/up`).

1.  Buffer Size = 5 (Circular Buffer).
2.  **Producer Thread**: Generates data, puts in buffer.
    - Must wait if Buffer Full (`down(&empty)`).
    - Protect buffer access (`mutex`).
    - Signal Data Ready (`up(&full)`).
3.  **Consumer Thread**: Reads data.
    - Must wait if Buffer Empty (`down(&full)`).
4.  Implement using `struct semaphore` and `kthread`.

## Challenge 3: Hacking the Syscall Table (Rootkit Style)

**Goal**: Understand Kernel Internals. (Educational Only).

1.  Normally, the syscall table is Read-Only.
2.  **Disable Write Protection**: Toggle `CR0` register bit 16.
3.  **Hook `sys_mkdir`**: Save the original function pointer. Replace it with `my_mkdir`.
4.  **In `my_mkdir`**: Print "mkdir called for: <path>". Then call original `sys_mkdir`.
5.  **Restore**: On module unload, restore original function.
6.  **Test**: Run `mkdir test` and check dmesg.

_(Warning: This may crash your kernel if done wrong. Use a VM)._
