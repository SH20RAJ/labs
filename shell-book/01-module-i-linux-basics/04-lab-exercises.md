# Lab Exercises: Module I

## Exercise 1: File & Directory Manipulation

**Goal**: Practice basic navigation and file operations.

1. Create a directory structure: `project/src`, `project/docs`, `project/logs`.
2. Create 5 empty text files (file1.txt to file5.txt) in `project/src`.
3. Move `file1.txt` and `file2.txt` to `project/docs`.
4. Rename `project/docs/file1.txt` to `readme.md`.
5. Display the long listing (`ls -l`) of the `project` directory showing all subdirectories.

## Exercise 2: Permissions

**Goal**: Understand owner/group/other permissions.

1. Create a script file `hello.sh` with content `echo "Hello"`.
2. Try to run it (`./hello.sh`). Note the error.
3. Fix the permission using `chmod` so that only the **User** can read, write, and execute it, but **Group** and **Others** can only read it.
4. Verify permissions with `ls -l`.

## Exercise 3: Text Processing Pipe

**Goal**: Use `grep`, `wc`, and pipes.

1. Run `ls -R /etc > filelist.txt` (ignore errors if permission denied).
2. Count how many times the word "conf" appears in `filelist.txt`.
3. Sort `filelist.txt` alphabetically and save the first 20 lines to `top20.txt`.

## Exercise 4: The Backup Script

**Goal**: Write a bash script using variables and conditionals.

Create a script `backup_lab.sh` that:

1. Accepts a directory name as an argument.
2. Checks if the directory exists.
   - If YES: Creates a compressed tarball (`.tar.gz`) of that directory.
   - If NO: Prints an error message.
3. Prints "Backup completed successfully" at the end.

**Hint**:

```bash
if [ -d "$1" ]; then
    tar -czf "$1_backup.tar.gz" "$1"
    ...
```
