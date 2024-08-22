package ca.awoo.xboxmpv.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import ca.awoo.xboxmpv.web.exceptions.UnsupportedMediaTypeException;

/**
 * Represents the contents of a Content-Type header
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Type">Content-Type - HTTP | MDN</a>
 * @see <a href="https://www.iana.org/assignments/media-types/media-types.xhtml">Media Types</a>
 */
public class ContentType {
    private final String mimetype;
    private final Map<String, String> arguments;

    /**
     * Create a new ContentType with the given mimetype and no arguments
     * @param mimetype the mimetype of the ContentType
     */
    public ContentType(String mimetype){
        this.mimetype = mimetype;
        this.arguments = new HashMap<>();
    }

    /**
     * Get the mimetype of the ContentType
     * @return The mimetype 
     */
    public String getMimetype(){
        return mimetype;
    }

    /**
     * Try to get value of the given argument
     * @param key The argument key
     * @return The argument value or Optional.none() if the argument is not set
     */
    public Optional<String> getArgument(String key){
        return Optional.ofNullable(arguments.get(key));
    }

    /**
     * Set the given argument
     * @param key the key of the argument
     * @param value the new value of the argument
     */
    public void addArgument(String key, String value){
        arguments.put(key, value);
    }

    /**
     * Parse a Content-Type header into a ContentType object
     * @param header the Content-Type header value not including "Content-Type: " or the newline at the end.
     * @return The ContentType that represents the value of the Content-Type header
     * @throws UnsupportedMediaTypeException if we cannot parse the header
     */
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
