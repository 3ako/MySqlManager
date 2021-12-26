package mySqlManager;

import mySqlManager.events.EventManager;
import mySqlManager.server.MySqlServer;

import java.util.ArrayList;

public class MySqlManager {
    private final EventManager eventManager = new EventManager();
    private ArrayList<MySqlServer> servers = new ArrayList<MySqlServer>();

    //Конструкторы
    public MySqlServer registerServer(String ip, String user, String password){
        return this.registerServer(ip,user,password,3306);
    }
    public MySqlServer registerServer(String ip, String user, String password, Boolean connect){
        return this.registerServer(ip,user,password,3306,connect);
    }
    public MySqlServer registerServer(String ip, String user, String password, int port){
       return this.registerServer(ip,user,password,port,false);
    }
    public MySqlServer registerServer(String ip, String user, String password, int port, Boolean connect){
        MySqlServer server = new MySqlServer(ip,user,password,port,connect);
        servers.add(server);
        return server;
    }

    public Boolean removeServer(MySqlServer server){
        for (MySqlServer s:this.getServers()){
            if (server == s){
                if (s.isConnect())
                    s.disconnect();
                this.getServers().remove(s);
                return true;
            }
        }
        return false;
    }
    //Геттеры
    public EventManager getEventManager() {
        return eventManager;
    }
    public ArrayList<MySqlServer> getServers() {
        return servers;
    }

    //Сеттеры
    public void setServers(ArrayList<MySqlServer> servers) {
        this.servers = servers;
    }
}
