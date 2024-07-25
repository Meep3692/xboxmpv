package ca.awoo.xboxmpv;

public class App {
    public static void main(String[] args) throws InterruptedException, MpvException{

        Player player = new Player(
            new MpvOption("idle", "yes"),
            new MpvOption("force-window", "immediate"),
            new MpvOption("osc", "yes"));
        player.enqueue("https://www.youtube.com/watch?v=wTuhDxQk5fI");
        
        System.out.println("Should be running?");
        while(true){
            MpvEvent event = player.waitEvent(10);
            System.out.println(event);
            if(event.equals(MpvEvent.MPV_EVENT_SHUTDOWN)){
                break;
            }
            if(event.equals(MpvEvent.MPV_EVENT_END_FILE)){
                Thread.sleep(1000);
                player.enqueue("https://www.youtube.com/watch?v=VSrYhbYIwR4");
            }
        }
    }
}
