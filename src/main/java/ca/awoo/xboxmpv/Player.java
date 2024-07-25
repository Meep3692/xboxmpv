package ca.awoo.xboxmpv;

import ca.awoo.xboxmpv.MPV.mpv_event;

public class Player {
    private final MPV mpv;
    private final long handle;

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
    }

    public MpvEvent waitEvent(double timeout){
        mpv_event nativeEvent = mpv.mpv_wait_event(handle, 10);
        MpvEvent event = MpvEvent.fromValue(nativeEvent.event_id);
        return event;
    }
}
