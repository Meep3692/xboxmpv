package ca.awoo.xboxmpv.web;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Optional;

import ca.awoo.jabert.FastJsonFormat;
import ca.awoo.jabert.Format;
import ca.awoo.xboxmpv.web.exceptions.UnsupportedMediaTypeException;

public class JsonFormatProvider implements FormatProvider{

    @Override
    public Optional<Format> getFormat(ContentType contentType) throws WebException {
        if(contentType.getMimetype().equals("application/json")){
            String encoding = contentType.getArgument("charset").orElse("UTF-8").toUpperCase();
            if(!Charset.isSupported(encoding)){
                throw new UnsupportedMediaTypeException("Unsupported charset: " + contentType.getArgument("charset").orElse("none"));
            }
            FastJsonFormat json = new FastJsonFormat(encoding);
            return Optional.of(json);
        }else{
            return Optional.empty();
        }
    }
    
}
