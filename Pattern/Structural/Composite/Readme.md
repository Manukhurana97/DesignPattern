The <b>Composite design pattern</b> is a <a href="../../../Readme.md#structural">structural design pattern</a> that let you treat individual object and compositions of objects uniformly.

* Composite = Treat a single object and a group of objects the same way

```

                +----------------------------------+
                |        <<interface>>             |
                |        FileSystemItem             |
                +----------------------------------+
                | + getSize(): int                  |
                | + printStructure(indent: String) |
                | + delete(): void                  |
                +----------------------------------+
                              ^
              ----------------|-----------------
              |                                 |
    +------------------------+     +------------------------+
    |          File          |     |         Folder         |
    +------------------------+     +------------------------+
    | - name: String         |     | - name: String         |
    | - size: int            |     | - items: List<FileSystemItem> |
    +------------------------+     +------------------------+
    | + getSize()            |     | + add(item)            |
    | + printStructure()     |     | + getSize()            |
    | + delete()             |     | + printStructure()     |
    +------------------------+     | + delete()             |
                                   +------------------------+

                +------------------------+
                |          Main          |
                +------------------------+
                | + main(args): void     |
                +------------------------+




| Composite Rule     | Your Code                                   |
| ------------------ | ------------------------------------------- |
| Common interface   | `FileSystemItem`                            |
| Leaf               | `File`                                      |
| Composite          | `Folder`                                    |
| Tree structure     | Folder → Files                              |
| Uniform operations | `getSize()`, `printStructure()`, `delete()` |


```