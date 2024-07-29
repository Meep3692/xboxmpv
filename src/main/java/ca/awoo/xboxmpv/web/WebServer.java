package ca.awoo.xboxmpv.web;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;

public class WebServer {
    private final HttpServer server;
    private final HttpHandler httpHandler;

    private WebHandler handler;

    public WebServer() throws IOException{
        server = HttpServer.create();
        server.bind(new InetSocketAddress(8080), 0);
        httpHandler = exchange -> {
            WebContext context = new HttpWebContext(exchange);
            try {
                handler.handle(context);
            } catch (WebException e) {
                // TODO Send error page
                e.printStackTrace();
            } finally {
                exchange.close();
            }
        };
        server.createContext("/", httpHandler);
    }
}
