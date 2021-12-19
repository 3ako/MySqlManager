package mySqlUI;

import mySqlManager.server.MySqlServer;
import mySqlUI.eventhandler.*;

import javax.swing.*;
import java.awt.*;

public class Ui {
    private MySqlServer server;
    private Frame frame;
    public Ui (MySqlServer server) {
        this.setServer(server);
        //Загружаем цвета
        this.loadColorTheme();
        this.loadFrame();

        //Прописываем данные в монитор
        frame.getInfoMonitor().setPort(this.getServer().getPort());
        frame.getInfoMonitor().setServer(this.getServer().getIp());
        frame.getInfoMonitor().setUser(this.getServer().getUser());
        frame.getInfoMonitor().setBase(this.getServer().getSelectedBase());
        frame.getInfoMonitor().setConnectStatus(this.getServer().isConnect());

        //Регистрация событий
        server.getEventManager().registerListener(new GetOneEventListener(this));
        server.getEventManager().registerListener(new SetSelectBaseListener(this));
        server.getEventManager().registerListener(new RegisterBase(this));
        server.getEventManager().registerListener(new Connect(this));
        server.getEventManager().registerListener(new Disonnect(this));
    }
    //Геттеры
    public Frame getFrame() {
        return frame;
    }
    public MySqlServer getServer() {
        return server;
    }

    //Сеттеры
    private void setFrame(Frame frame) {
        this.frame = frame;
    }
    private void setServer(MySqlServer server) {
        this.server = server;
    }
    private void loadColorTheme(){
        UIManager.put( "control",new Color( 18, 30, 49) );
        UIManager.put( "info", new Color(73, 73, 73) );
        UIManager.put( "nimbusBase", new Color(26, 45, 75) );
        UIManager.put( "nimbusAlertYellow", new Color( 248, 187, 0) );
        UIManager.put( "nimbusDisabledText", new Color(44, 44, 44) );
        UIManager.put( "nimbusFocus", new Color(115,164,209) );
        UIManager.put( "nimbusGreen", new Color(176,179,50) );
        UIManager.put( "nimbusInfoBlue", new Color( 18, 30, 49) );
        UIManager.put( "nimbusLightBackground", new Color( 18, 30, 49) );
        UIManager.put( "nimbusOrange", new Color(191,98,4) );
        UIManager.put( "nimbusRed", new Color(169,46,34) );
        UIManager.put( "nimbusSelectedText", new Color( 255, 255, 255) );
        UIManager.put( "nimbusSelectionBackground", new Color(255, 255, 255) );
        UIManager.put( "text", new Color( 230, 230, 230) );
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (javax.swing.UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadFrame(){
        this.setFrame(new Frame());
        Frame frame = this.getFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Закрыл окно - удалил окно
        frame.setBackground(Color.GRAY);
        frame.setTitle(this.getServer().getIp()+'@'+this.getServer().getUser());
        frame.setVisible(true);
    }
}
