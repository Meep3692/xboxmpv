package ca.awoo.xboxmpv.web.exceptions;

import ca.awoo.xboxmpv.web.WebException;

public class MethodNotAllowedException extends WebException {

    public MethodNotAllowedException() {
        super(405);
    }

    public MethodNotAllowedException(String message){
        super(405, message);
    }

    public MethodNotAllowedException(Throwable cause){
        super(405, cause);
    }

    public MethodNotAllowedException(String message, Throwable cause){
        super(405, message, cause);
    }
    
}
