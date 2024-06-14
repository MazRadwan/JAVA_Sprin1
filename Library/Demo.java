package Library;

public class Demo {
    public static void main(String[] args) {
        Library library = new Library();

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

        // Borrow items
        library.borrowItem("Alice", "1");
        library.borrowItem("Bob", "2");

        // Return items
        library.returnItem("Alice", "1");

        // Display borrowed items for patrons
        System.out.println("Items borrowed by Alice:");
        for (LibraryItem item : patron1.getBorrowedItems()) {
            System.out.println(item.getTitle());
        }

        System.out.println("Items borrowed by Bob:");
        for (LibraryItem item : patron2.getBorrowedItems()) {
            System.out.println(item.getTitle());
        }

        // Search for items by title
        System.out.println("Search results for 'Book One':");
        for (LibraryItem item : library.searchItemsByTitle("Book One")) {
            System.out.println(item.getTitle() + " by " + item.getAuthor());
        }
    }
}
