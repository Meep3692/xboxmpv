package ca.awoo.xboxmpv.web.exceptions;

import ca.awoo.xboxmpv.web.WebException;

/**
 * 405 Method Not Allowed
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/405">405 Method Not Allowed - HTTP | MDN</a>
 */
public class MethodNotAllowedException extends WebException {

    /**
     * Create a MethodNotAllowedException with no message and no cause
     */
    public MethodNotAllowedException() {
        super(405);
    }

    /**
     * Create a MethodNotAllowedException with a message and no cause
     * @param message The message to display
     */
    public MethodNotAllowedException(String message){
        super(405, message);
    }

    /**
     * Create a MethodNotAllowedException with a cause and no message
     * @param cause The exception that was caught that caused this exception
     */
    public MethodNotAllowedException(Throwable cause){
        super(405, cause);
    }

    /**
     * Create a MethodNotAllowedException with a message and a cause
     * @param message The message to display
     * @param cause The exception that was caught that caused this exception
     */
    public MethodNotAllowedException(String message, Throwable cause){
        super(405, message, cause);
    }
    
}
