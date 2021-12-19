package mySqlManager.server.events;

import java.util.EventListener;

public interface ServerGetOneEventListener extends EventListener {
    void getOneEventListener(ServerGetResultSet e);
}
