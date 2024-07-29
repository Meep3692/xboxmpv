package ca.awoo.xboxmpv.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface WebContext {
    public InputStream getRequestBody();
    public OutputStream getResponseBody();
    public void sendHeaders(int code) throws IOException;
    public List<String> getRequestHeader(String name);
    public void setRequestHeader(String name, String value);
    public void addRequestHeader(String name, String value);
    public List<String> getResponseHeader(String name);
    public void setResponseHeader(String name, String value);
    public void addResponseHeader(String name, String value);
    public String getMethod();
    public void assertMethod(String method) throws WebException;
}
