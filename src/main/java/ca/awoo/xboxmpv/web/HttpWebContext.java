package ca.awoo.xboxmpv.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;

import ca.awoo.xboxmpv.web.exceptions.MethodNotAllowedException;

public class HttpWebContext implements WebContext{
    private final HttpExchange exchange;

    public HttpWebContext(HttpExchange exchange){
        this.exchange = exchange;
    }

    @Override
    public InputStream getRequestBody() {
        return exchange.getRequestBody();
    }

    @Override
    public OutputStream getResponseBody() {
        return exchange.getResponseBody();
    }

    @Override
    public void sendHeaders(int code) throws IOException {
        exchange.sendResponseHeaders(code, 0);
    }

    @Override
    public String getMethod() {
        return exchange.getRequestMethod();
    }

    @Override
    public void assertMethod(String method) throws WebException {
        if(!method.equals(exchange.getRequestMethod())){
            throw new MethodNotAllowedException();
        }
    }

    @Override
    public List<String> getRequestHeader(String name) {
        return exchange.getRequestHeaders().get(name);
    }

    @Override
    public void setRequestHeader(String name, String value) {
        exchange.getRequestHeaders().set(name, value);
    }

    @Override
    public void addRequestHeader(String name, String value) {
        exchange.getRequestHeaders().add(name, value);
    }

    @Override
    public List<String> getResponseHeader(String name) {
        return exchange.getResponseHeaders().get(name);
    }

    @Override
    public void setResponseHeader(String name, String value) {
        exchange.getResponseHeaders().set(name, value);
    }

    @Override
    public void addResponseHeader(String name, String value) {
        exchange.getResponseHeaders().add(name, value);
    }
}
