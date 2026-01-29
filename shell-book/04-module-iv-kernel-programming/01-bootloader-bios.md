# Bootloader and BIOS Interrupts

## 1. The Boot Process

When a computer turns on:

1. **BIOS/UEFI**: Initializes hardware. Check for bootable devices.
2. **Bootloader (GRUB/LILO)**: Loads the kernel into memory.
3. **Kernel**: Initializes OS, mounts root filesystem.
4. **Init System (systemd/SysVinit)**: Starts user-space services.

## 2. Writing a Simple Bootloader

A bootloader resides in the first **512 bytes** of the boot disk (MBR - Master Boot Record). It must end with the magic signature `0x55AA`.

### Printing "Hello OS" via BIOS Interrupts

We use Assembly (NASM syntax) to interact with the BIOS.

- **Interrupt 0x10**: Video Services.
- **Function 0x0E**: Teletype Output (Print character).

```nasm
; boot.asm - A simple bootloader
[org 0x7c00]    ; BIOS loads MBR at 0x7c00

mov ah, 0x0e    ; TTY mode
mov al, 'H'
int 0x10
mov al, 'e'
int 0x10
mov al, 'l'
int 0x10
mov al, 'l'
int 0x10
mov al, 'o'
int 0x10

jmp $           ; Infinite loop

; Padding and Magic Number
times 510-($-$$) db 0
dw 0xaa55
```

### Compiling and Testing with QEMU

1. **Compile**:
   ```bash
   nasm -f bin boot.asm -o boot.bin
   ```
2. **Run in QEMU**:
   ```bash
   qemu-system-x86_64 boot.bin
   ```
   _You should see "Hello" printed on the screen!_

## 3. Kernel Parameters

The bootloader passes parameters to the kernel at boot time.
Examples: `quiet`, `splash`, `root=/dev/sda1`.
These can be viewed in `/proc/cmdline`.
