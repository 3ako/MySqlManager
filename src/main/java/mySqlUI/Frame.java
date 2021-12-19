package mySqlUI;

import mySqlUI.logs.LogFrame;

import javax.swing.*;
import java.awt.*;


public class Frame extends JFrame {
    private LogFrame logFrame;
    private InfoMonitor infoMonitor;
    public Frame() {
        setVisible(true);
        int width = 820;
        int height = 680;
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth(); // Ширина монитора
        int screenHeight = gd.getDisplayMode().getHeight(); // Высота монитора
        this.setLayout(new BorderLayout());
        setBounds(screenWidth / 2 - width / 2, screenHeight / 2 - height / 2, width, height);
        this.setInfoMonitor(new InfoMonitor(this));
        this.setLogFrame(new LogFrame(this));
    }

    public InfoMonitor getInfoMonitor() {
        return infoMonitor;
    }

    public void setInfoMonitor(InfoMonitor infoMonitor) {
        this.infoMonitor = infoMonitor;
    }

    public LogFrame getLogFrame() {
        return logFrame;
    }

    private void setLogFrame(LogFrame logFrame) {
        this.logFrame = logFrame;
    }
}
