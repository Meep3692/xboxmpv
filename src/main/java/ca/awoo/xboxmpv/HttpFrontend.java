package ca.awoo.xboxmpv;

import java.io.IOException;
import ca.awoo.xboxmpv.web.WebServer;

/**
 * An HTTP frontend to control the player
 */
public class HttpFrontend {
    private final WebServer server;
    private final Player player;

    /**
     * Create a new HttpFrontend for the given Player
     * @param player The player to control
     * @throws IOException If the WebServer throws an exception
     */
    public HttpFrontend(Player player) throws IOException{
        this.player = player;
        server = new WebServer();
    }

    /**
     * Start the WebServer to start serving clients
     */
    public void start(){
        server.start();
    }
}
