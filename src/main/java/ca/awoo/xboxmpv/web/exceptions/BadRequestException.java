package ca.awoo.xboxmpv.web.exceptions;

import ca.awoo.xboxmpv.web.WebException;

public class BadRequestException extends WebException {

    public BadRequestException() {
        super(400);
    }

    public BadRequestException(String message){
        super(400, message);
    }

    public BadRequestException(Throwable cause){
        super(400, cause);
    }

    public BadRequestException(String message, Throwable cause){
        super(400, message, cause);
    }
    
}
