package mySqlUI;
import mySqlManager.server.database.MySqlDatabase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InfoMonitor {
    private JButton button1;
    private JPanel panel1;
    private JLabel server;
    private JLabel user;
    private JLabel port;
    private JLabel base;
    private JScrollPane basespanel;
    private JTable table1;
    private JPanel mainInfo;
    private JLabel connect;
    private String ipData;
    private String userData;
    private int portData;
    private String baseData;
    DefaultTableModel bases = new DefaultTableModel();
    private String[] columns = {"База","Статус"};
    public InfoMonitor(Frame frame){
        frame.getContentPane().add(panel1, BorderLayout.NORTH);
        for (int i = 0; i<this.columns.length;i = i+1){
            bases.addColumn(this.columns[i]);
        }
        this.table1.setModel(bases);

    }
    //Лоадеры
    public void addBase(MySqlDatabase base){
        this.bases.addRow(new Object[]{base.getName(),base.isSelect()});
    }
    public void reloadBaseForTable(String basename,MySqlDatabase base){
        for (int i = 0; i<this.bases.getRowCount();i = i+1) {
            if (this.bases.getValueAt(i,0).equals(basename)){
                this.bases.setValueAt(base.isSelect(),i,1);
            }
        }
    }

    //Геттеры
    public String getServer() {
        return ipData;
    }
    public int getPort() {
        return portData;
    }
    public String getUser() {
        return userData;
    }
    public String getBase(){
        return this.baseData;
    }

    //Сеттеры
    public void setBase(String base) {
        this.baseData = base;
        this.base.setText("Выбранная база: "+this.getBase());
    }
    public void setUser(String user) {
        this.userData = user;
        this.user.setText("Пользователь: "+this.getUser());
    }
    public void setServer(String server) {
        this.ipData = server;
        this.server.setText("Сервер: "+this.getServer());
    }
    public void setConnectStatus(Boolean status){
        this.connect.setText("Подключен: "+status);
    }
    public void setPort(int port) {
        this.portData = port;
        this.port.setText("Порт: "+this.getPort());
    }
}
