package library;

import java.util.ArrayList;

/**
 * Models a library containing various publications.
 *
 * @author             Professor George F. Rice
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */
public class Library {
    /**
     * Creates a Library instance.
     *
     * @param name         the name of the library
     * @since              1.0
     */
    public Library(String name) {
        this.name = name;
        this.publications = new ArrayList<>();
        this.patrons = new ArrayList<>();
    }
    /**
     * Adds a publication to this library instance.
     *
     * @param publication  the publication to add to the library
     * @since              1.0
     */
    public void addPublication(Publication publication) {
        publications.add(publication);
    }
    /**
     * Adds a patron to this library instance.
     *
     * @param patron  the patron to add to the library
     * @since              1.0
     */
    public void addPatron(Patron patron) {
        patrons.add(patron);
    }
    /**
     * Lists all patrons in this library instance.
     *
     * The index shown in the resulting String may be used 
     * to select a patron using the checkOut() method.
     *
     * @returns     the string representation of all library patrons
     * @since       1.0
     */
    public String patronMenu() {
       StringBuilder sb = new StringBuilder("Patrons\n\n");
        for(int i=0; i<patrons.size(); ++i)
            sb.append("" + i + ") " + patrons.get(i).toString() + "\n");
        return sb.toString();
    }
    /**
     * Checks out a publication from this library instance.
     *
     * The due date will be set to 2 weeks from the current date.
     *
     * @param publicationIndex  the index as shown by toString()
     * @param patronIndex       the index as shown by patronMenu()
     * @since                   1.0
     */
    public void checkOut(int publicationIndex, int patronIndex) {
        try {
            publications.get(publicationIndex).checkOut(patrons.get(patronIndex));
        } catch(Exception e) {
            System.err.println("\nUnable to check out publication #" + publicationIndex 
                + ": " + e.getMessage() + "\n");
        }
    }
    /**
     * Lists all publications in this library instance.
     *
     * The index shown in the resulting String may be used 
     * to check out a publication using the checkOut() method.
     *
     * @returns     the string representation of all library publications
     * @since       1.0
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + "\n\n");
        for(int i=0; i<publications.size(); ++i)
            sb.append("" + i + ") " + publications.get(i).toString() + "\n");
        return sb.toString();
    }
    private String name;
    private ArrayList<Publication> publications;
    private ArrayList<Patron> patrons;
}
