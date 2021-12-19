package mySqlManager.server.events;

import mySqlManager.server.MySqlServer;

import java.util.LinkedList;
import java.util.List;

public class ServerEventManager {
    private MySqlServer server;

    //Хранилища активных слушателей
    private List<ServerGetOneEventListener> getOneEventList = new LinkedList<>();
    private List<ServerSetSelectBaseListener> setSelectBase = new LinkedList<>();
    private List<ServerRegisterBaseListener> registerBase = new LinkedList<>();
    private List<ServerConnectListener> connect = new LinkedList<>();
    private List<ServerDisconnectListener> disconnect = new LinkedList<>();

    public ServerEventManager(MySqlServer server){
        this.server = server;
    }

    //Регистрация слушателей
    public void registerListener(ServerGetOneEventListener e){
        getOneEventList.add(e);
    }
    public void registerListener(ServerSetSelectBaseListener e){
        setSelectBase.add(e);
    }
    public void registerListener(ServerRegisterBaseListener e){
        registerBase.add(e);
    }
    public void registerListener(ServerConnectListener e){
        connect.add(e);
    }
    public void registerListener(ServerDisconnectListener e){
        disconnect.add(e);
    }

    //Нотификаторы
    public void notify(ServerGetResultSet e){
        e.setServerEventManager(this);
        for (ServerGetOneEventListener event:getOneEventList){
            event.getOneEventListener(e);
        }
    }
    public void notify(ServerSetSelectBase e){
        e.setServerEventManager(this);
        for (ServerSetSelectBaseListener event:setSelectBase){
            event.ServerSetSelectBaseListener(e);
        }
    }
    public void notify(ServerRegisterBase e){
        e.setServerEventManager(this);
        for (ServerRegisterBaseListener event:registerBase){
            event.ServerRegisterBaseListener(e);
        }
    }
    public void notify(ServerConnect e){
        e.setServerEventManager(this);
        for (ServerConnectListener event:connect){
            event.ServerConnectListener(e);
        }
    }
    public void notify(ServerDisconnect e){
        e.setServerEventManager(this);
        for (ServerDisconnectListener event:disconnect){
            event.ServerDisconnectListener(e);
        }
    }

    //Геттеры
    public MySqlServer getServer() {
        return server;
    }
}