# Bootloader and Low-Level Assembly

## 1. The Boot Sequence (Detailed)

1.  **Power On**: CPU starts in **Real Mode** (16-bit).
2.  **CS:IP Reset**: `CS=0xF000`, `IP=0xFFF0`. Jumps to BIOS.
3.  **POST**: Self-test.
4.  **Boot Sector Search**: BIOS checks configured devices (Floppy, HDD, USB) for magic number `0xAA55` at byte 510.
5.  **MBR Load**: BIOS loads 512 bytes to `0x7C00`. Jumps there.

## 2. 16-Bit Real Mode Assembly

In Real Mode, we have 16-bit registers: `AX`, `BX`, `CX`, `DX` (Data), `SI`, `DI`, `BP`, `SP` (Index/Ptr), `CS`, `DS`, `ES`, `SS` (Segments).

**Segmentation**:
Physical Address = `(Segment << 4) + Offset`.
Max Addressable Memory = 1MB.

### BIOS Interrupts (The "Original" Syscalls)

We rely on the BIOS (Basic Input/Output System) to talk to hardware.

#### `INT 0x10` - Video Services

- `AH = 0x0E`: Teletype Output.
  - `AL`: Character to print.
  - `BH`: Page Number.
  - `BL`: Color (Graphics mode only).

#### `INT 0x13` - Disk Services

- `AH = 0x02`: Read Sectors.
  - `AL`: Number of sectors.
  - `CH`: Cylinder. `CL`: Sector. `DH`: Head.
  - `ES:BX`: Buffer address.

**Example: Printing in Color**
To do this, we must switch video modes? No, just writing to Video Memory `0xB8000`.

```nasm
[org 0x7c00]

mov ax, 0xb800  ; Video Memory Segment
mov es, ax
xor di, di      ; Offset 0

mov byte [es:di], 'H'       ; Character
mov byte [es:di+1], 0x4F    ; Color (Red Background, White Text)

jmp $

times 510-($-$$) db 0
dw 0xaa55
```

## 3. Entering Protected Mode (32-bit)

1.  **Disable Interrupts**: `cli`.
2.  **Enable A20 Line**: Allow access > 1MB.
3.  **Load GDT**: Global Descriptor Table (defines memory segments).
4.  **Set PE Bit**: CR0 Register bit 0.
5.  **Far Jump**: Flush CPU pipeline.

```nasm
lgdt [gdt_descriptor]
mov eax, cr0
or eax, 1
mov cr0, eax
jmp CODE_SEG:init_pm
```
