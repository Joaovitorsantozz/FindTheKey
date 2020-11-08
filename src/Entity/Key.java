package Entity;

import Entity.particles.ParticleHandler;
import GameObject.GameObject;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Key extends GameObject {
    private BufferedImage spr;
    public ParticleHandler ph;
    public static boolean spaw;
    public Key(int x, int y, ID id) {
        super(x, y, id);
        setDepth(11);
        setWidth(48);
        setHeight(48);
        spr= id.SetImage(spr);
        ph=new ParticleHandler();
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        col();
        gravity(true, 0.10f);
        Collect();
    }
    public void col(){
        for(int i=0;i<Game.handler.object.size();i++){
            GameObject ee=Game.handler.object.get(i);
            if(ee.getId()==ID.Block){
                if(getP().intersects(ee.getP())){
                    y = ee.getY() - getHeight()+10;
                }
            }
        }
    }
    public void Collect(){
        for(int i=0;i<Game.handler.object.size();i++) {
            GameObject ee = Game.handler.object.get(i);
            if(ee instanceof Player){
                if(getP().intersects(ee.getP())){
                    ((Player) ee).hasKey=true;
                    Game.handler.DeleteObject(this);
                    spaw=true;
                }
            }
        }
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(spr,getX(),getY(),getWidth(),getHeight(),null);
    }

    public Rectangle getP() {return new Rectangle(getX(),getY(), getWidth(), getHeight()); }
    public Rectangle getRightP() {return new Rectangle(getX() + (getWidth())+3, getY()+5, 5, getHeight() - 10);}
    public Rectangle getLeftP() {return new Rectangle(getX()+13, getY()+5, 5, getHeight() - 10); }
    public Rectangle getToP() {return new Rectangle(getX() + (getWidth() / 2) - ((getWidth() / 2) / 2)+10, getY()+20, getWidth() / 2, getHeight() / 2-20);}
}
