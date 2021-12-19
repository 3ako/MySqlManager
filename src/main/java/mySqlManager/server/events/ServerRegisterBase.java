package mySqlManager.server.events;

import mySqlManager.server.database.MySqlDatabase;

import java.util.EventObject;

public class ServerRegisterBase extends EventObject {
    private ServerEventManager serverEventManager;
    private MySqlDatabase base;
    public ServerRegisterBase(Object source, MySqlDatabase base) {
        super(source);
        this.setBase(base);
    }

    public ServerEventManager getServerEventManager() {
        return serverEventManager;
    }

    public MySqlDatabase getBase() {
        return base;
    }

    private void setBase(MySqlDatabase base) {
        this.base = base;
    }

    public void setServerEventManager(ServerEventManager serverEventManager) {
        this.serverEventManager = serverEventManager;
    }
}
