package Main.utils;

import Main.Game;
import Main.MouseInput;
import Main.Windows;
import Main.console.ConsoleHandler;
import Main.console.ConsoleStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class ConsolePanel extends Canvas {
    public ConsoleStyle con;
    public static ConsoleHandler ch;
    public static int w=350, h=670;
    public ConsolePanel(Game game) {
        new Windows(w,h,"Console",game);
        con=new ConsoleStyle(350,670);
        ch=new ConsoleHandler();

    }
    public void tick(){
        try {
            ch.UpdateButtons();
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public void render(){
        BufferStrategy bs=this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g=bs.getDrawGraphics();
        con.drawConsole(g);
        ch.drawButtons(g);
        g.dispose();
        bs.show();
    }
}
