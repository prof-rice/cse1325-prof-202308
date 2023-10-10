package library;

// Copyright 2023 George F. Rice <https://github.com/prof-rice>
// This file is part of the Library Management System and is licensed
// under the terms of the Gnu General Public License version 3 or
// (at your option) any later version, see <https://www.gnu.org/licenses/>.

import java.time.LocalDate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * A library resource that can be checked out by a patron.
 *
 * @author             Professor George F. Rice
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */
public class Publication {
    public static final int LOAN_PERIOD = 14; // days
    
    /**
     * Creates a Publication instance.
     *
     * The year of copyright must be between 1900 and the present.
     *
     * @param title     the name of the library
     * @param author    the principal creator of this resource
     * @param copyright the year in which this publication was released or registered
     * @since              1.0
     */
    public Publication(String title, String author, int copyright) {
        if(copyright < 1900 || copyright > LocalDate.now().getYear())
            throw new IllegalArgumentException("Invalid copyright year : " + copyright);
        this.title = title;
        this.author = author;
        this.copyright = copyright;
    }
    /**
     * Creates a Publication instance from a BufferedReader stream.
     *
     * See {@link #save(BufferedWriter)} for the expected data format.
     *
     * @param br     a stream positioned where {@link #save(BufferedWriter)} wrote
     * @since        1.2
     */
    public Publication(BufferedReader br) throws IOException {
        this.title = br.readLine();
        this.author = br.readLine();
        this.copyright = Integer.parseInt(br.readLine());
        if(br.readLine().equals("checked in")) {
            this.loanedTo = null;
            this.dueDate = null;
        } else  {
            this.loanedTo = new Patron(br);
            this.dueDate  = LocalDate.of(Integer.parseInt(br.readLine()),
                                        Integer.parseInt(br.readLine()),
                                        Integer.parseInt(br.readLine()));
        }
    }
    /**
     * Saves all local fields to the BufferedWriter stream.
     *
     * The value of plain fields that are never null are written one per line. 
     * Fields which may be null are written as "null" (if null) or as "" 
     * followed by the value on the next line(s) otherwise. The values of 
     * LocalDate objects are written as 3 integers on separate lines - year, month, and date.
     *
     * @param bw    the stream to which all fields will be written
     * @since      1.2
     */
    public void save(BufferedWriter bw) throws IOException {
        bw.write(title + '\n');
        bw.write(author + '\n');
        bw.write("" + copyright + '\n');
        if (loanedTo == null) {
            bw.write("checked in\n");
        } else {
            bw.write("checked out\n");
            loanedTo.save(bw);
            bw.write("" + dueDate.getYear() + '\n' 
                        + dueDate.getMonthValue() + '\n'
                        + dueDate.getDayOfMonth() + '\n');
        }
    }
    /**
     * Notes the patron who borrowed this publication along with the due date
     *
     * The Patron is a string, with no data validation. This will require a lot
     * of discipline on the part of the libraries to make this field useful.
     *
     * @param patron    the identity of the person borrowing this publication
\     * @since              1.0
     */
    public void checkOut(Patron patron) {
        loanedTo = patron;
        dueDate = LocalDate.now().plusDays(LOAN_PERIOD);
    }
    /**
     * Notes that this publication has been returned
     *
     * @since              1.0
     */
    public void checkIn() {
        loanedTo = null;
        dueDate = null;
    }
    
    protected String toStringBuilder(String pre, String mid) {
        return pre + " \"" + title + "\" by " + author + ", copyright " + copyright
             + mid 
            + ((loanedTo != null) ? "\n     : loaned to " + loanedTo + " until " + dueDate
                                  : "");
    }
    /**
     * Formats the fields of the publication in human-readable form.
     *
     * @returns     the string representation of the publication
     * @since       1.0
     */
    @Override
    public String toString() {
        return toStringBuilder("Book", "");
    }
    private String title;
    private String author;
    private int copyright;
    private Patron loanedTo;
    private LocalDate dueDate;
}
