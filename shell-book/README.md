# Shell and Kernel Lab: The Ultimate Reference

Welcome to the **Comprehensive Shell and Kernel Guide**. This is not just a syllabus—it's a detailed encyclopedia for mastering Linux, from the shell prompt to the kernel source.

## 📚 Table of Contents

### [Module I: Linux Shell & Commands](./01-module-i-linux-basics/)

_The Encyclopedia of Commands._

- **Deep Dives**: `ls`, `find`, `grep`, `chmod` (SUID/SGID), `ps`.
- **Key Flags**: Explanations of every useful argument.
- **Labs**: Archive scripts, Permission repair, System auditing.

### [Module II: Advanced Scripting](./02-module-ii-advanced-scripting/)

_Automating the World._

- **Bash Mastery**: Arrays, Associative Arrays, Parameter Expansion (`${var%pat}`).
- **Debugging**: Traps, `set -euo pipefail`.
- **Tools**: `awk`, `sed`, `cron` vs Systemd Timers.

### [Module III: System Calls](./03-module-iii-system-calls/)

_Talking to the Kernel._

- **File I/O**: `open` (O_CREAT, O_EXCL), `dup2` (Redirection), `fcntl`.
- **Process**: `fork`, `execvp`, `waitpid` (WIFEXITED).
- **Project**: Building a Shell with Pipes support.

### [Module IV: Kernel Programming](./04-module-iv-kernel-programming/)

_Bare Metal & Drivers._

- **Bootloader**: Real Mode Assembly, BIOS Interrupts (`INT 10h`).
- **Modules**: `kmalloc` (GFP_KERNEL), `printk` levels, Kbuild Makefiles.

### [Module V: Advanced Kernel Features](./05-module-v-kernel-threads/)

_Concurrency & Hacking._

- **Syscalls**: Adding custom syscalls & syscall tables.
- **Concurrency**: Spinlocks vs Mutexes vs Semaphores.
- **Threads**: `kthread_run`.

## 🛠️ Prerequisites

- **OS**: Linux (Ubuntu/Debian/Fedora) or WSL2.
- **Compiler**: `gcc`, `make`, `nasm` (for Module IV).
- **Kernel Headers**: `sudo apt install linux-headers-$(uname -r)`.
