class Book {
    String title;
    String author;
    String genre;
    String bookId;
    boolean available;
    Book next;
    Book prev;

    public Book(String title, String author, String genre, String bookId) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.available = true;
        this.next = null;
        this.prev = null;
    }
}

class LibraryManagementSystem {
    private Book head;
    private Book tail;
    private int count;


    public void addAtBeginning(String title, String author, String genre, String bookId) {
        Book newBook = new Book(title, author, genre, bookId);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
        count++;
    }

    public void addAtEnd(String title, String author, String genre, String bookId) {
        Book newBook = new Book(title, author, genre, bookId);
        if (head == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
        count++;
    }

    public void addAtPosition(int pos, String title, String author, String genre, String bookId) {
        if (pos == 1) {
            addAtBeginning(title, author, genre, bookId);
            return;
        }
        if (pos > count + 1) {
            System.out.println("Position out of bounds");
            return;
        }
        Book newBook = new Book(title, author, genre, bookId);
        Book current = head;
        for (int i = 1; i < pos - 1; i++) {
            current = current.next;
        }
        newBook.next = current.next;
        newBook.prev = current;
        if (current.next != null) {
            current.next.prev = newBook;
        } else {
            tail = newBook;
        }
        current.next = newBook;
        count++;
    }

    public void removeById(String bookId) {
        if (head == null) return;
        if (head.bookId.equals(bookId)) {
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
            count--;
            return;
        }
        Book current = head;
        while (current != null && !current.bookId.equals(bookId)) {
            current = current.next;
        }
        if (current == null) return;
        if (current == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        count--;
    }

    public Book searchByTitle(String title) {
        Book current = head;
        while (current != null) {
            if (current.title.equals(title)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public Book searchByAuthor(String author) {
        Book current = head;
        while (current != null) {
            if (current.author.equals(author)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void updateAvailability(String bookId, boolean available) {
        Book current = head;
        while (current != null) {
            if (current.bookId.equals(bookId)) {
                current.available = available;
                return;
            }
            current = current.next;
        }
    }

    public void displayForward() {
        Book current = head;
        while (current != null) {
            System.out.println("Title: " + current.title + ", Author: " + current.author +
                    ", Genre: " + current.genre + ", ID: " + current.bookId +
                    ", Available: " + current.available);
            current = current.next;
        }
    }

    public void displayBackward() {
        Book current = tail;
        while (current != null) {
            System.out.println("Title: " + current.title + ", Author: " + current.author +
                    ", Genre: " + current.genre + ", ID: " + current.bookId +
                    ", Available: " + current.available);
            current = current.prev;
        }
    }

    public int countBooks() {
        return count;
    }
}