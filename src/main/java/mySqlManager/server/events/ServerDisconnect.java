package mySqlManager.server.events;

import java.util.EventObject;

public class ServerDisconnect extends EventObject {
    private ServerEventManager serverEventManager;
    private String sql;
    public ServerDisconnect(Object source) {
        super(source);
    }

    public ServerEventManager getServerEventManager() {
        return serverEventManager;
    }

    public void setServerEventManager(ServerEventManager serverEventManager) {
        this.serverEventManager = serverEventManager;
    }
}
