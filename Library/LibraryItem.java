package Library;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class LibraryItem {
    private String id;
    private String title;
    private String author;
    private String ISBN;
    private String publisher;
    private int numberOfCopies;
    private Status status;

    public LibraryItem(String id, String title, String author, String ISBN, String publisher, int numberOfCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.numberOfCopies = numberOfCopies;
        this.status = Status.AVAILABLE;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public Status getStatus() {
        return status;
    }

    // Setters
    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Abstract method
    public abstract String getItemType();

    public static List<LibraryItem> readFromFile(String fileName) throws IOException {
        List<LibraryItem> items = new ArrayList<>();
        List<String> lines = FileUtils.readFile(fileName);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 7) {
                String itemType = parts[6];
                if ("Book".equalsIgnoreCase(itemType)) {
                    items.add(new Book(parts[0], parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5])));
                } else if ("Periodical".equalsIgnoreCase(itemType)) {
                    items.add(new Periodical(parts[0], parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5])));
                }
            }
        }
        return items;
    }

    public static void writeToFile(String fileName, List<LibraryItem> items) throws IOException {
        List<String> lines = new ArrayList<>();
        for (LibraryItem item : items) {
            lines.add(item.getId() + "," + item.getTitle() + "," + item.getAuthor() + "," + item.getISBN() + "," + item.getPublisher() + "," + item.getNumberOfCopies() + "," + item.getItemType());
        }
        FileUtils.writeFile(fileName, lines);
    }
}
