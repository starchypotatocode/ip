package kai;

/**
 * KaiException is a custom Exception class
 * to enable easier passing of messages for debugging
 */
public class KaiException extends Exception {

    /**
     * Constructor for the exception with the given message
     * @param message the message in question
     */
    public KaiException(String message) {
        super(message);
    }
}
