package Library;


public class Book extends LibraryItem implements Borrowable {
    private BookType bookType;
    private Status status;

    public Book(String id, String title, String author, String ISBN, String publisher, int numberOfCopies, BookType bookType, Status status) {
        super(id, title, author, ISBN, publisher, numberOfCopies);
        this.bookType = bookType;
        this.status = status;
    }

    // Getters and setters
    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void borrowItem() {
        if (status == Status.AVAILABLE && getNumberOfCopies() > 0) {
            setNumberOfCopies(getNumberOfCopies() - 1);
            if (getNumberOfCopies() == 0) {
                status = Status.CHECKED_OUT;
            }
        } else {
            System.out.println("Item not available for borrowing.");
        }
    }

    @Override
    public void returnItem() {
        setNumberOfCopies(getNumberOfCopies() + 1);
        if (status == Status.CHECKED_OUT) {
            status = Status.AVAILABLE;
        }
    }

    @Override
    public String getItemType() {
        return "Book - " + bookType.toString();
    }
}
