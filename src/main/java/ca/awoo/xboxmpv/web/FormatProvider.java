package ca.awoo.xboxmpv.web;

import java.util.Optional;

import ca.awoo.jabert.Format;

public interface FormatProvider {
    public Optional<Format> getFormat(ContentType contentType) throws WebException;
}
