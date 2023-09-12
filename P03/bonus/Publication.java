import java.time.LocalDate;

class Publication {
    public static final int LOAN_PERIOD = 14; // days
    public Publication(String title, String author, int copyright) {
        if(copyright < 1900 || copyright > LocalDate.now().getYear())
            throw new IllegalArgumentException("Invalid copyright year : " + copyright);
        this.title = title;
        this.author = author;
        this.copyright = copyright;
    }
    public void checkOut(Patron patron) {
        loanedTo = patron;
        dueDate = LocalDate.now().plusDays(LOAN_PERIOD);
    }
    public void checkIn() {
        loanedTo = null;
        dueDate = null;
    }
    @Override
    public String toString() {
        return "\"" + title + "\" by " + author + ", copyright " + copyright
            + ((loanedTo != null) ? "\n  : loaned to " + loanedTo + " until " + dueDate
                                  : "");
    }
    private String title;
    private String author;
    private int copyright;
    private Patron loanedTo;
    private LocalDate dueDate;
}