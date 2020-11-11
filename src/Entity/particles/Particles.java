package Entity.particles;

import Entity.ID;
import GameObject.GameObject;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Particles extends GameObject {
    Color color;
    public int life=150;
    private double dx,dy;
    public BufferedImage particle;
    boolean oval;
    double rotate;
    public Particles(int x, int y,float velX,float velY,Color color,ID id,BufferedImage part) {
        super(x, y, id);
        setWidth(256);
        setHeight(256);
        dx=new Random().nextGaussian();
        dy=new Random().nextGaussian();
        this.color=color;
        this.velX=velX;
        this.velY=velY;
        this.particle=part;
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
        g2.rotate(rotate,getX()+getWidth()/2f,getY()+getHeight()/2f);
        if(particle==null)renderFormat(g);
        else renderImage(g);
        g2.rotate(-rotate,getX()+getWidth()/2f,getY()+getHeight()/2f);
    }
    public void renderImage(Graphics g){
        g.drawImage(particle,getX(),getY(),getWidth(),getHeight(),null);
    }
    public void renderFormat(Graphics g){
        g.setColor(new Color(color.getRed(),color.getGreen(),color.getBlue(),life*3));
        if (oval) g.fillOval(x, y, getWidth(), getHeight());
        else g.fillRect(x, y, getWidth(), getHeight());
    }
    @Override
    public Rectangle getP() {return null;}
    public Rectangle getRightP() {return null;}
    public Rectangle getLeftP() {return null;}
    public Rectangle getToP() {return null;}
    public boolean isOval() {
        return oval;
    }

    public void setOval(boolean oval) {
        this.oval = oval;
    }
}
