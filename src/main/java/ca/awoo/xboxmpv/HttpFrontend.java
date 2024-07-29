package ca.awoo.xboxmpv;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

import com.sun.net.httpserver.HttpServer;

public class HttpFrontend {
    private final Player player;
    private final HttpServer listener;

    public HttpFrontend(Player player) throws IOException{
        this.player = player;
        listener = HttpServer.create();
        listener.bind(new InetSocketAddress(8080), 0);
        listener.createContext("/playlist", exchange -> {
            List<String> playlist = player.getPlaylist();
            String response = "<ol>";
            for(String item : playlist){
                response += "<li>";
                response += item;
                response += "</li>";
            }
            response += "</ol>";
            byte[] responseBytes = response.getBytes();
            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=utf-8");
            exchange.sendResponseHeaders(200, responseBytes.length);
            exchange.getResponseBody().write(responseBytes);
            exchange.close();
        });
        listener.createContext("/enqueue", exchange -> {
            if(!exchange.getRequestMethod().equals("POST")){
                exchange.sendResponseHeaders(405, 0);
                exchange.close();
                return;
            }
        });
        listener.createContext("/", exchange -> {
            String page = "<form action=\"/enqueue\"><input type=\"text\" id=\"url\" name=\"url\"><input type=\"submit\"></form>";
            byte[] responseBytes = page.getBytes();
            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=utf-8");
            exchange.sendResponseHeaders(200, responseBytes.length);
            exchange.getResponseBody().write(responseBytes);
            exchange.close();
        });
    }

    public void start(){
        listener.start();
    }
}
