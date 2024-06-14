package Library;

public class Periodical extends LibraryItem {
    private PeriodicalType periodicalType;

    public Periodical(String id, String title, String author, String ISBN, String publisher, int numberOfCopies, PeriodicalType periodicalType) {
        super(id, title, author, ISBN, publisher, numberOfCopies);
        this.periodicalType = periodicalType;
    }

    // Getters and setters
    public PeriodicalType getPeriodicalType() {
        return periodicalType;
    }

    public void setPeriodicalType(PeriodicalType periodicalType) {
        this.periodicalType = periodicalType;
    }

    @Override
    public String getItemType() {
        return "Periodical - " + periodicalType.toString();
    }
}
