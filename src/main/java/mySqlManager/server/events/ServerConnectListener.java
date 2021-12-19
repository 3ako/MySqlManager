package mySqlManager.server.events;

import java.util.EventListener;

public interface ServerConnectListener extends EventListener {
    void ServerConnectListener(ServerConnect e);
}
