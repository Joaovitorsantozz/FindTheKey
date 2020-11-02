package Entity;

import GameObject.GameObject;
import Main.Game;
import Main.utils.LoadImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Door extends GameObject {
    public BufferedImage spr,lock,atualDraw=null;
    public boolean showDraw;
    public Door(int x, int y, ID id) {
        super(x, y, id);
        spr=new LoadImage("/GameObject/Door.png").getImage();
        lock=new LoadImage("/GameObject/Lock.png").getImage();
        setWidth(32*3);
        setHeight(41*3);
        setDepth(9);
    }

    @Override
    public void tick() {
        showDraw=false;
        for(int i=0;i<Game.handler.object.size();i++){
            GameObject e=Game.handler.object.get(i);
            if(e.getId()==ID.Player){
                if(calculateDistance(getX(),getY(),e.getX(),e.getY())<60){
                    atualDraw=lock;
                    showDraw=true;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(spr,getX(),getY(),getWidth(),getHeight(),null);
        if(showDraw)
        g.drawImage(atualDraw,getX()+32,getY()-80, atualDraw.getWidth()*3,atualDraw.getHeight()*3,null);
    }

    @Override
    public Rectangle getP() {
        return null;
    }

    @Override
    public Rectangle getRightP() {
        return null;
    }

    @Override
    public Rectangle getLeftP() {
        return null;
    }

    @Override
    public Rectangle getToP() {
        return null;
    }
}
