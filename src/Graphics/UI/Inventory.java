package Graphics.UI;

import Entity.Player;
import GameObject.GameObject;
import Main.Game;
import Main.HandlerGame;
import Main.utils.CustomColor;

import java.awt.*;

public class Inventory {
    private int width=48,height=48,x,y;
    private int slotSize[][]=new int[width][height];
    private int maxSlots;

    public Inventory(int x,int y,int ms){
        this.x=x;
        this.y=y;
        this.maxSlots=ms;
    }
    public void CreateRectangle(Graphics2D g, int maxSlots){
        for(int i=0;i<maxSlots;i++){
            g.setColor(CustomColor.lightbrown);
            g.fillRect((x+16)+(i*width),y-38,width+2,height+2);
        }
        for(int i=0;i<5;i++){
            g.setColor(CustomColor.brown);
            g.drawRect((x+16)+i,(y-38)+i,width+1,height+1);
        }
    }
    public void render(Graphics2D g){
        CreateRectangle(g,maxSlots);
        for(int i=0;i< Game.handler.object.size();i++) {
            GameObject e=Game.handler.object.get(i);
            if (e instanceof Player) {
                if(((Player) e).hasKey)
                g.drawImage(HandlerGame.spr.getSprite(64, 0, 16, 16), x+16, y - 34, 48, 48, null);
            }
        }
    }
}
