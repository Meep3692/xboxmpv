package ca.awoo.xboxmpv.web;

public class WebException extends Exception {
    public final int responseCode;

    public WebException(int responseCode){
        this.responseCode = responseCode;
    }

    public WebException(int responseCode, String message){
        super(message);
        this.responseCode = responseCode;
    }

    public WebException(int responseCode, Throwable cause){
        super(cause);
        this.responseCode = responseCode;
    }

    public WebException(int responseCode, String message, Throwable cause){
        super(message, cause);
        this.responseCode = responseCode;
    }
}
