package ca.awoo.xboxmpv.web.exceptions;

import ca.awoo.xboxmpv.web.WebException;

public class UnsupportedMediaTypeException extends WebException{

    public UnsupportedMediaTypeException() {
        super(415);
    }

    public UnsupportedMediaTypeException(String message) {
        super(415, message);
    }

    public UnsupportedMediaTypeException(Throwable cause) {
        super(415, cause);
    }

    public UnsupportedMediaTypeException(String message, Throwable cause){
        super(415, message, cause);
    }
    
}
