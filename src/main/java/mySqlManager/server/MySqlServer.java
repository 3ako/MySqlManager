package mySqlManager.server;

import mySqlManager.server.database.MySqlDatabase;
import mySqlManager.server.events.*;
import mySqlUI.Ui;

import java.sql.*;
import java.util.ArrayList;

public class MySqlServer {
    private String ip;
    private String user;
    private String password;
    private int port;
    private Connection connection;
    public Boolean autoReconnect = true;
    private ArrayList<MySqlDatabase> databases = new ArrayList<MySqlDatabase>();
    private String selectedBase;
    private ServerEventManager serverEventManager;
    private Ui ui;
    
    //Конструкторы
    public MySqlServer(String ip, String user, String password, int port, Boolean connect){
        this.ip = ip;
        this.user = user;
        this.password = password;
        this.port = port;
        this.setServerEventManager(new ServerEventManager(this));
        if (connect){
            this.connect();
        }
    }
    public MySqlServer(String ip, String user, String password, int port){
        this(ip,user,password,port,false);
    }

    //Базы
    public ArrayList<MySqlDatabase> registerAllDatabases(){
        try {
//            Statement stmt = connection.createStatement();
            ResultSet rs = getResultSet("Show Databases");
            while(rs.next()) {
                this.registerBase(rs.getString(1));
            }
            return this.databases;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public MySqlDatabase registerBase(String name){
        MySqlDatabase base = new MySqlDatabase(name,this);
        databases.add(base);
        this.getEventManager().notify(new ServerRegisterBase(this,base));
//        System.out.println("Зарегистрирована база "+base.getName());
        return base;
    }

    //Состояния
    public void connect(){
        if (!this.isConnect()) {
            try {
                this.connection = DriverManager.getConnection(this.getConnectionUrl(), this.getUser(), this.getPassword());
                System.out.println("# MySQL connected");
                this.getEventManager().notify(new ServerConnect(this));
            }
            catch (SQLException e) {
                System.out.println("Возникла ошибка при подключении к серверу: ");
                e.printStackTrace();
            }
        }
    }
    public void disconnect(){
        if (this.isConnect()) {
            try {
                this.connection.close();
                System.out.println("# MySql server "+this.getUser()+" disconnect");
                this.getEventManager().notify(new ServerDisconnect(this));
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void reconnect(){
        if (isConnect()){
            this.disconnect();
            this.connect();
        }
    }
    public Boolean isConnect(){
        return this.connection != null;
    }
    public void selectBase(MySqlDatabase base){
        try {
            Statement stmt = connection.createStatement();
            String baseName = base.getName();
            stmt.executeUpdate("USE "+baseName);
            this.setSelectedBase(baseName);
            this.getEventManager().notify(new ServerSetSelectBase(this,base));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Запросы
    private void prepareStatement(final String sql) {
        try {
            final PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Object getResult(final String sql) {
        try {
            final PreparedStatement ps = this.getConnection().prepareStatement(sql);
            final ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                final Object result = rs.getObject(1);
                rs.close();
                ps.close();
                return result;
            }
            rs.close();
            ps.close();
            return null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public ResultSet getResultSet(final String sql) {
        try {
            final PreparedStatement ps = this.getConnection().prepareStatement(sql);
            final ResultSet rs = ps.executeQuery();
            this.getEventManager().notify(new ServerGetResultSet(this,sql));
            return rs;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Интерфейс
    public Ui startUi(){
        this.setUi(new Ui(this));
        return this.getUi();
    }

    //Геттеры
    public ServerEventManager getServerEventManager() {
        return serverEventManager;
    }
    public Ui getUi() {
        return ui;
    }
    public ArrayList<MySqlDatabase> getDatabases() {
        return databases;
    }
    public String getUser() {
        return user;
    }
    public Connection getConnection(){
        return this.connection;
    }
    public String getConnectionUrl() {
        return "jdbc:mysql://" + this.getIp() + ":" + this.getPort()
                + ""+ "?autoReconnect="+this.autoReconnect
                +"&useUnicode=true&characterEncoding=utf8";
    }
    public String getPassword() {
        return password;
    }
    public String getIp() {
        return ip;
    }
    public int getPort() {
        return port;
    }
    public MySqlDatabase getSelectBase(){
        for (MySqlDatabase b : this.getDatabases()){
            if (b.getName().equals(this.selectedBase)){
                return b;
            }
        }
        return null;
    }
    public MySqlDatabase getBaseByName(String name){
        for (MySqlDatabase base : this.getDatabases()){
            if (base.getName().equals(name)){
                return base;
            }
        }
        return null;
    }
    public ServerEventManager getEventManager() {
        return this.serverEventManager;
    }
    public String getSelectedBase() {
        return selectedBase;
    }
    public Boolean getAutoReconnect() {
        return autoReconnect;
    }

    //Сеттеры
    public void setServerEventManager(ServerEventManager serverEventManager) {
        this.serverEventManager = serverEventManager;
    }
    public void setUi(Ui ui) {
        this.ui = ui;
    }
    public void setDatabases(ArrayList<MySqlDatabase> databases) {
        this.databases = databases;
    }
    public void setAutoReconnect(Boolean autoReconnect) {
        this.autoReconnect = autoReconnect;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public void setEventManager(ServerEventManager eventManager) {
        this.serverEventManager = eventManager;
    }
    public void setIp(String ip) {
        this.ip = ip;
        this.reconnect();
    }
    public void setUser(String user) {
        this.user = user;
        this.reconnect();
    }
    public void setPassword(String password) {
        this.password = password;
        this.reconnect();
    }
    public void setPort(int port) {
        this.port = port;
        this.reconnect();
    }
    public void autoReconnect(Boolean state){
        this.autoReconnect = state;
        this.reconnect();
    }
    private void setSelectedBase(String base){
        this.selectedBase = base;
    }
}
