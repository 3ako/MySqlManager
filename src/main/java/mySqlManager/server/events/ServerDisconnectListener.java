package mySqlManager.server.events;

import java.util.EventListener;

public interface ServerDisconnectListener extends EventListener {
    void ServerDisconnectListener(ServerDisconnect e);
}
