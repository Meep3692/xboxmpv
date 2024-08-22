package ca.awoo.xboxmpv;

import java.io.IOException;
import ca.awoo.xboxmpv.web.WebServer;

public class HttpFrontend {
    private final WebServer server;
    private final Player player;

    public HttpFrontend(Player player) throws IOException{
        this.player = player;
        server = new WebServer();
    }

    public void start(){
        server.start();
    }
}
