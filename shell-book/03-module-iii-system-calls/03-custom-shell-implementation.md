# Building a Custom Shell (Deep Dive)

Building a shell requires handling PIDs, signals, pipes, and redirection.

## 1. The Core Loop

```c
while (1) {
    print_prompt();
    read_command(line);
    parse_args(line, args);
    execute(args);
}
```

## 2. Handling Redirection (`>` and `<`)

Before `exec()`, you must replace `stdin`/`stdout`.

**Implementing `ls > out.txt`**:

1.  `fork()`.
2.  Inside Child:
    - `fd = open("out.txt", O_WRONLY|O_CREAT|O_TRUNC, 0644);`
    - `dup2(fd, STDOUT_FILENO);` // Replace stdout with file
    - `close(fd);`
    - `execvp("ls", args);`

## 3. Implementing Pipes (`cmd1 | cmd2`)

A pipe connects stdout of cmd1 to stdin of cmd2.

```c
int fd[2];
pipe(fd); // fd[0] is Read, fd[1] is Write

if (fork() == 0) {
    // Left Process (Writer)
    dup2(fd[1], STDOUT_FILENO);
    close(fd[0]);
    close(fd[1]);
    execvp(cmd1[0], cmd1);
}

if (fork() == 0) {
    // Right Process (Reader)
    dup2(fd[0], STDIN_FILENO);
    close(fd[1]);
    close(fd[0]);
    execvp(cmd2[0], cmd2);
}

// Parent must close everything
close(fd[0]);
close(fd[1]);
wait(NULL);
wait(NULL);
```

## 4. Handling Signals

A shell should ignore `SIGINT` (Ctrl+C) so it doesn't die when you try to kill a running job.

```c
signal(SIGINT, SIG_IGN);
```

But the **Child** process must restore the default handler, or it won't be killable!

```c
// Inside child, before exec
signal(SIGINT, SIG_DFL);
```
