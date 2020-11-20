package Main.console;

import Entity.ID;
import GameObject.GameObject;
import Main.Game;
import Main.HandlerGame;
import Main.utils.FontStyle;
import Main.utils.LoadImage;
import Main.utils.Text.Text;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class ConsoleStyle{
    //Variables
    private int width,height;
    private LinkedList<GameObject>obj;
    public int x,y;
    public BufferedImage cons=new LoadImage("/UI/Console.png").getImage();
    public boolean isDragged,isHide=true;
    public ConsoleStyle(int width, int height, LinkedList<GameObject>obj, MouseListener ml) {
        this.width=width;
        this.height=height;
        this.obj=obj;
        x=Game.W-width+260;
        y=30;
    }
    public void drawConsole(Graphics g) {
        g.drawImage(cons, x, y, width, height, null);
        if (isDragged){
            x = Game.W - width - 30;
            for(int i=0;i<obj.size();i++) {
                GameObject e = obj.get(i);
                if(e.getId()!= ID.Block) {
                    g.setFont(FontStyle.getFont(20, 20));
                    g.setColor(Color.white);
                    g.drawString( ""+ e.getClass(), x+45, y+90 + (i * 50));
                }
            }
        }
        else if (isHide) x = Game.W - width + 260;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
