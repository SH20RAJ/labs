# Kernel Threads and Synchronization

## 1. Kernel Threads

Kernel threads act like user threads but run in kernel space. They are useful for background tasks (e.g., flushing disk buffers).

**API**: `<linux/kthread.h>`

### Creating a Thread

```c
#include <linux/kthread.h>
#include <linux/delay.h>

static struct task_struct *my_thread;

int thread_fn(void *data) {
    while (!kthread_should_stop()) {
        printk(KERN_INFO "Thread running...\n");
        msleep(1000); // Sleep 1 second
    }
    return 0;
}

// In module_init:
my_thread = kthread_run(thread_fn, NULL, "my_worker");
```

### Stopping a Thread

```c
// In module_exit:
if (my_thread) {
    kthread_stop(my_thread);
}
```

## 2. Synchronization

When multiple threads (or ISRs) access shared data, we need locking.

### Mutex

Sleeping lock (good for threads).

```c
#include <linux/mutex.h>

static DEFINE_MUTEX(my_mutex);

void safe_function() {
    mutex_lock(&my_mutex);
    // Critical Section
    mutex_unlock(&my_mutex);
}
```

### Spinlocks

Busy-wait lock (good for interrupts, code must be fast).

```c
#include <linux/spinlock.h>

static DEFINE_SPINLOCK(my_lock);

void fast_function() {
    spin_lock(&my_lock);
    // Critical Section (NO SLEEPING HERE)
    spin_unlock(&my_lock);
}
```

### Semaphores

Old school, counting locks.

```c
#include <linux/semaphore.h>

static struct semaphore my_sem;

// Init
sema_init(&my_sem, 1); // 1 = Mutex behavior

// Use
down(&my_sem); // Acquire
up(&my_sem);   // Release
```
