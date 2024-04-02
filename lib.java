import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book{
    public String title;
    public String author;
    public int totalCopies;
    public int availableCopies;

    public Book(String title, String author, int totalCopies) {
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void incrementAvailableCopies() {
        availableCopies++;
    }

    public void decrementAvailableCopies() {
        availableCopies--;
    }
}

class Library {
    public List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("Books in the library:");
            for (Book book : books) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Total copies: " + book.getTotalCopies());
                System.out.println("Available copies: " + book.getAvailableCopies());
                System.out.println();
            }
        }
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

public class lib {
    public static Scanner scanner = new Scanner(System.in);
    public static Library library = new Library();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nWelcome to Library Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    issueBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void addBook() {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        System.out.print("Enter the author of the book: ");
        String author = scanner.nextLine();
        System.out.print("Enter the total number of copies: ");
        int totalCopies = scanner.nextInt();

        Book book = new Book(title, author, totalCopies);
        library.addBook(book);
        System.out.println("Book added successfully.");
    }

    public static void viewBooks() {
        library.displayBooks();
    }

    public static void issueBook() {
        System.out.print("Enter the title of the book to issue: ");
        String title = scanner.nextLine();

        Book book = library.findBookByTitle(title);
        if (book != null) {
            if (book.getAvailableCopies() > 0) {
                book.decrementAvailableCopies();
                System.out.println("Book issued successfully.");
            } else {
                System.out.println("No available copies of the book.");
            }
        } else {
            System.out.println("Book not found in the library.");
        }
    }

    public static void returnBook() {
        System.out.print("Enter the title of the book to return: ");
        String title = scanner.nextLine();

        Book book = library.findBookByTitle(title);
        if (book != null) {
            if (book.getAvailableCopies() < book.getTotalCopies()) {
                book.incrementAvailableCopies();
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("All copies of the book are already available.");
            }
        } else {
            System.out.println("Book not found in the library.");
        }
    }
}

