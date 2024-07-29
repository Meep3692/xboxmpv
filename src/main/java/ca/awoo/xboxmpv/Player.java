package ca.awoo.xboxmpv;

import java.util.ArrayList;
import java.util.List;

import ca.awoo.xboxmpv.MPV.mpv_event;

public class Player {
    private final MPV mpv;
    private final long handle;

    private List<String> playlist;
    private int position = 0;

    private Thread listenThread;

    public Player(MpvOption... options) throws MpvException{
        this.mpv = MPV.INSTANCE;
        this.handle = mpv.mpv_create();
        for(MpvOption option : options){
            setOption(option);
        }
        int error;
        error = mpv.mpv_initialize(handle);
        if(error != 0){
            throw new MpvException(error, "Failed to initialize player");
        }
        playlist = new ArrayList<>();
        listenThread = new Thread(() -> {
            while(true){
                MpvEvent event = waitEvent(10);
                System.out.println(event);
                if(event.equals(MpvEvent.MPV_EVENT_SHUTDOWN)){
                    break;
                }
                if(event.equals(MpvEvent.MPV_EVENT_END_FILE)){
                    position++;
                }
            }
        });
        listenThread.start();
    }

    public void setOption(MpvOption option) throws MpvException{
        int error = mpv.mpv_set_property_string(handle, option.name(), option.value());
        if(error != 0){
            throw new MpvException(error, "Exception while setting option " + option);
        }
    }

    public void enqueue(String file) throws MpvException{
        int error = mpv.mpv_command(handle, new String[]{"loadfile", file, "append-play"});
        if(error != 0){
            throw new MpvException(error, "Failed to enqueue file: " + file);
        }
        playlist.add(file);
    }

    private MpvEvent waitEvent(double timeout){
        mpv_event nativeEvent = mpv.mpv_wait_event(handle, 10);
        MpvEvent event = MpvEvent.fromValue(nativeEvent.event_id);
        return event;
    }

    public List<String> getPlaylist(){
        return playlist;
    }
}
