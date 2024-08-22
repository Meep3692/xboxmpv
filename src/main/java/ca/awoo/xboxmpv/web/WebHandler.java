package ca.awoo.xboxmpv.web;

/**
 * Basic unit of web handling. Used for endpoints and middleware.
 */
public interface WebHandler {
    public void handle(WebContext context) throws WebException;
}
