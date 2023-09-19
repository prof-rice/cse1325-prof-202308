class InvalidPageException extends RuntimeException {
    InvalidPageException(String book, int page) {
        super("Page " + page + " does not exist in '" + book + "'");
    }
}


class Book {
    void selectPage(int page) {
        if(page > pages)throw new InvalidPageException(title, page);
        else currentPage = page;
    }
    String title = "Three Little Pigs";
    int pages = 12;
    int currentPage;
}

public class Library {
    public static void main(String[] args) {
        Book book = new Book();
        try {
            book.selectPage(30);
        } catch (InvalidPageException e) {
            System.err.println(e.getMessage());
        }
    }
}
