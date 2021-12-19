package mySqlManager.events;

import java.util.LinkedList;
import java.util.List;

public class EventManager {
    private List<RegisterServerListener> registerServer = new LinkedList<>();

    public void registerListener (RegisterServerListener e){
        registerServer.add(e);
    }

    public void notify(RegisterServer e){
        for (RegisterServerListener event:registerServer){
            event.getOneEventListener(e);
        }
    }
}
