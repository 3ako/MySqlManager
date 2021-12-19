package mySqlManager;

import mySqlManager.events.EventManager;
import mySqlManager.server.MySqlServer;

import java.util.ArrayList;

public class MySqlManager {
    private final EventManager eventManager = new EventManager();
    private ArrayList<MySqlServer> servers = new ArrayList<MySqlServer>();

    //Конструкторы
    public MySqlServer createServer(String ip, String user, String password){
        return this.createServer(ip,user,password,3306);
    }
    public MySqlServer createServer(String ip, String user, String password,Boolean connect){
        return this.createServer(ip,user,password,3306,connect);
    }
    public MySqlServer createServer(String ip, String user, String password,int port){
       return this.createServer(ip,user,password,port,false);
    }
    public MySqlServer createServer(String ip, String user, String password,int port,Boolean connect){
        MySqlServer server = new MySqlServer(ip,user,password,port,connect);
        servers.add(server);
        return server;
    }

    //Геттеры
    public EventManager getEventManager() {
        return eventManager;
    }
    public ArrayList<MySqlServer> getServers() {
        return servers;
    }
    public ArrayList<MySqlServer> getAllServers(){
        return servers;
    }

    //Сеттеры
    public void setServers(ArrayList<MySqlServer> servers) {
        this.servers = servers;
    }
}
