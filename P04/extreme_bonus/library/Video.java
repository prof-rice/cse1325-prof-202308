package library;

import java.time.Duration;

/**
 * A library video that can be checked out by a patron.
 *
 * @author             Professor George F. Rice
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */
public class Video extends Publication {
    /**
     * Thown when a Video runtime is invalid.
     *
     * The runtime is specified in minutes.
     *
     * @since              1.0
     */
    public class InvalidRuntime extends ArithmeticException {
        /**
         * Constructs an InvalidRuntime with no detail message.
         *
         * @since       1.0
         */
        public InvalidRuntime() {super();}
        /**
         * Constructs an InvalidRuntime with the specified detail message.
         *
         * @since       1.0
         */
        public InvalidRuntime(String message) {super(message);}
        /**
         * Constructs an InvalidRuntime with a custom message.
         *
         * @since       1.0
         */
        public InvalidRuntime(String title, int runtime) {
            super(title + " has invalid runtime " + runtime);
        }
    }
    /**
     * Creates a Video instance.
     *
     * The runtime is specified in minutes.
     *
     * @param title        the name of the library
     * @param author       the principal creator of this resource
     * @param copyright    the year in which this publication was released or registered
     * @param runtime      the number of minutes require to play the video at standard speed
     * @since              1.0
     */
    public Video(String title, String author, int copyright, int runtime) {
        super(title, author, copyright);
        if(runtime < 1) throw new InvalidRuntime(title, runtime);
        this.runtime = Duration.ofMinutes(runtime);
    }
    /**
     * Formats the fields of the publication in human-readable form.
     *
     * @returns     the string representation of the publication
     * @since       1.0
     */
    @Override
    public String toString() {
        return toStringBuilder("Video", ", runtime " + runtime.toMinutes() + " minutes");
    }
    Duration runtime;
}
