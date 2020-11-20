package Entity.particles;

import Entity.ID;
import GameObject.GameObject;
import Main.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Particles extends GameObject {
    Color color;
    public int life;
    private double dx,dy;
    public BufferedImage particle;
    public int alpha=250;
    boolean oval;
    double rotate;
    public Particles(int x, int y,float velX,float velY,int life,Color color,ID id,BufferedImage part) {
        super(x, y, id);
        setWidth(8+new Random().nextInt(8));
        setHeight(getWidth());
        dx=new Random().nextGaussian();
        dy=new Random().nextGaussian();
        this.color=color;
        this.velX=velX;
        this.velY=velY;
        this.particle=part;
        this.life=life;
    }

    @Override
    public void tick() {
        x+=velX*dx;
        y+=velY*dy;
        life--;
        if(alpha>=0) alpha--;
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
        g.setColor(new Color(color.getRed(),color.getGreen(),color.getBlue(),alpha));
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
