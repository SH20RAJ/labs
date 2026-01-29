# System Info, Permissions, and Advanced Process Management

## 1. Advanced File Permissions

Linux permissions are more than just `rwx`.

### The Mode Bits

`rwx` maps to `4-2-1`.

- `chmod 777` = `rwxrwxrwx`
- `chmod 755` = `rwxr-xr-x`

### Special Permissions (The 4th Digit)

| Bit        | Name         | Symbol      | Value | Effect                                                                                       |
| :--------- | :----------- | :---------- | :---- | :------------------------------------------------------------------------------------------- |
| **SUID**   | Set User ID  | `s` (User)  | 4000  | Run as **owner**, not as current user. (e.g., `passwd` needs root to write to `/etc/shadow`) |
| **SGID**   | Set Group ID | `s` (Group) | 2000  | Files created in directory inherit group ID. (Collaborative Folders).                        |
| **Sticky** | Sticky Bit   | `t`         | 1000  | Only owner can delete files in directory. (e.g., `/tmp`).                                    |

**Setting Special Bits**:

```bash
chmod u+s file      # Add SUID
chmod g+s dir       # Add SGID
chmod +t dir        # Add Sticky Bit
chmod 4755 file     # SUID + 755
```

### ACLs (Access Control Lists)

Standard groups are limited (only one group per file). ACLs allow fine-grained access.

**Commands**: `getfacl`, `setfacl`.

```bash
# Give user 'alice' read/write access to a file owned by 'bob'
setfacl -m u:alice:rw project_plan.txt

# Remove ACL
setfacl -x u:alice project_plan.txt

# View ACLs
getfacl project_plan.txt
```

### `umask` - Default Permissions

Determines permissions for NEW files.

- **Default**: `022` (Files get 644, Dirs get 755).
- **Calculation**: `Max Perms - Umask`.
  - File: `666 - 022 = 644`.
  - Dir: `777 - 022 = 755`.

---

## 2. Advanced Process Management

### `ps` - Process Status

**Usage**: `ps [OPTIONS]`

| Option   | Description                                             |
| :------- | :------------------------------------------------------ |
| `aux`    | **BSD Style**. All users, with TTYs. Standard use.      |
| `-ef`    | **System V Style**. Full listing.                       |
| `--sort` | Sort output. `ps aux --sort=-%mem` (Sort by RAM usage). |
| `axjf`   | **Tree View**. Shows parent-child relationship.         |

### `top` & `htop`

Real-time monitoring.

- **Load Average**: 1min, 5min, 15min. (1.0 = 1 CPU fully loaded).
- **NI (Nice Value)**: Priority. `-20` (Highest) to `19` (Lowest).
- **RES**: Resident Memory (Physical RAM).
- **VIRT**: Virtual Memory.

### `kill` - Signals

**Usage**: `kill -[SIGNAL] PID`

| Signal | ID      | Name      | Effect                                                              |
| :----- | :------ | :-------- | :------------------------------------------------------------------ |
| `-1`   | SIGHUP  | Hangup    | Reload configuration (often).                                       |
| `-2`   | SIGINT  | Interrupt | Ctrl+C. Polite stop.                                                |
| `-9`   | SIGKILL | Kill      | **Force Kill**. Cannot be handled/ignored. Kernel destroys process. |
| `-15`  | SIGTERM | Terminate | Default. Polite request to stop (cleanup allowed).                  |

**Other Commands**:

- `killall firefox` : Kill process by name.
- `pkill -u alice` : Kill all processes by user `alice`.

### Background Jobs

- `&`: Start in background. `sleep 100 &`.
- `Ctrl+z`: Pause (Stop) current foreground job.
- `bg`: Resume paused job in background.
- `fg`: Bring background job to foreground.
- `jobs`: List active jobs in current shell.

**Disowning**:
If you close terminal, background jobs die (SIGHUP).

- `nohup command &`: Ignore SIGHUP. Output goes to `nohup.out`.
- `disown`: Remove job from shell's job table (so it won't die on exit).
