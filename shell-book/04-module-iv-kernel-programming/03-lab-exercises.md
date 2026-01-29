# Lab Exercises: Module IV (Mastery)

## Challenge 1: The "VGA" Bootloader

**Goal**: Write Real Mode Assembly.

1.  Write a bootloader (`boot.asm`) that switches video mode to **VGA 320x200 256-color** (`INT 10h, AH=00h, AL=13h`).
2.  Draw a pixel in the center of the screen.
    - Video memory for Mode 13h starts at `0xA0000`.
    - Address = `(Y * 320) + X`.
    - Write a color byte (e.g., `0x0F` for White) to that address.

## Challenge 2: The Kernel Sniffer

**Goal**: Iterate over kernel structures.

Write a Kernel Module that:

1.  Iterates over the `current` task_struct (representing the `insmod` process).
2.  Walks up the parent chain (`current->real_parent`) until it hits PID 1 (systemd/init).
3.  Logs the `comm` (Name) and `pid` of every ancestor.
    - Output: `insmod(1234) -> bash(1000) -> sshd(500) -> systemd(1)`.

## Challenge 3: The "Oops" Creator

**Goal**: Debugging Kernel Panics.

Write a module that **intentionally crashes**.

1.  Create a NULL pointer: `int *ptr = NULL;`.
2.  Try to write to it: `*ptr = 10;`.
3.  Load the module.
4.  Observe the **Kernel Oops** in `dmesg`.
5.  Analyze the **Call Trace** and **EIP** (Instruction Pointer) to pinpoint the line of code.

**(Do this in a VM. It will likely kill the current process or the OS).**

## Challenge 4: Keyboard Logger (Conceptual)

**Goal**: Interrupt Handling.

_(Advanced)_: Attempt to register an interrupt handler for IRQ 1 (Keyboard).

- `request_irq(1, handler_func, IRQF_SHARED, "my_kb", (void *)(handler_func));`
- In the handler, print "Key Pressed!" to dmesg.
- **Note**: This conflicts with the actual keyboard driver, so you likely cannot claim it exclusively, but try `IRQF_SHARED`.
