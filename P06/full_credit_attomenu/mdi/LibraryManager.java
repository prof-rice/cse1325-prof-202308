package mdi;

import attomenu.Menu;
import attomenu.MenuItem;

import library.Library;
import library.Publication;
import library.Video;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

import java.time.LocalDate;

public class LibraryManager {
    public static void main(String[] args) {
        new LibraryManager();
    }
    
    public LibraryManager() {
        library = new Library("The Library at Alexandria (Texas)");
        addTestData(library);
        status = new StringBuilder("Welcome to " + library.getName());
        Menu.SHOW_INT = false; // don't show integers unless no key is available
        
        ArrayList<String> help = loadHelp(helpFileName);
        
        fileMenu = new Menu("\n\n" + center("File Menu") + "\n" + center(library.getName()), 
            status, help.get(6),
            new MenuItem("Open Library", () -> onOpenLibrary(), 'o', help.get(7)),
            new MenuItem("Save Library", () -> onSaveLibrary(filename), 's', help.get(8)),
            new MenuItem("Save Library As...", () -> onSaveLibraryAs(), 'a', help.get(9))
        );
        mainMenu = new Menu("\n\n" + center("Main Menu") + "\n" + center(library.getName()), 
            status, help.get(0),
            new MenuItem("List Publications", () -> setStatus(library.toString()), 'l', help.get(1)),
            new MenuItem("Check Out", () -> onCheckOut(), 'o', help.get(2)),
            new MenuItem("Check In", () -> onCheckIn(), 'i', help.get(3)),
            new MenuItem("New Publication", () -> onNewPublication(), 'p', help.get(4)),
            new MenuItem("New Video", () -> onNewVideo(), 'v', help.get(5)),
            new MenuItem("File Submenu", () -> fileMenu.runOnce(), 'f', help.get(6))
        );
        mainMenu.run();
    }
    
    public void onCheckOut() {
        setStatus("Selection for check out cancelled");
        try {
            List<Publication> available = library.listAvailable();
            if(available.isEmpty()) {
                setStatus("No publications available for checkout");
                return;
            }
            int selection = Menu.select("Select a publication to check out", available.toArray());
            if(selection >= 0) {
                library.checkOut((Publication) available.get(selection), 
                                 getString("Patron's name: "));
                setStatus(available.get(selection).toString());
            }
        } catch(CancelException e) {
        } catch (Exception e) {
            setStatus("#### Unable to check out that publication\n" + e.getMessage());
        }
    }
    
    public void onCheckIn() {
        setStatus("Selection for check in cancelled");
        try {
            List<Publication> unavailable = library.listUnavailable();
            if(unavailable.isEmpty()) {
                setStatus("No publications available for checkin");
                return;
            }
            int selection = Menu.select("Select a publication to check in", unavailable.toArray());
            if(selection >= 0) {
                library.checkIn(unavailable.get(selection));
                setStatus(unavailable.get(selection).toString());
            }
        } catch (Exception e) {
            setStatus("#### Unable to check in that publication\n" + e.getMessage());
        }
    }
    
    public void onNewPublication() {
        setStatus("New publication entry cancelled");
        try {
            Publication p = new Publication(
                getString("Enter new Publication's title:  "),
                getString("Enter new Publication's author: "),
                getInt("Enter new Publication's copyright: ", 1, LocalDate.now().getYear())
            );
            library.addPublication(p);
            setStatus("Added " + p);
        } catch (CancelException e) {
        } catch (Exception e) {
            setStatus("#### Unable to create new Publication");
        }
    }
    
    public void onNewVideo() {
        setStatus("New video entry cancelled");
        try {
            Publication p = new Video(
                getString("Enter new Video's title:  "),
                getString("Enter new Video's author: "),
                getInt("Enter new Video's copyright: ", 1, LocalDate.now().getYear()),
                getInt("Enter new Video's runtime (in minutes): ", 1, Integer.MAX_VALUE)
            );
            library.addPublication(p);
            setStatus("Added " + p);
        } catch (CancelException e) {
        } catch (Exception e) {
            setStatus("#### Unable to create new Publication");
        }
    }
    public void onOpenLibrary() {
        File file = Menu.selectFile("Open Filename", 
            (filename != null) ? filename.getParentFile() : null, null);
        if(file == null) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            library = new Library(br);
            filename = file;
        } catch(IOException e) {
            System.err.println("#### Error: Cannot read " + filename + "\n" + e.getMessage());
        }
    }
    public void onSaveLibraryAs() {
        File file = Menu.selectFile("Save Filename", 
            (filename != null) ? filename.getParentFile() : null, null);
        if(file == null) return;
        onSaveLibrary(file);
    }
    public void onSaveLibrary(File file) {
        if(file == null) {
            onSaveLibraryAs();
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                library.save(bw);
                filename = file;
            } catch(IOException e) {
                System.err.println("#### Error: Cannot write " + filename + "\n" + e.getMessage());
            }
        }
    }

    // Get a String from the user
    private String getString(String prompt) throws CancelException {
        String s = System.console().readLine(prompt);
        if(s.isEmpty()) throw new CancelException();
        return s;
    }
    // Returns an int from the user between min and max.
    // Catches and reports exceptions, and retries until the user gets it right
    //   or types 'x' to cancel.
    // Throws a CancelException on a cancel.
    private int getInt(String prompt, int min, int max) throws CancelException {
        int result = 0;
        while(true) {
            try {
                result = Integer.parseInt(getString(prompt).trim());
                if(result < min || result > max) throw new RuntimeException();
                break;
            } catch (CancelException e) {
                throw e; // Pass the cancel to the caller
            } catch (NumberFormatException e) {
                System.err.println("#### That is not an int!");
            } catch (RuntimeException e) {
                System.err.println("#### " + result + " is not between " + min + " and " + max);
            } catch (Exception e) {
                System.err.println("#### Invalid input: " + e.getMessage());
            }
        }
        return result;
    }
    // This loads help text from filename into an ArrayList.
    // Different topics are separated by a single # line.
    public ArrayList<String> loadHelp(String filename) {
        ArrayList<String> help = new ArrayList<>();
        String line = "";
        StringBuilder topic = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while(true) {
                line = br.readLine();
                if(line == null) {                      // End of help text
                    if(topic.toString().trim().length() > 0) help.add(topic.toString());
                    break;
                } else if (line.trim().equals("#")) {   // End of a topic
                    help.add(topic.toString());
                    topic.delete(0, topic.length());
                } else {                                // Another line in the topic
                    topic.append("\n" + line);
                } 
            }
        } catch(Exception e) {
            System.err.println("Unable to load help from " + filename + ": " + e.getMessage());
        }
        return help;
    }
    
    // Center text on an 80 column display
    private static final String helpFileName = "help.txt";
    private static final int COLUMNS = 80;
    private static String center(String text) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<(COLUMNS - text.length())/2; ++i) sb.append(' ');
        return sb.toString() + text;
    }
    private class CancelException extends Exception {
    }
    private Library library;
    private Menu mainMenu;
    private Menu fileMenu;
    private File filename;
    
    private StringBuilder status;
    private void setStatus(String status) {
        this.status.replace(0, Integer.MAX_VALUE, status);
    }
    private void clearStatus() {
        setStatus("");
    }
    
    public void addTestData(Library library) {
        library.addPublication(new Publication("The Cat in the Hat", "Dr. Suess", 1957));
        library.addPublication(new Publication("The Firm", "John Grisham", 1992));
        library.addPublication(new Publication("Foundation", "Isaac Asimov", 1951));
        library.addPublication(new Video("Citizen Kane", "Orson Welles", 1941, 119));
        library.addPublication(new Video("Star Wars", "George Lucas", 1977, 121));
        library.addPublication(new Video("七人の侍 (Seven Samurai)", "Akira Kurosawa", 1954, 207));
    }

}
