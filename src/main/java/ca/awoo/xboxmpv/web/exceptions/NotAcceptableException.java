package ca.awoo.xboxmpv.web.exceptions;

import ca.awoo.xboxmpv.web.WebException;

/**
 * 406 Not Acceptable
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/406">406 Not Acceptable - HTTP | MDN</a>
 */
public class NotAcceptableException extends WebException{
    /**
     * Create a NotAcceptableException with no message and no cause
     */
    public NotAcceptableException() {
        super(406);
    }

    /**
     * Create a NotAcceptableException with a message and no cause
     * @param message The message to display
     */
    public NotAcceptableException(String message){
        super(406, message);
    }

    /**
     * Create a NotAcceptableException with a cause and no message
     * @param cause The exception that was caught that caused this exception
     */
    public NotAcceptableException(Throwable cause){
        super(406, cause);
    }

    /**
     * Create a NotAcceptableException with a message and a cause
     * @param message The message to display
     * @param cause The exception that was caught that caused this exception
     */
    public NotAcceptableException(String message, Throwable cause){
        super(406, message, cause);
    }
}
