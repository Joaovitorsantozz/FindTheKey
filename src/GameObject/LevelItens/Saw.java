package GameObject.LevelItens;

import Entity.ID;
import GameObject.GameObject;
import Main.utils.Animator;
import Main.utils.LoadImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Saw extends GameObject {
    public BufferedImage init,anim[];
    private int mF=3,mI=7;
    Animator an;
    public Saw(int x, int y, ID id) {
        super(x, y, id);
        init=new LoadImage("/GameObject/Saw.png").getImage();
        anim=new LoadImage(null).Cut(7,0,0,32,32,init);
        an=new Animator(mF,mI);
        an.setAnimation(anim);
        setWidth(32*3);
        setHeight(32*3);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2=(Graphics2D)g;
        g.drawImage(an.getAnimation(),getX(),getY(),getWidth(),getHeight(),null);
        g.setColor(Color.white);

    }

    @Override
    public Rectangle getP() {
        return new Rectangle(getX(),getY(),getWidth()-15,getHeight()-15);
    }

    public Rectangle getRightP() {
        return null;
    }

    public Rectangle getLeftP() {
        return null;
    }

    public Rectangle getToP() {
        return null;
    }
}
