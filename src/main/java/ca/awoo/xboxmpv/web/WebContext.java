package ca.awoo.xboxmpv.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import ca.awoo.jabert.SValue;

/**
 * Contains all context for a full HTTP transaction
 */
public interface WebContext {
    /**
     * Get request body stream.
     * <p>
     * This gets the raw stream of the request. To get the parsed body based on the request Content-Type, use {@link readRequestBody}.
     * </p>
     * @return Request body stream.
     */
    public InputStream getRequestBody();

    /**
     * Get response body stream.
     * <p>
     * This is for writing raw bytes to the client. To use MVC features like ViewMiddleware, use {@link setModel}.
     * </p>
     * @return Repsonse body stream.
     */
    public OutputStream getResponseBody();

    /**
     * Send headers. Headers cannot be modified after calling this
     * @param code the status code to send
     * @throws IOException if the stream throws an exception while writing
     */
    public void sendHeaders(int code) throws IOException;

    /**
     * Get request header
     * @param name the header name to get
     * @return A list of values for the header <p>The list will have 0 entries if the header is not present and more than one if the header is present multiple times.</p>
     */
    public List<String> getRequestHeader(String name);

    /**
     * Set request header, clearing all values of that header first
     * <p>
     * For use by middleware to modify header data in transit
     * </p>
     * @param name name of the header
     * @param value the new value of the header
     */
    public void setRequestHeader(String name, String value);

    /**
     * Add request header without clearing existing values
     * <p>
     * For use by middleware to modify header data in transit
     * </p>
     * @param name name of the header
     * @param value the new value of the header
     */
    public void addRequestHeader(String name, String value);

    /**
     * Get response header
     * <p>
     * For use by middleware checking what the handler set the header to
     * </p>
     * @param name name of the header
     * @return A list of values for the header <p>The list will have 0 entries if the header is not present and more than one if the header is present multiple times.</p>
     */
    public List<String> getResponseHeader(String name);

    /**
     * Set response header, clearing all values of that header first
     * @param name name of the header
     * @param value the new value of the header
     */
    public void setResponseHeader(String name, String value);

    /**
     * Add response header without clearing existing values
     * @param name name of the header
     * @param value the new value of the header
     */
    public void addResponseHeader(String name, String value);

    /**
     * Get the request method
     * @return The request method as specified by the client
     */
    public String getMethod();

    /**
     * Assert that the specified method was used
     * @param method The method that should be used
     * @throws WebException If the client is using a different method
     */
    public void assertMethod(String method) throws WebException;

    /**
     * Read the request body into an SValue
     * @return The SValue representation of the request body
     * @throws WebException If the body cannot be parsed or the content type is invalid
     */
    public SValue readRequestBody() throws WebException;

    /**
     * Set the response model
     * <p>
     * This is for MVC systems. By default, the WebServer does nothing with this. Instead, you need a middleware that does use this.
     * </p>
     * @param value The SValue representation of the model
     */
    public void setModel(SValue value);

    /**
     * Get the set response model
     * <p>
     * This is the method that MVC middleware will use to get model info
     * </p>
     * @return The set response model
     */
    public SValue getModel();

    /**
     * Get the server that is serving this request.
     * @return The server
     */
    public WebServer getServer();
}
