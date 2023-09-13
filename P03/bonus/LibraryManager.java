class LibraryManager {
    public static void main(String[] args) {
        try {
            Library library = new Library("The Library at Alexandria (Texas)");
            library.addPublication(new Publication("The Cat in the Hat", "Dr. Suess", 1957));
            library.addPublication(new Publication("The Firm", "John Grisham", 1992));
            library.addPublication(new Publication("Foundation", "Isaac Asimov", 1951));
            library.addPatron(new Patron("Prof Rice", "george.rice@uta.edu"));
            System.out.println(library);
            int selection = Integer.parseInt(System.console().readLine("\nWhich book to check out? "));
            System.out.println(library.patronMenu());
            int patron = Integer.parseInt(System.console().readLine("\nWho are you? "));
            library.checkOut(selection, patron);
            System.out.println(library);
        } catch(Exception e) {
            System.err.println("Unable to check out a publication\n" + e);
        }

    }
}
