package ca.awoo.xboxmpv.web;

public class EchoHandler implements WebHandler {

    @Override
    public void handle(WebContext context) throws WebException {
        context.setModel(context.readRequestBody());
    }
    
}
