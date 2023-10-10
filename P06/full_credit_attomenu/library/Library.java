package library;

// Copyright 2023 George F. Rice <https://github.com/prof-rice>
// This file is part of the Library Management System and is licensed
// under the terms of the Gnu General Public License version 3 or
// (at your option) any later version, see <https://www.gnu.org/licenses/>.

import java.util.List;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

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
     * Creates a Library instance from a BufferedReader stream.
     *
     * See {@link #save(BufferedWriter)} for the expected data format.
     *
     * @param br     a stream positioned where {@link #save(BufferedWriter)} wrote
     * @since        1.2
     */
    public Library(BufferedReader br) throws IOException {
        this(br.readLine());
        int size = Integer.parseInt(br.readLine());
        while(size-- > 0) {
            if(br.readLine().equals("video")) 
                 publications.add(new Video(br));
            else publications.add(new Publication(br));
        }
    }
    /**
     * Saves all local fields to the BufferedWriter stream.
     *
     * The value of plain fields that are never null are written one per line. 
     * Lists are written first as the number of elements on a separate line,
     * followed by each element on separate lines (some elements may themselves
     * be multi-line).
     *
     * @param bw    the stream to which all fields will be written
     * @since      1.2
     */
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write("" + publications.size() + '\n');
        for(var p : publications) {
            bw.write(p instanceof Video ? "video\n" : "publication\n");
            p.save(bw);
        }
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
