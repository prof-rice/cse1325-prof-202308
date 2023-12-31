package mdi;

import library.Library;
import library.Publication;
import library.Video;
import library.Patron;

import java.io.Console;

import java.io.BufferedReader;
import java.io.FileReader;
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
        int patron = Integer.parseInt(console.readLine(library.patronMenu() + "\n\nPatron: "));
        library.checkOut(index, patron);
    }
    public void checkInPublication() {
        System.out.println(library);
        int index = Integer.parseInt(console.readLine("Selection: "));
        library.checkIn(index);
    }
    public void listPatrons() {
        System.out.println("===============\nLibrary Patrons\n===============\n");
        System.out.println(library.patronMenu());
    }
    public void addPatron() {
        String name = console.readLine("Name: ");   if(name.isEmpty()) return;
        String email = console.readLine("Email: "); if(email.isEmpty()) return;
        library.addPatron(new Patron(name, email));
    }
    public void loadData() {
        String filename = console.readLine("File: ");   if(filename.isEmpty()) return;
        try (BufferedReader br = new BufferedReader(new FileReader((filename)))) {
            String name;
            while((name=br.readLine())!=null) {
                if(name.isEmpty()) break;
                if(name.equals("Publication")) {
                    library.addPublication(new Publication(br.readLine(), br.readLine(), 
                                           Integer.parseInt(br.readLine())));
                } else if(name.equals("Video")) {
                    library.addPublication(new Video(br.readLine(), br.readLine(), 
                                           Integer.parseInt(br.readLine()),
                                           Integer.parseInt(br.readLine())));                
                } else {
                    throw new IOException("Unable to load " + filename);
                }
            }
            while((name=br.readLine())!=null) {
                if(name.isEmpty()) break;
                library.addPatron(new Patron(name, br.readLine()));
            }
        } catch(IOException e) {
            e.printStackTrace();
            System.err.println("Error reading file " + filename + " - " + e);
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
                    case  0 -> System.exit(0);
                    case  1 -> lm.listPublications();
                    case  2 -> lm.addPublication();
                    case  3 -> lm.checkOutPublication();
                    case  4 -> lm.checkInPublication();
                    case  5 -> lm.listPatrons();
                    case  6 -> lm.addPatron();
                    case  7 -> lm.loadData();
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
        + "Publications\n1) List\n2) Add\n3) Check out\n4) Check in\n\n"
        + "Patrons\n5) List \n6) Add\n\n"
        + "Files\n7) Load file\n0) Exit\n\n";

    private Library library;
}
