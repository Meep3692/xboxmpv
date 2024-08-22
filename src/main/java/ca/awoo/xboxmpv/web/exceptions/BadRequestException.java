package ca.awoo.xboxmpv.web.exceptions;

import ca.awoo.xboxmpv.web.WebException;

/**
 * 400 Bad Request
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400">400 Bad Request - HTTP | MDN</a>
 */
public class BadRequestException extends WebException {

    /**
     * Create a BadRequestException with no message and no cause
     */
    public BadRequestException() {
        super(400);
    }

    /**
     * Create a BadRequestException with a message and no cause
     * @param message The message to display
     */
    public BadRequestException(String message){
        super(400, message);
    }

    /**
     * Create a BadRequestException with a cause and no message
     * @param cause The exception that was caught that caused this exception
     */
    public BadRequestException(Throwable cause){
        super(400, cause);
    }

    /**
     * Create a BadRequestException with a message and a cause
     * @param message The message to display
     * @param cause The exception that was caught that caused this exception
     */
    public BadRequestException(String message, Throwable cause){
        super(400, message, cause);
    }
    
}
