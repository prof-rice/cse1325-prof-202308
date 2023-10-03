package library;

import java.util.List;
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
     * Reports an attempt to check in or out a Publication that is unknown to the Library.
     *
     * @since         1.1
     */
    public class NotInLibraryException extends Exception {
    }
    /**
     * Creates a Library instance.
     *
     * @param name         the name of the library
     * @since              1.0
     */
    public Library(String name) {
        this.name = name;
        this.publications = new ArrayList<>();
    }
    /**
     * Gets the name of this library instance.
     *
     * @returns the name of the library
     * @since              1.1
     */
    public String getName() {
        return name;
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
     * Provides a list of publications that are not currently checked out.
     *
     * @returns            List of all publications not checked out
     * @since              1.0
     */
    public List<Publication> listAvailable() {
        ArrayList<Publication> available = new ArrayList<>();
        for(var p : publications) {
            if(!p.isCheckedOut()) available.add(p);
        }
        return available;
    }
   /**
     * Checks out a publication from this library instance.
     *
     * The due date will be set to 2 weeks from the current date.
     *
     * @param publication  the publication to be checked out
     * @param patron       identification of the borrower
     * @since              1.0
     */
    public void checkOut(Publication publication, String patron) 
            throws Publication.AlreadyCheckedOutException, NotInLibraryException {
        if(!publications.contains(publication)) throw new NotInLibraryException();
        publication.checkOut(patron);
    }
     /**
     * Provides a list of publications that are currently checked in.
     *
     * @returns            List of all publications checked out
     * @since              1.0
     */
    public List<Publication> listUnavailable() {
        ArrayList<Publication> unavailable = new ArrayList<>();
        for(var p : publications) 
            if(p.isCheckedOut()) unavailable.add(p);
        return unavailable;
    }
     /**
     * Checks out a publication from this library instance.
     *
     * The due date will be set to 2 weeks from the current date.
     *
     * @param publication  the publication to be checked out
     * @param patron       identification of the borrower
     * @since              1.0
     */
    public void checkIn(Publication publication) 
            throws Publication.AlreadyCheckedInException, NotInLibraryException {
        if(!publications.contains(publication)) throw new NotInLibraryException();
        publication.checkIn();
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
}
