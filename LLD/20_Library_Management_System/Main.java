import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

enum BookType {
    BOOK, MAGAZINE
}


class LibraryItem {
    public final String id;
    public final String title;
    private final String author;
    private final List<BookCopy> copies = new CopyOnWriteArrayList<>();
    private final List<Member> observers = new CopyOnWriteArrayList<>();

    public LibraryItem(String title, String author) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public List<BookCopy> getCopies() {
        return copies;
    }

    public boolean hasObserver() {
        return !observers.isEmpty();
    }

    public boolean isObserver(Member m) {
        return observers.contains(m);
    }

    public void notifyObservers() {
    }

    public void addCopy(BookCopy copy) {
        copies.add(copy);
    }

    public void addObserver(Member m) {
        this.observers.add(m);
    }

    public void removeObserver(Member m) {
        this.observers.remove(m);
    }

}

class Book extends LibraryItem {

    public Book(String title, String author) {
        super(title, author);
    }

}

class Magazine extends LibraryItem {
    public Magazine(String title, String author) {
        super(title, author);
    }
}

class BookCopy {
    private final String id;
    private final LibraryItem item;
    private CurrentState currentstate = new AvailableState();

    public BookCopy(LibraryItem item) {
        this.id = UUID.randomUUID().toString();
        this.item = item;
    }

    public String getId() {
        return id;
    }

    public LibraryItem getItem() {
        return item;
    }

    public CurrentState getCurrentstate() {
        return currentstate;
    }

    public void setState(CurrentState currentstate) {
        this.currentstate = currentstate;
    }

    public void checkout(BookCopy copy, Member member) {
        currentstate.checkout(copy, member);
    }

    public void returnItem(BookCopy copy) {
        this.currentstate.returnItem(copy);
    }
}

class Member {
    public final Set<BorrowedBook> borrowedBook = new CopyOnWriteArraySet<>();
    private final String id;
    private final String name;
    private final boolean isActive;

    public Member(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.isActive = true;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void borrowed(BorrowedBook book) {
        borrowedBook.add(book);
    }

    public void removeBorrowing(BorrowedBook book) {
        borrowedBook.remove(book);
    }
}

class BorrowedBook {
    public final BookCopy copy;
    private final Member member;
    private final LocalDateTime checkoutAt;

    public BorrowedBook(BookCopy copy, Member member) {
        this.copy = copy;
        this.member = member;
        this.checkoutAt = LocalDateTime.now();
    }

    public Member getMember() {
        return member;
    }
}

// Factory
class ItemFactory {
    public static LibraryItem getItem(BookType type, String title, String author) {
        return switch (type) {
            case BOOK -> new Book(title, author);
            case MAGAZINE -> new Magazine(title, author);
            default -> throw new IllegalArgumentException("Unknown type");
        };
    }
}

// state
interface CurrentState {
    void checkout(BookCopy copy, Member member);

    void returnItem(BookCopy copy);

    void placeHold(BookCopy copy, Member member);
}

class AvailableState implements CurrentState {


    public void checkout(BookCopy copy, Member member) {
        TransactionService.getInstance().checkOut(copy, member);
        copy.setState(new checkOutState());
        System.out.println(copy.getId() + " checked out by " + member.getName());
    }

    public void returnItem(BookCopy copy) {
        System.out.println("Cannot return an item that is already available.");
    }

    public void placeHold(BookCopy copy, Member member) {
        System.out.println("Cannot place hold on an available item. Please check it out.");
    }
}

class checkOutState implements CurrentState {
    public void checkout(BookCopy c, Member member) {
        System.out.println(c.getId() + " is already checked out.");
    }

    public void returnItem(BookCopy c) {
        TransactionService.getInstance().returnBook(c);
        System.out.println(c.getId() + " returned.");
        if (c.getItem().hasObserver()) {
            c.setState(new OnHold());
            c.getItem().notifyObservers();
        } else {
            c.setState(new AvailableState());
        }
    }

    public void placeHold(BookCopy c, Member m) {
        c.getItem().addObserver(m);
        System.out.println(m.getName() + " place a hold on " + c.getItem().getTitle());
    }
}

class OnHold implements CurrentState {

    public void checkout(BookCopy copy, Member member) {
        if (copy.getItem().isObserver(member)) {
            TransactionService.getInstance().checkOut(copy, member);
            copy.setState(new checkOutState());
            copy.getItem().removeObserver(member);
            System.out.printf("Hold fulfilled. %s is checked out by %s%n", copy.getItem().getTitle(), member.getName());
        } else {
            System.out.printf("this item is on hold for other member %n");
        }
    }

    public void returnItem(BookCopy copy) {
        System.out.println("Invalid Operation, This copy is on hold");
    }

    public void placeHold(BookCopy copy, Member member) {
        System.out.println("Copy is already on hold");
    }
}

// strategy
interface SearchStrategy {
    List<LibraryItem> search(String query, List<LibraryItem> item);
}

class TitleSearchStrategy implements SearchStrategy {
    public List<LibraryItem> search(String query, List<LibraryItem> items) {
        String q = query.toLowerCase();
        return items.stream().filter(i -> i.getTitle().toLowerCase().contains(q)).collect(Collectors.toList());
    }
}

class AuthorSearchStrategy implements SearchStrategy {
    public List<LibraryItem> search(String query, List<LibraryItem> items) {
        String q = query.toLowerCase();
        return items.stream().filter(i -> i.getAuthor().toLowerCase().contains(q)).collect(Collectors.toList());
    }
}

class TransactionService {
    private static TransactionService INSTANCE;
    private final Map<String, BorrowedBook> borrowedBooks = new ConcurrentHashMap<>();

    public TransactionService() {

    }

    public synchronized static TransactionService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TransactionService();
        }

        return INSTANCE;
    }

    public void checkOut(BookCopy copy, Member member) {
        BorrowedBook bb = new BorrowedBook(copy, member);
        borrowedBooks.put(copy.getId(), bb);
        member.borrowed(bb);
    }

    public void returnBook(BookCopy c) {
        BorrowedBook book = borrowedBooks.remove(c.getId());
        if (book != null) {
            book.getMember().removeBorrowing(book);
        }
    }
}


// facade
class LibraryService {
    private static LibraryService INSTANCE;
    public final Map<String, LibraryItem> libraryItems = new ConcurrentHashMap<>();
    public final Map<String, Member> members = new ConcurrentHashMap<>();
    public final Map<String, BookCopy> bookCopies = new ConcurrentHashMap<>();

    private final SearchStrategy searchStrategy;


    public LibraryService() {
        searchStrategy = new TitleSearchStrategy();
    }

    public synchronized static LibraryService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LibraryService();
        }

        return INSTANCE;
    }

    public LibraryItem addItem(BookType type, String title, String author, int copies) {
        List<BookCopy> books = new ArrayList<>();
        LibraryItem item = ItemFactory.getItem(type, title, author);


        for (int i = 0; i < copies; i++) {
            BookCopy copy = new BookCopy(item);
            bookCopies.put(copy.getId(), copy);
            item.addCopy(copy);
        }

        libraryItems.put(item.getId(), item);

        System.out.printf("Added %s copies of %s %n", copies, title);
        return item;
    }

    public Member addMember(String name) {
        Member member = new Member(name);
        members.put(member.getId(), member);
        System.out.println("member successfully added");
        return member;
    }

    public void checkOut(String memberId, String copyId) {
        Member member = members.get(memberId);
        BookCopy copy = bookCopies.get(copyId);

        if (member != null && copy != null) {
            copy.checkout(copy, member);
        } else {
            System.out.println("Invalid data");
        }
    }

    public void returnItem(String copyId) {
        BookCopy copy = bookCopies.get(copyId);

        if (copy == null) {
            System.out.println("Invalid id");
            return;
        }

        copy.returnItem(copy);
    }

    public List<LibraryItem> search(String query) {
        return searchStrategy.search(query, new ArrayList<>(libraryItems.values()));
    }

    public List<LibraryItem> search(String query, SearchStrategy userSearchStrategy) {
        return userSearchStrategy.search(query, new ArrayList<>(libraryItems.values()));
    }

    public void printCatalog() {
        for (LibraryItem item : libraryItems.values()) {
            System.out.printf("Id:%s , Title:%s , Copies Available:%s %n", item.getId(), item.getTitle(), item.getCopies().size());
        }
    }
}


public class Main {
    static void main(String[] args) {
        LibraryService libraryService = LibraryService.getInstance();
    }
}