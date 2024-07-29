package ca.awoo.xboxmpv.web;

public interface WebHandler {
    public void handle(WebContext context) throws WebException;
}
