The <b>Iterator design pattern</b> is a <a href="../../../Readme.md#behavioural">behavioural design pattern </a> that provides a way to access elements of a collection sequentially without exposing its interval structure.

* Provide a way to access elements of a collection sequentially without exposing its internal structure.


```

                +--------------------------+
                |    <<interface>>         |
                |        Iterator<T>       |
                +--------------------------+
                | + hasNext(): boolean     |
                | + next(): T              |
                +--------------------------+
                           ^
                           |
                +--------------------------+
                |    PlaylistIterator      |
                +--------------------------+
                | - playlist: Playlist     |
                | - index: int              |
                +--------------------------+
                | + hasNext()               |
                | + next()                  |
                +--------------------------+


                +-------------------------------+
                |   <<interface>>               |
                |   IterableCollection<T>       |
                +-------------------------------+
                | + createIterator(): Iterator  |
                +-------------------------------+
                           ^
                           |
                +-------------------------------+
                |           Playlist            |
                +-------------------------------+
                | - songs: List<String>         |
                +-------------------------------+
                | + addSong()                   |
                | + getSongAt(index)            |
                | + getSize()                   |
                | + createIterator()            |
                +-------------------------------+


                +-----------------------+
                |         Main          |
                +-----------------------+
                | + main(args): void    |
                +-----------------------+



| Iterator            | Composite         |
| ------------------- | ----------------- |
| Traverses structure | Defines structure |
| Access logic        | Hierarchy logic   |
| Often used together |                   |

```