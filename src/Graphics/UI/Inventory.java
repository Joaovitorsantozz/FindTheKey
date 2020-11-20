package Graphics.UI;

import Entity.Player;
import GameObject.GameObject;
import Main.Game;
import Main.HandlerGame;
import Main.utils.CustomColor;
import Main.utils.LoadImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Inventory {
    private int width=48,height=48,x,y;
    private int slotSize[][]=new int[width][height];
    private int maxSlots;
    public final BufferedImage inv=new LoadImage("/UI/HotBar.png").getImage();
    public Inventory(int x,int y,int ms){
        this.x=x;
        this.y=y;
        this.maxSlots=ms;
    }
    public void CreateRectangle(Graphics2D g, int maxSlots){
        //x+16 y-38
        for(int i=0;i<maxSlots;i++)
        g.drawImage(inv,x+20+(i*width),y-38,48+12,48+12,null);
    }
    public void render(Graphics2D g){
        CreateRectangle(g,maxSlots);
        for(int i=0;i< Game.handler.object.size();i++) {
            GameObject e=Game.handler.object.get(i);
            if (e instanceof Player) {
                if(((Player) e).hasKey)
                g.drawImage(HandlerGame.spr.getSprite(64, 0, 16, 16), x+24, y - 30, 48, 48, null);
            }
        }
    }
}
