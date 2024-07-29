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
                StringBuilder html = new StringBuilder();
                html.append("<h1>Error ");
                html.append(e.responseCode);
                html.append("</h1>");
                String message = e.getMessage();
                if(!message.isEmpty()){
                    html.append("<h2>");
                    html.append(message);
                    html.append("/<h2>");
                }
                exceptionToHtml(html, e);
                byte[] bytes = html.toString().getBytes();
                exchange.getResponseHeaders().add("Content-Type", "text/html; charset=utf-8");
                exchange.sendResponseHeaders(e.responseCode, bytes.length);
                exchange.getResponseBody().write(bytes);
                exchange.close();
            } finally {
                exchange.close();
            }
        };
        server.createContext("/", httpHandler);
    }

    private void exceptionToHtml(StringBuilder html, Exception e){
        html.append("<p>");
        html.append(e.toString());
        html.append("</p>");
        html.append("<blockquote>");
        html.append("<ul>");
        for(StackTraceElement traceElement : e.getStackTrace()){
            html.append("<li>");
            html.append("at " + traceElement);
            html.append("</li>");
        }
        html.append("</ul>");
        html.append("</blockquote>");
        html.append("<blockquote><ul>");
        for(Throwable se : e.getSuppressed()){
            html.append("<li>");
            enclosedToHtml(html, se, e.getStackTrace(), "Suppressed: ");
            html.append("</li>");
        }
        html.append("</ul></blockquote>");
        Throwable ourCause = e.getCause();
        if(ourCause != null){
            enclosedToHtml(html, ourCause, e.getStackTrace(), "Caused by: ");
        }
    }

    private void enclosedToHtml(StringBuilder html, Throwable e, StackTraceElement[] enclosingTrace, String caption){
        StackTraceElement[] trace = e.getStackTrace();
        int m = trace.length - 1;
        int n = enclosingTrace.length - 1;
        while(m >= 0 && n >= 0 && trace[m].equals(enclosingTrace[n])){
            m--;n--;
        }
        int framesInCommon = trace.length - 1 - m;

        html.append("<p>");
        html.append(caption + e.toString());
        html.append("</p>");
        html.append("<blockquote><ul>");
        for(int i = 0; i <= m; i++){
            html.append("<li>");
            html.append("at " + trace[i]);
            html.append("</li>");
        }
        if(framesInCommon != 0){
            html.append("<li>... " + framesInCommon + " more</li>");
        }
        html.append("</ul></blockquote>");
        html.append("<blockquote><ul>");
        for(Throwable se : e.getSuppressed()){
            html.append("<li>");
            enclosedToHtml(html, se, trace, "Suppressed: ");
            html.append("</li>");
        }
        html.append("</ul></blockquote>");
        Throwable ourCause = e.getCause();
        if(ourCause != null){
            enclosedToHtml(html, ourCause, trace, "Caused by: ");
        }
    }
}
