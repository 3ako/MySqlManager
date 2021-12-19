package mySqlUI.logs;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFrame {
    private Frame frame;
    private JTextArea ta = new JTextArea();
    private JScrollPane sp = new JScrollPane(ta);
    public LogFrame(Frame frame){
        this.frame = frame;
        ta.setLineWrap(true);
        ta.setEditable(false);
        sp.setBounds(5,5,700,300);
        sp.setPreferredSize(new Dimension(40, 150));
        frame.add(sp,BorderLayout.SOUTH);
    }
    public void log(String in){
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        ta.append("["+formatForDateNow.format(date)+"]    "+in+"\n");
    }
}
