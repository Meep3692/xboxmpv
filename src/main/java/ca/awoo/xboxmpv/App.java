package ca.awoo.xboxmpv;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws InterruptedException, MpvException, IOException{

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
