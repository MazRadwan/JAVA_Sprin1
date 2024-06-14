package Library;

import java.util.ArrayList;
import java.util.List;

public class Patron {
    private String name;
    private String address;
    private String phoneNumber;
    private List<LibraryItem> borrowedItems;

    public Patron(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.borrowedItems = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<LibraryItem> getBorrowedItems() {
        return borrowedItems;
    }

    public void setBorrowedItems(List<LibraryItem> borrowedItems) {
        this.borrowedItems = borrowedItems;
    }

    public void borrowItem(LibraryItem item) {
        if (item instanceof Borrowable) {
            ((Borrowable) item).borrowItem();
            borrowedItems.add(item);
        } else {
            System.out.println("This item cannot be borrowed.");
        }
    }

    public void returnItem(LibraryItem item) {
        if (item instanceof Borrowable) {
            ((Borrowable) item).returnItem();
            borrowedItems.remove(item);
        } else {
            System.out.println("This item cannot be returned.");
        }
    }
}
