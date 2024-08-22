package ca.awoo.xboxmpv.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import ca.awoo.jabert.Format;
import ca.awoo.jabert.FormatException;
import ca.awoo.xboxmpv.web.exceptions.InternalServerException;
import ca.awoo.xboxmpv.web.exceptions.NotAcceptableException;

public class ViewMiddleware implements WebHandler {
    private final WebHandler next;

    public ViewMiddleware(WebHandler next){
        this.next = next;
    }

    @Override
    public void handle(WebContext context) throws WebException {
        //TODO: it's almost impressive how much this isn't how you do content negotiation
        List<String> acceptHeaders = context.getRequestHeader("Accept");
        List<String> accept = 
            acceptHeaders.stream()
                         .flatMap((s) -> Arrays.stream(s.split(",")))
                         .map((s) -> s.split(";")[0].trim())
                         .filter((s) -> !s.contains("*"))
                         .toList();
        Format format = null;
        String responseMime = null;
        for(String mime : accept){
            Optional<Format> f = context.getServer().getFormat(new ContentType(mime));
            if(f.isPresent()){
                format = f.get();
                responseMime = mime;
                break;
            }
        }
        if(format == null){
            throw new NotAcceptableException("Could not find matching format");
        }
        next.handle(context);
        context.setResponseHeader("Content-Type", responseMime);
        try {
            context.sendHeaders(200);
            format.emit(context.getModel(), context.getResponseBody());
        } catch (FormatException | IOException e) {
            throw new InternalServerException(e);
        }
    }
    
}
