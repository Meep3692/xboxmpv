package ca.awoo.xboxmpv.web;

import java.util.Optional;

import ca.awoo.jabert.Format;

/**
 * Provide a Praser Format based on a ContentType
 */
public interface FormatProvider {
    /**
     * Given a ContentType, return an appropriate Format
     * @param contentType the ContentType to match
     * @return An appropriate Format or Optional.none() if this FormatProvider cannot provide one
     * @throws WebException if an exception occurs such as ill-formated content type arguments
     */
    public Optional<Format> getFormat(ContentType contentType) throws WebException;
}
