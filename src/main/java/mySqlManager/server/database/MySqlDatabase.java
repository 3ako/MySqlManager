package mySqlManager.server.database;

import mySqlManager.server.MySqlServer;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MySqlDatabase {
    private final MySqlServer server;
    private String name;
    private Connection connect;

    public MySqlDatabase(String databaseName, MySqlServer server){
        this.server = server;
        this.name = databaseName;
    }

    private void prepareStatement(final String sql) {
        try {
            final PreparedStatement ps = this.getServer().getConnection().prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean isSelect(){
        String base = this.getServer().getSelectedBase();
        if (base == this.getName()){
            return true;
        }
        return false;
    }
    public ResultSet getOne(String sql){
        if (sql.matches("(.*)LIMIT\\s+\\d+(.*)")){
            Matcher matcher = Pattern.compile("LIMIT\\s+\\d+").matcher(sql);
            if(matcher.find())
                sql = sql.replace(sql.substring(matcher.start(), matcher.end()),"LIMIT 1");
        }else {
           sql = sql.replace(";","");
           sql += " LIMIT 1;";
        }
        try{
            ResultSet rs = this.getResultSet(sql);
            rs.next();
//            rs.close();
            return rs;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public ResultSet getResultSet(final String sql) {
        if (this.getServer().getSelectBase() == null || this.getServer().getSelectBase().getName()!=this.getName()){
            this.getServer().selectBase(this);
        }
        return this.getServer().getResultSet(sql);
    }

    public Connection getConnection(){
        return this.getServer().getConnection();
    }
    public MySqlServer getServer() {
        return server;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
