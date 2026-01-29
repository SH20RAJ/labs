# Building a Custom Shell

One of the best ways to understand an OS is to build a shell. A shell basically does this loop:

1. **Read**: specific command from stdin.
2. **Parse**: Split string into program and arguments.
3. **Execute**: Fork, Exec, Wait.

## Simplified Shell Logic (in C)

```c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>

#define MAX_CMD_LEN 1024
#define MAX_ARGS 64

void split_line(char *line, char **args) {
    int i = 0;
    char *token = strtok(line, " \t\n");
    while (token != NULL) {
        args[i++] = token;
        token = strtok(NULL, " \t\n");
    }
    args[i] = NULL;
}

int main() {
    char line[MAX_CMD_LEN];
    char *args[MAX_ARGS];

    while (1) {
        printf("myshell> ");
        if (fgets(line, MAX_CMD_LEN, stdin) == NULL) break;

        split_line(line, args);

        if (args[0] == NULL) continue;

        // Built-in commands
        if (strcmp(args[0], "exit") == 0) {
            break;
        }
        if (strcmp(args[0], "cd") == 0) {
            if (args[1] == NULL) {
                fprintf(stderr, "cd: expected argument\n");
            } else {
                if (chdir(args[1]) != 0) perror("cd");
            }
            continue;
        }

        // External commands
        pid_t pid = fork();
        if (pid == 0) {
            // Child process
            if (execvp(args[0], args) == -1) {
                perror("execvp");
            }
            exit(EXIT_FAILURE);
        } else if (pid < 0) {
            perror("fork");
        } else {
            // Parent process
            wait(NULL);
        }
    }
    return 0;
}
```

## Features to Add

1. **IO Redirection**: Detect `>` and use `dup2()` to change stdout.
2. **Pipes**: Detect `|` and use `pipe()` to connect two processes.
3. **Background**: Detect `&` and skipped `wait()`.
