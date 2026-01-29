# Lab Exercises: Module IV

## Exercise 1: Boot Sector

**Goal**: Understand the bare minimum boot process.

1. Write the `boot.asm` code provided in the theory.
2. Modify it to print your own name instead of "Hello".
3. Use a different color! (Hint: BIOS `int 10h` function `0Eh` uses BL register for color, but only in graphical modes. Try function `13h` for strings with attributes, but that's harder. Stick to text for now, or just change the message).

## Exercise 2: My First Kernel Module

**Goal**: Compile and load a kernel module.

1. Create a directory `my_module`.
2. Create `hello.c` and `Makefile`.
3. Compile with `make`.
4. Load it: `sudo insmod hello.ko`.
5. Check logs: `sudo dmesg | tail`.
6. Unload it: `sudo rmmod hello`.
7. Check logs again.

## Exercise 3: Parameter Power

**Goal**: Module parameters.

Modify `hello.c` to accept a string parameter `who`.

- Default value: "World".
- If loaded with `who="Student"`, it should print "Hello, Student!".

```c
static char *who = "World";
module_param(who, charp, 0644);
// In init: printk(..., who);
```
