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
     * @since              1.0
     */
    public class InvalidRuntimeException extends ArithmeticException {
        /**
         * Constructs an InvalidRuntimeException with no detail message.
         *
         * @since              1.0
         */
        public InvalidRuntimeException() {super();}
        /**
         * Constructs an InvalidRuntimeException with with the specified detail message.
         *
         * @param message      the detailed message
         * @since              1.0
         */
        public InvalidRuntimeException(String message) {super(message);}
        /**
         * Constructs an InvalidRuntimeException with a custom message.
         *
         * @param title        the name of the library
         * @param runtime      the (invalid) number of minutes require to play the video at standard speed
         * @since              1.0
         */
        public InvalidRuntimeException(String title, int runtime) {
            super(title + " has invalid runtime " + runtime);
        }
    }

    /**
     * Creates a Video instance.
     *
     * The runtime is specified in minutes.
     *
     * @param title        the name of the video
     * @param author       the principal creator of this video, typically the producer
     * @param copyright    the year in which this video was released or registered
     * @param runtime      the number of minutes required to play the video at standard speed
     * @since              1.0
     * @throws             InvalidRuntimeException
     */
    public Video(String title, String author, int copyright, int runtime) {
        super(title, author, copyright);
        if(runtime < 1) throw new InvalidRuntimeException(title, runtime);
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
