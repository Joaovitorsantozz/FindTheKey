package Entity.particles;

import Entity.ID;
import GameObject.GameObject;
import Main.Game;

import java.awt.*;
import java.util.Random;

public class Particles extends GameObject {
    Color color;
    public int life=50;
    private double dx,dy;
    private int speed=1;
    double rotate;
    public Particles(int x, int y,Color color,ID id) {
        super(x, y, id);
        setWidth(16);
        setHeight(16);
        dx=new Random().nextGaussian();
        dy=new Random().nextGaussian();
        this.color=color;
        velX=1.3f;
        velY=1.3f;
    }

    @Override
    public void tick() {
        x+=velX*dx;
        y+=velY*dy;
        life--;
        if(life<=0)Game.handler.particles.remove(this);
    }

    @Override
    public void render(Graphics g) {
        rotate+=0.3;
        Graphics2D g2=(Graphics2D)g;
        g.setColor(new Color(color.getRed(),color.getGreen(),color.getBlue(),life*5));
        g2.rotate(rotate,getX()+getWidth()/2,getY()+getHeight()/2);
        g.fillRect(x,y,getWidth(),getHeight());
        g2.rotate(-rotate,getX()+getWidth()/2,getY()+getHeight()/2);
    }

    @Override
    public Rectangle getP() {return null;}
    public Rectangle getRightP() {return null;}
    public Rectangle getLeftP() {return null;}
    public Rectangle getToP() {return null;}
}
