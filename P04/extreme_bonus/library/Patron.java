package library;

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
