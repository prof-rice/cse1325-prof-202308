package mdi;

import library.Library;
import library.Publication;
import library.Video;

public class LibraryManager {
    public static void main(String[] args) {
        Library library = new Library("The Library at Alexandria (Texas)");
        library.addPublication(new Publication("The Cat in the Hat", "Dr. Suess", 1957));
        library.addPublication(new Publication("The Firm", "John Grisham", 1992));
        library.addPublication(new Publication("Foundation", "Isaac Asimov", 1951));
        library.addPublication(new Video("Citizen Kane", "Orson Welles", 1941, 119));
        library.addPublication(new Video("Star Wars", "George Lucas", 1977, 121));
        library.addPublication(new Video("七人の侍 (Seven Samurai)", "Akira Kurosawa", 1954, 207));
        System.out.println(library);
        int selection = Integer.parseInt(System.console().readLine("\nWhich publication to check out? "));
        String patron = System.console().readLine("Who are you? ");
        library.checkOut(selection, patron);
        System.out.println(library);
    }
}
