package Graphics.UI;

import Entity.Player;
import Main.HandlerGame;

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
            g.setColor(new Color(135,60,14));
            g.fillRect(x+(i*width),y,width+2,height+2);
        }
        for(int i=0;i<5;i++){
            g.setColor(new Color(99,44,9));
            g.drawRect(x+i,y+i,width+1,height+1);
        }
    }
    public void render(Graphics2D g){
        CreateRectangle(g,maxSlots);
        if(Player.hasKey){
            g.drawImage(HandlerGame.spr.getSprite(64,0,16,16),x,y+5,48,48,null);
        }
    }
}
