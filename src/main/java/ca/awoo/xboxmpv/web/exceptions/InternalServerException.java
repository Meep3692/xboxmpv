package ca.awoo.xboxmpv.web.exceptions;

import ca.awoo.xboxmpv.web.WebException;

/**
 * 500 Internal Server Error
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/500">500 Internal Server Error - HTTP | MDN</a>
 */
public class InternalServerException extends WebException{
    /**
     * Create a InternalServerException with no message and no cause
     */
    public InternalServerException() {
        super(500);
    }

    /**
     * Create a InternalServerException with a message and no cause
     * @param message The message to display
     */
    public InternalServerException(String message){
        super(500, message);
    }

    /**
     * Create a InternalServerException with a cause and no message
     * @param cause The exception that was caught that caused this exception
     */
    public InternalServerException(Throwable cause){
        super(500, cause);
    }

    /**
     * Create a InternalServerException with a message and a cause
     * @param message The message to display
     * @param cause The exception that was caught that caused this exception
     */
    public InternalServerException(String message, Throwable cause){
        super(500, message, cause);
    }
}
