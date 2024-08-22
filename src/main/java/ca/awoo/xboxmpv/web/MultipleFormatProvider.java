package ca.awoo.xboxmpv.web;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import ca.awoo.jabert.Format;

public class MultipleFormatProvider implements FormatProvider {
    private final Set<FormatProvider> providers = new HashSet<>();

    public void addFormat(FormatProvider provider){
        providers.add(provider);
    }

    @Override
    public Optional<Format> getFormat(ContentType contentType) throws WebException{
        for(FormatProvider provider : providers){
            Optional<Format> format = provider.getFormat(contentType);
            if(format.isPresent()){
                return format;
            }
        }
        return Optional.empty();
    }
    
}
