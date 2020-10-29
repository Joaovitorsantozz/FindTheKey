package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.function.BiFunction;

import EngineInterfaces.Animable;
import GameObject.GameObject;
import GameObject.GameObjectHandler;
import Main.Game;
import Main.utils.Animator;
import Main.utils.LoadImage;

public class Player extends GameObject {
    public GameObjectHandler hand;
    private float speed = 7;
    public boolean isFalling,canJump;
    private int maxFrames=5,maxIndex=8;
    private BufferedImage sprP,
    anim[]=new BufferedImage[8],idle[]=new BufferedImage[8];
    
    Animator an;
    public Player(int x, int y, ID id, GameObjectHandler hand) {
        super(x, y, id);
        // TODO Auto-generated constructor stub
        this.hand = hand;
        this.dir=1;
        setDepth(10);
        setWidth(16 * 3);
        setHeight(32 * 3);
        sprP=new LoadImage("/PlayerSheet.png").getImage();
        anim=new LoadImage(null).Cut(8,0,0,32,32,sprP);
        idle=new LoadImage(null).Cut(8,32,0,32,32,sprP);
        an=new Animator(maxFrames,maxIndex);
        //////////////////
    }

    @Override
    public void tick() {
        // Do logic
        Fall();
        x += velX;
        y += velY;
        ///// Logic/////
        // Call Methods//
        Col();
        Moving((int) speed, hand);

    }

    public void Fall() {
        if (isFalling) {
            float grv = 1.4f;
            velY += grv;
            if (velY > 10)
                velY = 10;
        }
        if (hand.isJump()) {
            if (canJump)
             setVelY(-20);
            canJump = false;
        }
    }

    protected void Col() {
        for (int i = 0; i < Game.handler.object.size(); i++) {
            GameObject e = Game.handler.object.get(i);
            if (e.getId() == ID.Block) {
                if (getP().intersects(e.getP())) {
                    y = e.getY() - getHeight();
                    canJump = true;
                    velY = 0;
                    isFalling = false;
                } else {
                    isFalling = true;
                }
                if(getToP().intersects(e.getP())){
                    y=e.getY()+32;
                    velY=0;
                }
                if (getRightP().intersects(e.getP()))
                    x = getX() - e.getWidth()+25;
                if (getLeftP().intersects(e.getP()))
                    x = e.getX() + (getWidth() / 2)-5;
            }
        }
    }

    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //Anim\\
        if(Moving((int)speed,hand))an.setAnimation(anim);
        else if(!Moving((int)speed,hand))an.setAnimation(idle);
        //Switch Image\\
        if(dir==1)g.drawImage(an.getAnimation(),getX(),getY(),96,96,null);
        else if(dir==-1)g.drawImage(an.getAnimation(),getX()+48,getY(),-96,96,null);
    }


    private void Drawbounds(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.draw(getP());
        g2.draw(getRightP());
        g2.draw(getLeftP());
        g2.draw(getToP());
    }
    @Override
    public Rectangle getP() {
        // TODO Auto-generated method stub
        return new Rectangle((int) (getX() + (getWidth() / 2) - ((getWidth() / 2)-45 / 2)),
                (int) (getY() + (getHeight() / 2)), getWidth() / 2, getHeight() / 2 + 20);
    }

    public Rectangle getRightP() {return new Rectangle(getX() + (getWidth())+3, getY()+5, 5, getHeight() - 10);}
    public Rectangle getLeftP() {return new Rectangle(getX()+13, getY()+5, 5, getHeight() - 10); }
    public Rectangle getToP() {return new Rectangle(getX() + (getWidth() / 2) - ((getWidth() / 2) / 2)+10, getY(), getWidth() / 2, getHeight() / 2);}
    public Rectangle HitDetection() {return new Rectangle((int) getX() - 75, getY(), 200, 120);}


}