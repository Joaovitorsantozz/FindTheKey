package Entity;

import GameObject.GameObject;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Key extends GameObject {
    private BufferedImage spr;
    private ID it;
    public Key(int x, int y, ID id) {
        super(x, y, id);
        setDepth(11);
        setWidth(48);
        setHeight(48);
        this.it=id;
        spr=it.SetImage(spr);
    }

    @Override
    public void tick() {
        System.out.print("Vivp");
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
