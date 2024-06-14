package Library;

import java.util.Scanner;

public class LibraryMenu {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadData();
        int choice;

        do {
            System.out.println("Welcome to the Library Management System");
            System.out.println("1. Add Library Item");
            System.out.println("2. Edit Library Item");
            System.out.println("3. Delete Library Item");
            System.out.println("4. Borrow Library Item");
            System.out.println("5. Return Library Item");
            System.out.println("6. Display All Library Items");
            System.out.println("7. Exit");
            System.out.print("Enter your choice (1-7): ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addLibraryItem();
                    break;
                case 2:
                    editLibraryItem();
                    break;
                case 3:
                    deleteLibraryItem();
                    break;
                case 4:
                    borrowLibraryItem();
                    break;
                case 5:
                    returnLibraryItem();
                    break;
                case 6:
                    displayAllLibraryItems();
                    break;
                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        } while (choice != 7);

        scanner.close();
    }

    private static void loadData() {
        // Add authors
        Author author1 = new Author("John Doe", "01-01-1970");
        Author author2 = new Author("Jane Smith", "02-02-1980");
        library.addAuthor(author1);
        library.addAuthor(author2);

        // Add items
        Book book1 = new Book("1", "Book One", "John Doe", "1234567890", "Publisher One", 5, BookType.PRINTED, Status.AVAILABLE);
        Book book2 = new Book("2", "Book Two", "Jane Smith", "0987654321", "Publisher Two", 3, BookType.ELECTRONIC, Status.AVAILABLE);
        Periodical periodical1 = new Periodical("3", "Periodical One", "John Doe", "1122334455", "Publisher Three", 7, PeriodicalType.PRINTED);
        library.addItem(book1);
        library.addItem(book2);
        library.addItem(periodical1);

        // Add patrons
        Patron patron1 = new Patron("Alice", "123 Main St", "555-1234");
        Patron patron2 = new Patron("Bob", "456 Elm St", "555-5678");
        library.addPatron(patron1);
        library.addPatron(patron2);
    }

    private static void addLibraryItem() {
        // Generate new ID automatically
        String newId = generateNewId();
        System.out.println("Generated ID for new item: " + newId);
        
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Enter number of copies: ");
        int numberOfCopies = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.println("Select type of item:");
        System.out.println("1. Book");
        System.out.println("2. Periodical");
        int typeChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (typeChoice == 1) {
            System.out.println("Select type of book:");
            System.out.println("1. Printed");
            System.out.println("2. Electronic");
            System.out.println("3. Audio");
            int bookTypeChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            BookType bookType = BookType.PRINTED;
            if (bookTypeChoice == 2) {
                bookType = BookType.ELECTRONIC;
            } else if (bookTypeChoice == 3) {
                bookType = BookType.AUDIO;
            }

            Book book = new Book(newId, title, author, isbn, publisher, numberOfCopies, bookType, Status.AVAILABLE);
            library.addItem(book);
            System.out.println("Book added successfully.");
        } else if (typeChoice == 2) {
            System.out.println("Select type of periodical:");
            System.out.println("1. Printed");
            System.out.println("2. Electronic");
            int periodicalTypeChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            PeriodicalType periodicalType = PeriodicalType.PRINTED;
            if (periodicalTypeChoice == 2) {
                periodicalType = PeriodicalType.ELECTRONIC;
            }

            Periodical periodical = new Periodical(newId, title, author, isbn, publisher, numberOfCopies, periodicalType);
            library.addItem(periodical);
            System.out.println("Periodical added successfully.");
        } else {
            System.out.println("Invalid item type choice.");
        }
    }

    private static String generateNewId() {
        int maxId = 0;
        for (LibraryItem item : library.getItems()) {
            int itemId = Integer.parseInt(item.getId());
            if (itemId > maxId) {
                maxId = itemId;
            }
        }
        return String.valueOf(maxId + 1);
    }

    private static void editLibraryItem() {
        System.out.print("Enter ID of the item to edit: ");
        String id = scanner.nextLine();

        LibraryItem existingItem = library.findItemById(id);
        if (existingItem != null) {
            System.out.print("Enter new title: ");
            String title = scanner.nextLine();
            System.out.print("Enter new author: ");
            String author = scanner.nextLine();
            System.out.print("Enter new ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Enter new publisher: ");
            String publisher = scanner.nextLine();
            System.out.print("Enter new number of copies: ");
            int numberOfCopies = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (existingItem instanceof Book) {
                Book book = (Book) existingItem;
                System.out.println("Select new type of book:");
                System.out.println("1. Printed");
                System.out.println("2. Electronic");
                System.out.println("3. Audio");
                int bookTypeChoice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                BookType bookType = BookType.PRINTED;
                if (bookTypeChoice == 2) {
                    bookType = BookType.ELECTRONIC;
                } else if (bookTypeChoice == 3) {
                    bookType = BookType.AUDIO;
                }

                Book updatedBook = new Book(id, title, author, isbn, publisher, numberOfCopies, bookType, book.getStatus());
                library.editItem(id, updatedBook);
                System.out.println("Book updated successfully.");
            } else if (existingItem instanceof Periodical) {
                System.out.println("Select new type of periodical:");
                System.out.println("1. Printed");
                System.out.println("2. Electronic");
                int periodicalTypeChoice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                PeriodicalType periodicalType = PeriodicalType.PRINTED;
                if (periodicalTypeChoice == 2) {
                    periodicalType = PeriodicalType.ELECTRONIC;
                }

                Periodical updatedPeriodical = new Periodical(id, title, author, isbn, publisher, numberOfCopies, periodicalType);
                library.editItem(id, updatedPeriodical);
                System.out.println("Periodical updated successfully.");
            }
        } else {
            System.out.println("Item with the specified ID not found.");
        }
    }

    private static void deleteLibraryItem() {
        System.out.print("Enter ID of the item to delete: ");
        String id = scanner.nextLine();

        library.deleteItem(id);
        System.out.println("Item deleted successfully.");
    }

    private static void borrowLibraryItem() {
        System.out.print("Enter name of the patron: ");
        String patronName = scanner.nextLine();
        System.out.print("Enter ID of the item to borrow: ");
        String itemId = scanner.nextLine();

        library.borrowItem(patronName, itemId);
        System.out.println("Item borrowed successfully.");
    }

    private static void returnLibraryItem() {
        System.out.print("Enter name of the patron: ");
        String patronName = scanner.nextLine();
        System.out.print("Enter ID of the item to return: ");
        String itemId = scanner.nextLine();

        library.returnItem(patronName, itemId);
        System.out.println("Item returned successfully.");
    }

    private static void displayAllLibraryItems() {
        System.out.println("Library Items:");
        for (LibraryItem item : library.getItems()) {
            System.out.println("id: " + item.getId() + ": " + item.getTitle() + " by " + item.getAuthor() + " (" + item.getItemType() + ")");
        }
    }
}

