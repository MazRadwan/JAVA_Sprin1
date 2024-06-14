package Library;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<LibraryItem> items;
    private List<Author> authors;
    private List<Patron> patrons;

    public Library() {
        this.items = new ArrayList<>();
        this.authors = new ArrayList<>();
        this.patrons = new ArrayList<>();
    }

    // Item management
    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public void editItem(String id, LibraryItem newItem) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                items.set(i, newItem);
                return;
            }
        }
    }

    public void deleteItem(String id) {
        items.removeIf(item -> item.getId().equals(id));
    }

    public List<LibraryItem> getItems() {
        return items;
    }

    // Author management
    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void editAuthor(String name, Author newAuthor) {
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getName().equals(name)) {
                authors.set(i, newAuthor);
                return;
            }
        }
    }

    public void deleteAuthor(String name) {
        authors.removeIf(author -> author.getName().equals(name));
    }

    // Patron management
    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public void editPatron(String name, Patron newPatron) {
        for (int i = 0; i < patrons.size(); i++) {
            if (patrons.get(i).getName().equals(name)) {
                patrons.set(i, newPatron);
                return;
            }
        }
    }

    public void deletePatron(String name) {
        patrons.removeIf(patron -> patron.getName().equals(name));
    }

    // Borrowing and returning items
    public void borrowItem(String patronName, String itemId) {
        Patron patron = findPatronByName(patronName);
        LibraryItem item = findItemById(itemId);
        if (patron != null && item != null) {
            patron.borrowItem(item);
        }
    }

    public void returnItem(String patronName, String itemId) {
        Patron patron = findPatronByName(patronName);
        LibraryItem item = findItemById(itemId);
        if (patron != null && item != null) {
            patron.returnItem(item);
        }
    }

    public Patron findPatronByName(String name) {
        for (Patron patron : patrons) {
            if (patron.getName().equals(name)) {
                return patron;
            }
        }
        return null;
    }

    public LibraryItem findItemById(String id) {
        for (LibraryItem item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    // Additional methods for searching items, authors, and patrons
    public List<LibraryItem> searchItemsByTitle(String title) {
        List<LibraryItem> results = new ArrayList<>();
        for (LibraryItem item : items) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                results.add(item);
            }
        }
        return results;
    }

    public List<LibraryItem> searchItemsByAuthor(String author) {
        List<LibraryItem> results = new ArrayList<>();
        for (LibraryItem item : items) {
            if (item.getAuthor().equalsIgnoreCase(author)) {
                results.add(item);
            }
        }
        return results;
    }

    public List<LibraryItem> searchItemsByISBN(String ISBN) {
        List<LibraryItem> results = new ArrayList<>();
        for (LibraryItem item : items) {
            if (item.getISBN().equals(ISBN)) {
                results.add(item);
            }
        }
        return results;
    }
}
