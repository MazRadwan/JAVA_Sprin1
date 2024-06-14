package Library;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class LibraryMenu {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("Welcome to the Library Management System");
            System.out.println("1. Add Library Item");
            System.out.println("2. Edit Library Item");
            System.out.println("3. Delete Library Item");
            System.out.println("4. Borrow Library Item");
            System.out.println("5. Return Library Item");
            System.out.println("6. Search Library Items");
            System.out.println("7. Display All Library Items");
            System.out.println("8. Exit");
            System.out.print("Enter your choice (1-8): ");
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
                    searchLibraryItems();
                    break;
                case 7:
                    displayAllLibraryItems();
                    break;
                case 8:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        } while (choice != 8);

        scanner.close();
    }

    private static void addLibraryItem() {
        System.out.println("Enter details of the new library item:");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.print("Enter publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Enter number of copies: ");
        int numberOfCopies = scanner.nextInt();
        scanner.nextLine();  // Consume newline
    
        System.out.println("Select item type:");
        System.out.println("1. Book");
        System.out.println("2. Periodical");
        int itemType = scanner.nextInt();
        scanner.nextLine();  // Consume newline
    
        System.out.println("Select book type:");
        for (BookType bt : BookType.values()) {
            System.out.println(bt.ordinal() + 1 + ". " + bt);
        }
        int bookTypeIndex = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        BookType bookType = BookType.values()[bookTypeIndex - 1];
    
        int newId = getNewId();
        Status status = Status.AVAILABLE; // Set default status to AVAILABLE
        if (itemType == 1) {
            library.addItem(new Book(String.valueOf(newId), title, author, ISBN, publisher, numberOfCopies, status, bookType));
        } else if (itemType == 2) {
            library.addItem(new Periodical(String.valueOf(newId), title, author, ISBN, publisher, numberOfCopies, status, bookType));
        }
    }
    


    private static void editLibraryItem() {
        System.out.print("Enter ID of the item to edit: ");
        String itemId = scanner.nextLine();
        LibraryItem item = library.findItemById(itemId);
        if (item != null) {
            System.out.println("Editing " + item.getTitle());
            System.out.print("Enter new title: ");
            item.setTitle(scanner.nextLine());
            System.out.print("Enter new author: ");
            item.setAuthor(scanner.nextLine());
            System.out.print("Enter new ISBN: ");
            item.setISBN(scanner.nextLine());
            System.out.print("Enter new publisher: ");
            item.setPublisher(scanner.nextLine());
            System.out.print("Enter new number of copies: ");
            item.setNumberOfCopies(scanner.nextInt());
            scanner.nextLine();  // Consume newline
            library.saveItems();
            System.out.println("Item edited successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    private static void deleteLibraryItem() {
        System.out.print("Enter ID of the item to delete: ");
        String itemId = scanner.nextLine();
        library.removeItem(itemId);
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

    private static void searchLibraryItems() {
        System.out.println("Search by:");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. ISBN");
        int searchChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.print("Enter search term: ");
        String searchTerm = scanner.nextLine();

        List<LibraryItem> results = new ArrayList<>();
        switch (searchChoice) {
            case 1:
                results = library.searchItemsByTitle(searchTerm);
                break;
            case 2:
                results = library.searchItemsByAuthor(searchTerm);
                break;
            case 3:
                results = library.searchItemsByISBN(searchTerm);
                break;
            default:
                System.out.println("Invalid search choice.");
                return;
        }

        if (results.isEmpty()) {
            System.out.println();
            System.out.println("No items found.");
        } else {
            System.out.println();
            System.out.println();
            System.out.println("Search Results:");
            for (LibraryItem item : results) {
                System.out.println(item.getId() + ": " + item.getTitle() + " by " + item.getAuthor() + " (" + item.getItemType() + "), Status: " + item.getStatus() + ", Book Type: " + item.getBookType());
            }
            System.out.println();
            System.out.println();
        }
    }

    private static void displayAllLibraryItems() {
        System.out.println();
        System.out.println();
        System.out.println("Library Items:");
        for (LibraryItem item : library.getItems()) {
            System.out.println(item.getId() + ": " + item.getTitle() + " by " + item.getAuthor() + " (" + item.getItemType() + "), Status: " + item.getStatus() + ", Book Type: " + item.getBookType());
        }
        System.out.println(); // First line break
        System.out.println(); // Second line break
    }

    private static int getNewId() {
        int maxId = 0;
        for (LibraryItem item : library.getItems()) {
            int itemId = Integer.parseInt(item.getId());
            if (itemId > maxId) {
                maxId = itemId;
            }
        }
        return maxId + 1;
    }
}
