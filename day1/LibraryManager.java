class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean isAvailable;
    Book prev;
    Book next;

    public Book(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
        this.prev = null;
        this.next = null;
    }
}

class Library {
    Book head = null;
    Book tail = null;

    public void addAtBeginning(String title, String author, String genre, int id, boolean available) {
        Book newBook = new Book(title, author, genre, id, available);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
    }

    public void addAtEnd(String title, String author, String genre, int id, boolean available) {
        Book newBook = new Book(title, author, genre, id, available);
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
    }

    public void addAtPosition(int position, String title, String author, String genre, int id, boolean available) {
        if (position <= 1 || head == null) {
            addAtBeginning(title, author, genre, id, available);
            return;
        }

        Book newBook = new Book(title, author, genre, id, available);
        Book current = head;
        int count = 1;

        while (current.next != null && count < position - 1) {
            current = current.next;
            count++;
        }

        newBook.next = current.next;
        newBook.prev = current;

        if (current.next != null) {
            current.next.prev = newBook;
        } else {
            tail = newBook;
        }

        current.next = newBook;
    }

    public void removeById(int id) {
        if (head == null) return;

        Book current = head;

        while (current != null && current.bookId != id) {
            current = current.next;
        }

        if (current == null) return;

        if (current == head) {
            head = current.next;
            if (head != null) head.prev = null;
            else tail = null;
        } else if (current == tail) {
            tail = current.prev;
            tail.next = null;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
    }

    public void searchByTitle(String title) {
        Book current = head;
        boolean found = false;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                printBook(current);
                found = true;
            }
            current = current.next;
        }
        if (!found) System.out.println("No book found with that title.");
    }

    public void searchByAuthor(String author) {
        Book current = head;
        boolean found = false;
        while (current != null) {
            if (current.author.equalsIgnoreCase(author)) {
                printBook(current);
                found = true;
            }
            current = current.next;
        }
        if (!found) System.out.println("No book found by that author.");
    }

    public void updateAvailability(int id, boolean status) {
        Book current = head;
        while (current != null) {
            if (current.bookId == id) {
                current.isAvailable = status;
                System.out.println("Availability updated.");
                return;
            }
            current = current.next;
        }
        System.out.println("Book not found.");
    }

    public void displayForward() {
        Book current = head;
        while (current != null) {
            printBook(current);
            current = current.next;
        }
    }

    public void displayReverse() {
        Book current = tail;
        while (current != null) {
            printBook(current);
            current = current.prev;
        }
    }

    public void countBooks() {
        int count = 0;
        Book current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        System.out.println("Total number of books: " + count);
    }

    private void printBook(Book book) {
        System.out.println("ID: " + book.bookId +
                ", Title: " + book.title +
                ", Author: " + book.author +
                ", Genre: " + book.genre +
                ", Available: " + (book.isAvailable ? "Yes" : "No"));
    }
}

public class LibraryManager {
    public static void main(String[] args) {
        Library library = new Library();

        library.addAtBeginning("The Hobbit", "J.R.R. Tolkien", "Fantasy", 101, true);
        library.addAtEnd("1984", "George Orwell", "Dystopian", 102, true);
        library.addAtEnd("To Kill a Mockingbird", "Harper Lee", "Classic", 103, false);
        library.addAtPosition(2, "Pride and Prejudice", "Jane Austen", "Romance", 104, true);

        System.out.println("Display books forward:");
        library.displayForward();

        System.out.println("\nDisplay books reverse:");
        library.displayReverse();

        System.out.println("\nSearch by title:");
        library.searchByTitle("1984");

        System.out.println("\nSearch by author:");
        library.searchByAuthor("Jane Austen");

        System.out.println("\nUpdate availability:");
        library.updateAvailability(103, true);

        System.out.println("\nRemove book with ID 102:");
        library.removeById(102);
        library.displayForward();

        library.countBooks();
    }
}
