# Kernel Synchronization & Threads: Concurrency

## 1. Kernel Threads (`kthread`)

Kernel threads run only in kernel mode. They have no user address space (which makes expensive context switches cheaper).

**API**: `<linux/kthread.h>`

| Function                           | Description                                       |
| :--------------------------------- | :------------------------------------------------ |
| `kthread_run(func, data, name)`    | Create and start a thread immediately.            |
| `kthread_create(func, data, name)` | Create but don't start. (Need `wake_up_process`). |
| `kthread_stop(task)`               | Ask thread to stop. Returns thread return value.  |
| `kthread_should_stop()`            | Check inside thread loop if stop was requested.   |

**Example Loop**:

```c
int my_worker(void *data) {
    while (!kthread_should_stop()) {
        // Do work
        msleep(100);
    }
    return 0;
}
```

## 2. Synchronization Primitives

The Kernel is preemptive. We MUST lock shared data.

### A. Spinlocks (`<linux/spinlock.h>`)

**Behavior**: Busy Loop (CPU cycles). "Are we there yet? No? Check again."

- **Context**: Interrupt Context (ISRs) or Atomic Context.
- **Rule**: **NEVER SLEEP** holding a spinlock.
- **API**:
  - `spin_lock(&lock)`
  - `spin_unlock(&lock)`
  - `spin_lock_irqsave(&lock, flags)`: Disables interrupts locally (Safe for ISR sharing).

### B. Mutexes (`<linux/mutex.h>`)

**Behavior**: Sleep (Context Switch). "Wake me up when it's free."

- **Context**: Process Context only.
- **Rule**: Used for "long" held locks. Can sleep.
- **API**:
  - `mutex_lock(&lock)`
  - `mutex_unlock(&lock)`

### C. Semaphores (`<linux/semaphore.h>`)

**Behavior**: Counting lock. Allows N concurrent holders.

- **API**:
  - `down(&sem)`: Decrement (Acquire). Sleeps if 0.
  - `up(&sem)`: Increment (Release).

## 3. Atomic Operations (`<linux/atomic.h>`)

For simple integers, avoid locks. Use hardware atomic instructions.

- `atomic_set(&v, 5)`
- `atomic_add(10, &v)`
- `atomic_inc(&v)`
- `atomic_read(&v)`

**Why?**
`v++` is NOT atomic. It becomes:

1.  Read `v` to register.
2.  Increment register.
3.  Write register to memory.
    _Two threads doing this simultaneously causes a Lost Update._
