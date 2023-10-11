package library;

// Copyright 2023 George F. Rice <https://github.com/prof-rice>
// This file is part of the Library Management System and is licensed
// under the terms of the Gnu General Public License version 3 or
// (at your option) any later version, see <https://www.gnu.org/licenses/>.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * A person authorized to check out a library resource.
 *
 * @author             Professor George F. Rice
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */
public class Patron {
    /**
     * Creates a Patron instance.
     *
     * @param name     the name on the patron's ID card
     * @param email    the email address reported by the patron and verified by the library staff
     * @since              1.0
     */
    public Patron(String name, String email) {
        this.name = name;
        this.email = email;
    }
    /**
     * Creates a Patron instance from a BufferedReader stream.
     *
     * See {@link #save(BufferedWriter)} for the expected data format.
     *
     * @param br     a stream positioned where {@link #save(BufferedWriter)} wrote
     * @since        1.2
     */
    public Patron(BufferedReader br) throws IOException {
        this(br.readLine(), br.readLine());
    }
    /**
     * Saves all inherited and local fields to the BufferedWriter stream.
     *
     * The name and email fields are written on separate lines to the stream.
     *
     * @param bw    the stream to which all fields will be written
     * @since      1.2
     */
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(email + '\n');
    }
 
    /**
     * Formats the fields of the patron in human-readable form.
     *
     * @returns     the string representation of the patron
     * @since       1.0
     */

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
    private String name;
    private String email;
}
