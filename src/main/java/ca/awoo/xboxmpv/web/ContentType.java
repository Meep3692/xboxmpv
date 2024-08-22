package ca.awoo.xboxmpv.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import ca.awoo.xboxmpv.web.exceptions.UnsupportedMediaTypeException;

public class ContentType {
    private final String mimetype;
    private final Map<String, String> arguments;

    public ContentType(String mimetype){
        this.mimetype = mimetype;
        this.arguments = new HashMap<>();
    }

    public String getMimetype(){
        return mimetype;
    }

    public Optional<String> getArgument(String key){
        return Optional.ofNullable(arguments.get(key));
    }

    public void addArgument(String key, String value){
        arguments.put(key, value);
    }

    public static ContentType parseHeader(String header) throws UnsupportedMediaTypeException{
        String[] mimesplit = header.split(";");
        if(mimesplit.length != 2){
            throw new UnsupportedMediaTypeException("More than one semicolon in Content-Type header");
        }
        String mimetype = mimesplit[0].trim();
        ContentType contentType = new ContentType(mimetype);
        String[] params = mimesplit[1].split(" ");
        for(String param : params){
            if(param.length() > 0){
                String[] paramSplit = param.split("=");
                if(paramSplit.length != 2){
                    throw new UnsupportedMediaTypeException("Param somehow has more than one equals in Content-Type header");
                }
                contentType.addArgument(paramSplit[0], paramSplit[1]);
            }
        }
        return contentType;
    }
}
