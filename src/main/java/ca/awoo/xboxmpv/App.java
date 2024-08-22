package ca.awoo.xboxmpv;

import java.io.IOException;

/**
 * Entry point class.
 */
public class App {
    /**
     * Main entry point. Creates a player and frontends for it.
     * @param args Command line arguments. Not used.
     * @throws MpvException if the player encounters a problem
     * @throws IOException if the HttpFrontend fails to create 
     */
    public static void main(String[] args) throws MpvException, IOException{

        Player player = new Player(
            new MpvOption("idle", "yes"),
            new MpvOption("force-window", "immediate"),
            new MpvOption("osc", "yes"));
        player.enqueue("https://www.youtube.com/watch?v=wTuhDxQk5fI");
        
        System.out.println("Should be running?");
        HttpFrontend frontend = new HttpFrontend(player);
        frontend.start();
    }
}
