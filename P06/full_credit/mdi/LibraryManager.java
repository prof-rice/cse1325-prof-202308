package mdi;

import library.Library;
import library.Publication;
import library.Video;

import java.io.Console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LibraryManager {
    public LibraryManager(Library library) {
        this.library = library;
    }
    public void listPublications() {
        System.out.println("=================\nLibrary Catalogue\n=================\n");
        System.out.println(library);
    }
    public void addPublication() {
        String title = console.readLine("Title: ");   if(title.isEmpty()) return;
        String author = console.readLine("Author: "); if(author.isEmpty()) return;
        int copyright = Integer.parseInt(console.readLine("Copyright: "));
        String runtime = console.readLine("Runtime (Enter if not video): ");
        Publication p = null;
        if(runtime.isEmpty()) {
            p = new Publication(title, author, copyright);
        } else {
            p = new Video(title, author, copyright, Integer.parseInt(runtime));
        }
        library.addPublication(p);
    }
    public void checkOutPublication() {
        System.out.println(library);
        int index = Integer.parseInt(console.readLine("Selection: "));
        String patron = console.readLine("Patron: ");
        library.checkOut(index, patron);
    }
    public void checkInPublication() {
        System.out.println(library);
        int index = Integer.parseInt(console.readLine("Selection: "));
        library.checkIn(index);
    }
    public void openLibrary() {
        String filename = console.readLine("Open filename: ");
        if(filename.isEmpty()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            library = new Library(br);
        } catch(IOException e) {
            System.err.println("#### Error: Cannot read " + filename + "\n" + e.getMessage());
        }
    }
    public void saveLibrary() {
        String filename = console.readLine("Save filename: ");
        if(filename.isEmpty()) return;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            library.save(bw);
        } catch(IOException e) {
            System.err.println("#### Error: Cannot write " + filename + "\n" + e.getMessage());
        }
    }
    public static void main(String[] args) {
        LibraryManager lm = new LibraryManager(new Library(name));
        while(true) {
            try {
                System.out.println(menu);
                int selection = Integer.parseInt(console.readLine("Selection: "));
                System.out.println();
                switch(selection) {
                    case -1 -> lm.testData();
                    case  0 -> System.exit(0);
                    case  1 -> lm.listPublications();
                    case  2 -> lm.addPublication();
                    case  3 -> lm.checkOutPublication();
                    case  4 -> lm.checkInPublication();
                    case  5 -> lm.openLibrary();
                    case  6 -> lm.saveLibrary();
                    default -> throw new RuntimeException("Invalid: " + selection);
                }
            } catch (Exception e) {
                System.err.println("#### Error - " + e.getMessage());
            }
        }
    }

    private static Console console = System.console();
    private static final String name = "The Library at Alexandria (Texas)";
    private static final String menu = "\n\n=========\nMain Menu\n=========\n\n" + name + "\n\n"
        + "0) Exit\n1) List\n2) Add\n3) Check out\n4) Check in\n5) Open\n6) Save\n\n";

    private Library library;
    
    public void testData() {
        library.addPublication(new Publication("The Cat in the Hat", "Dr. Suess", 1957));
        library.addPublication(new Publication("The Firm", "John Grisham", 1992));
        library.addPublication(new Publication("Foundation", "Isaac Asimov", 1951));
        library.addPublication(new Video("Citizen Kane", "Orson Welles", 1941, 119));
        library.addPublication(new Video("Star Wars", "George Lucas", 1977, 121));
        library.addPublication(new Video("七人の侍 (Seven Samurai)", "Akira Kurosawa", 1954, 207));
    }
}
