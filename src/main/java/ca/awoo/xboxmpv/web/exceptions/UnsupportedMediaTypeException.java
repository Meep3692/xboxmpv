package ca.awoo.xboxmpv.web.exceptions;

import ca.awoo.xboxmpv.web.WebException;

/**
 * 415 Unsupported Media Type
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/415">415 Unsupported Media Type - HTTP | MDN</a>
 */
public class UnsupportedMediaTypeException extends WebException{

    /**
     * Create a UnsupportedMediaTypeException with no message and no cause
     */
    public UnsupportedMediaTypeException() {
        super(415);
    }

    /**
     * Create a UnsupportedMediaTypeException with a message and no cause
     * @param message The message to display
     */
    public UnsupportedMediaTypeException(String message){
        super(415, message);
    }

    /**
     * Create a UnsupportedMediaTypeException with a cause and no message
     * @param cause The exception that was caught that caused this exception
     */
    public UnsupportedMediaTypeException(Throwable cause){
        super(415, cause);
    }

    /**
     * Create a UnsupportedMediaTypeException with a message and a cause
     * @param message The message to display
     * @param cause The exception that was caught that caused this exception
     */
    public UnsupportedMediaTypeException(String message, Throwable cause){
        super(415, message, cause);
    }
    
}
