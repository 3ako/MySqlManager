package mySqlManager.server.events;

import java.util.EventObject;

public class ServerGetResultSet extends EventObject {
    private ServerEventManager serverEventManager;
    private String sql;
    public ServerGetResultSet(Object source, String sql) {
        super(source);
        this.setSql(sql);
    }

    public ServerEventManager getServerEventManager() {
        return serverEventManager;
    }

    public String getSql() {
        return sql;
    }

    private void setSql(String sql) {
        this.sql = sql;
    }

    public void setServerEventManager(ServerEventManager serverEventManager) {
        this.serverEventManager = serverEventManager;
    }
}
