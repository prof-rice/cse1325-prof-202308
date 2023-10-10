package library;

// Copyright 2023 George F. Rice <https://github.com/prof-rice>
// This file is part of the Library Management System and is licensed
// under the terms of the Gnu General Public License version 3 or
// (at your option) any later version, see <https://www.gnu.org/licenses/>.

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
     * Adds a publication to this library instance.
     *
     * @param publication  the publication to add to the library
     * @since              1.0
     */
    public void addPublication(Publication publication) {
        publications.add(publication);
    }
    /**
     * Checks out a publication from this library instance.
     *
     * The due date will be set to 2 weeks from the current date.
     *
     * @param publicationIndex  the index as shown by toString()
     * @param patron            identification of the borrower
     * @since                   1.0
     */
    public void checkOut(int publicationIndex, String patron) {
        try {
            publications.get(publicationIndex).checkOut(patron);
        } catch(Exception e) {
            System.err.println("\nUnable to check out publication #" + publicationIndex 
                + ": " + e.getMessage() + "\n");
        }
    }
    /**
     * Checks in a publication from this library instance.
     *
     * @param publicationIndex  the index as shown by toString()
     * @since                   1.1
     */
    public void checkIn(int publicationIndex) {
        try {
            publications.get(publicationIndex).checkIn();
        } catch(Exception e) {
            System.err.println("\nUnable to check in publication #" + publicationIndex 
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
}
